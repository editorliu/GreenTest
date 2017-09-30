package com.er.greentest.service;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.app.Service;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.TaskStackBuilder;
import android.util.Log;

import com.er.greentest.HGActivity;
import com.er.greentest.R;

/**
 * Created by Administrator on 2017/9/13.
 * Description：
 * Version：
 */

public class MyService extends Service {
    public static final int MSG_SAY_HELLO = 1;
    public static final String TAG = "MyService";
    private MyBinder myBinder = new MyBinder();

    public class MyBinder extends Binder {
        public MyService getService() {
            return MyService.this;
        }
    }

    private int count;

    @Override
    public void onCreate() {
        super.onCreate();

        Log.w(TAG, "------------onCreate()");
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                while (true) {
//                    try {
//                        Thread.sleep(1000);
//                        count++;
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//            }
//        });
//        thread.start();
    }

    public int getCount() {
        return count;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, final int startId) {
        Log.w(TAG, "------------onStartCommand()");
//        Thread thread = new Thread(new Runnable() {
//            @Override
//            public void run() {
//                int count = 0;
//                while (count < 5*startId) {
//                    try {
//                        Thread.sleep(1000);
//                        Log.w(TAG, "------------"+Thread.currentThread().getName()+" count=" + count++);
//                    } catch (InterruptedException e) {
//                        e.printStackTrace();
//                    }
//                }
//                stopSelf(startId);
//                Log.w(TAG, "------------stopSelf() startId " + startId);
//            }
//        }, "Thread id " + startId);
//        thread.start();

        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent hgIntent = new Intent(MyService.this, HGActivity.class);
                TaskStackBuilder taskStackBuilder = TaskStackBuilder.create(MyService.this);
                taskStackBuilder.addParentStack(HGActivity.class);
                taskStackBuilder.addNextIntent(hgIntent);

                Intent intent1 = taskStackBuilder.editIntentAt(1);
                intent1.putExtra("intent", "<这是第一个intent>");
                Intent intent2 = taskStackBuilder.editIntentAt(taskStackBuilder.getIntentCount() - 1);
                intent2.putExtra("intentLast", "<后去到IntentLast>");

                PendingIntent pendingIntent = taskStackBuilder.getPendingIntent(1800, PendingIntent.FLAG_UPDATE_CURRENT);
                NotificationCompat.Builder builder = new NotificationCompat.Builder(MyService.this)
                        .setSmallIcon(R.mipmap.ic_launcher)
                        .setContentTitle("hello")
                        .setContentText("中国人民abcdefg")
                        .setContentIntent(pendingIntent)
                        .setAutoCancel(true);
                NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
                for (int i = 0; i < 100; i++) {
                    builder.setProgress(100, i, false);
                    try {
                        Thread.sleep(200);
                        notificationManager.notify(3000, builder.build());
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
                builder.setProgress(0, 0, false);
                notificationManager.notify(3000, builder.build());


//                PendingIntent pendingIntent = PendingIntent.getActivity(MyService.this, PENDING_INTENT_CODE, hgIntent, PendingIntent.FLAG_UPDATE_CURRENT);
//                Notification notification =
//                        new NotificationCompat.Builder(MyService.this)
//                                .setSmallIcon(R.mipmap.ic_launcher)
//                                .setContentTitle("hello")
//                                .setContentText("中国人民abcdefg")
//                                .setContentIntent(pendingIntent)
//                                .build();
//                startForeground(FOREGROUND_ID, notification);
            }
        }, 5 * 1000);

        return super.onStartCommand(intent, flags, startId);
    }

    public static final int FOREGROUND_ID = 3020;
    public static final int PENDING_INTENT_CODE = 1001;

    private Messenger messenger = new Messenger(new MyHandler());

    private static class MyHandler extends Handler {
        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            switch (msg.what) {
                case MSG_SAY_HELLO:
                    Log.w(TAG, "------------MyService MSG_SAY_HELLO msg.arg1=" + msg.arg1);
                    break;
            }
        }
    }


    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        Log.w(TAG, "------------onBind()");
//        return myBinder;
        return messenger.getBinder();
    }

    @Override
    public void unbindService(ServiceConnection conn) {
        Log.w(TAG, "------------unbindService()");
        super.unbindService(conn);
    }

    @Override
    public boolean onUnbind(Intent intent) {
        Log.w(TAG, "------------onUnbind()");
        return super.onUnbind(intent);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Log.w(TAG, "------------onDestroy()");
    }
}
