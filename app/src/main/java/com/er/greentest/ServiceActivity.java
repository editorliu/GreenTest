package com.er.greentest;

import android.app.Service;
import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.os.Message;
import android.os.Messenger;
import android.os.RemoteException;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.er.greentest.service.MyService;

public class ServiceActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_service);
    }

    public void ServiceClick(View view) {
        switch (view.getId()) {
            case R.id.button:
                Intent intent = new Intent(this, MyService.class);
                startService(intent);
                break;
            case R.id.button2:
                Intent stopIntent = new Intent(this, MyService.class);
                stopService(stopIntent);
                break;
            case R.id.button3://bindService
                bindService(new Intent(this, MyService.class), conn, Service.BIND_AUTO_CREATE);
                break;
            case R.id.button4:
//                if (myService != null) {
//                    myService = null;
//                    unbindService(conn);
//                }

                unbindService(conn);
                break;
            case R.id.button6:
                if (myService != null) {
                    Log.w(MyService.TAG, "------------myService.getCount()="+myService.getCount());
                }
                break;
            case R.id.button7:
                if (messenger != null) {
                    Log.w(MyService.TAG, "------------sayHello");
                    sayHello();
                }
                break;
        }
    }

    private MyService myService;
    private Messenger messenger;
    private ServiceConnection conn = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName name, IBinder service) {
            Log.w(MyService.TAG, "------------ServiceConnection   onServiceConnected()");
//            MyService.MyBinder binder = (MyService.MyBinder) service;
//            myService = binder.getService();

            messenger = new Messenger(service);
        }

        @Override
        public void onServiceDisconnected(ComponentName name) {
            Log.w(MyService.TAG, "------------ServiceConnection   onServiceDisconnected()");
//            myService = null;
            messenger = null;
        }
    };

    private void sayHello() {
        if (messenger != null) {
            Message message = Message.obtain();
            message.what = MyService.MSG_SAY_HELLO;
            message.arg1 = 100;
            message.arg2 = 1010;

            try {
                messenger.send(message);
            } catch (RemoteException e) {
                e.printStackTrace();
            }
        }
    }


}
