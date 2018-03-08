package com.er.greentest.auto;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.widget.Button;

import com.er.greentest.R;

import java.lang.ref.WeakReference;

public class AutoActivity extends AppCompatActivity {

    private Button btn;
    private Button btnB;
    private Button btnC;
    private Handler handler;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_auto);
        handler = new MyHandler(this);

        btn = (Button) findViewById(R.id.btn);
        btnB = (Button) findViewById(R.id.btnB);
        btnC = (Button) findViewById(R.id.btnC);
        handler.sendEmptyMessageDelayed(1, 2000);
    }

    private  static class MyHandler extends Handler{
        private final WeakReference<AutoActivity> weakReference;
        MyHandler(AutoActivity activity) {
            weakReference = new WeakReference<>(activity);
        }

        @Override
        public void handleMessage(Message msg) {
            super.handleMessage(msg);
            AutoActivity autoActivity = weakReference.get();
            new Auto().autoClick(autoActivity.btn,autoActivity.btnB,autoActivity.btnC);
        }
    }
}
