package com.er.greentest.service;

import android.app.Service;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Binder;
import android.os.Handler;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.support.annotation.Nullable;
import android.util.Log;

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
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                while (true) {
                    try {
                        Thread.sleep(1000);
                        count++;
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        });
        thread.start();
    }

    public int getCount() {
        return count;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        Log.w(TAG, "------------onStartCommand()");
        return super.onStartCommand(intent, flags, startId);
    }

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
