package com.er.greentest;

import android.app.Notification;
import android.app.NotificationManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.RemoteInput;
import android.support.v4.app.TaskStackBuilder;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class HGActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_hg);
        String intentLast = getIntent().getStringExtra("intentLast");
        String intent = getIntent().getStringExtra("intent");
        Log.w("MyService", "HGActivity intentLast=" + intentLast + "  intent=" + intent);
        getMessageTextNotification(getIntent());
    }

    private void getMessageTextNotification(Intent intent) {
        Bundle resultsFromIntent = RemoteInput.getResultsFromIntent(intent);
        if (resultsFromIntent != null) {
            String reply_key = resultsFromIntent.getString("reply_KEY");
            Log.w("Notification", "----------reply_key=" + reply_key);

            CharSequence reply_key2 = resultsFromIntent.getCharSequence("reply_KEY");
            Log.w("Notification", "----------reply_key2=" + reply_key2);


            NotificationManager notificationManager = (NotificationManager) getSystemService(Context.NOTIFICATION_SERVICE);
            Notification notification = new NotificationCompat.Builder(this)
                    .setSmallIcon(R.mipmap.ic_launcher)
                    .setContentTitle("成功")
                    .setContentText("收到了您的回复")
                    .build();

            notificationManager.notify(300,notification);
        }

        TaskStackBuilder intents = TaskStackBuilder.create(this);

    }
}
