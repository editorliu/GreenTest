package com.er.greentest;

import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.RemoteViews;

public class NotificationActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        String intentLast = getIntent().getStringExtra("intentLast");
        String intent = getIntent().getStringExtra("intent");
        Log.w("MyService", "NotificationActivity intentLast=" + intentLast + "  intent=" + intent);
    }


    public void onMyNotificationClick(View view) {
        switch (view.getId()) {
            case R.id.sendNotify:
                sendNotification();
                break;
            case R.id.cancelNotification:
                cancel();
                break;
            case R.id.notifyAction:
                issueActionNotification();
                break;
        }
    }

    private int count = 0;
    private void sendNotification() {
        Log.w("Notification", "---------sendNotification count:"+count);

        //创建Notification对象
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //创建Notification的Channel
//        NotificationChannel notificationChannel = new NotificationChannel("myChannel","这是一个channel",NotificationManager.IMPORTANCE_LOW);
//        notificationChannel.setDescription("这是我的一个NotificationChannel");
//        notificationChannel.setShowBadge(true);
//
//        notificationManager.createNotificationChannel(notificationChannel);

        //创建点击Notification的PendingIntent对象
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1000, new Intent(this, HGActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);

        //创建自定义视图
        RemoteViews smallView = new RemoteViews(getPackageName(), R.layout.remote_view_small);
        RemoteViews bigView = new RemoteViews(getPackageName(), R.layout.remote_view_big);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("contentTitle")
                .setContentText("contextText"+ ((int) (Math.random() * 10)))

                .setContentIntent(pendingIntent)
                .setAutoCancel(true)

                .setPriority(NotificationCompat.PRIORITY_MAX)
                .setVibrate(new long[]{100})
//                .setFullScreenIntent(pendingIntent,true)

//                .setCustomContentView(smallView)
//                .setCustomBigContentView(bigView)

                .setNumber(++count);//不显示，不知道怎么回事儿，呵呵^^

        Notification notification = builder.build();
        notificationManager.notify("MyNotification", 100, notification);
    }

    private void cancel() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
        notificationManager.cancel("MyNotification", 100);
    }

    private void issueActionNotification() {
        NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);

        //创建一个RemoteInput对象
        RemoteInput replyRemoteInput = new RemoteInput.Builder("reply_KEY")
                .setLabel("回复")
                .build();

        PendingIntent pendingIntent = PendingIntent.getActivity(this, 1222, new Intent(this, HGActivity.class), PendingIntent.FLAG_UPDATE_CURRENT);
        NotificationCompat.Action action = new NotificationCompat.Action.Builder(R.mipmap.ic_launcher, "回复Title", pendingIntent)
                .addRemoteInput(replyRemoteInput)
                .build();


        Notification notification = new NotificationCompat.Builder(this)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentTitle("附带Action")
                .setContentText("这是一个附带Action的通知，可直接在再通知的窗口中回复")
                .setAutoCancel(true)
                .addAction(action)
                .build();

        notificationManager.notify(300,notification);

    }

}
