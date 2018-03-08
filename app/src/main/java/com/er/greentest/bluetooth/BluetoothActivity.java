package com.er.greentest.bluetooth;

import android.Manifest;
import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothGattService;
import android.bluetooth.BluetoothProfile;
import android.bluetooth.BluetoothServerSocket;
import android.bluetooth.BluetoothSocket;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.pm.PackageManager;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.er.greentest.R;

import java.io.IOException;
import java.util.Set;
import java.util.UUID;

public class BluetoothActivity extends AppCompatActivity {

    private static final int REQUEST_BLUETOOTH_ENABLE = 1010;
    private MyReceiver myReceiver;
    private BluetoothAdapter adapter;
    private MyThread myThread;
    private boolean run = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bluetooth);
        init();
        myReceiver = new MyReceiver();
        IntentFilter filter = new IntentFilter();
        filter.addAction(BluetoothAdapter.ACTION_STATE_CHANGED);//蓝牙状态改变 开、关等
        filter.addAction(BluetoothDevice.ACTION_FOUND);//扫描发现设备
        filter.addAction(BluetoothAdapter.ACTION_SCAN_MODE_CHANGED);// discoverable mode 扫描状态 可被扫描  不可被扫描
        registerReceiver(myReceiver, filter);

//        myThread = new MyThread();
//        myThread.start();
    }

    public void bluetoothClick(View v) {
        switch (v.getId()) {
            case R.id.scan:
                //扫描  sdk大于23的时候需要access_coarse_location权限
                if (Build.VERSION.SDK_INT >= 23) {
                    if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
                        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 10);
                    } else {
                        adapter.startDiscovery();
                    }
                }
                break;
            case R.id.stop:
                adapter.cancelDiscovery();
                break;
            case R.id.shutdown:
                run = false;
                break;
            case R.id.bleScan:
                startBleScan();
                break;
        }
    }

    private void init() {

//        获取adapter
//        BluetoothManager bm = (BluetoothManager) getSystemService(Context.BLUETOOTH_SERVICE);
//        BluetoothAdapter adapter = bm.getAdapter();

//        获取adapter
        adapter = BluetoothAdapter.getDefaultAdapter();


//        判断是否支持蓝牙
        if (adapter == null) {
            Log.w("bleScanCallback", "defaultAdapter null. not support bluetooth");
        } else {
            Log.w("bleScanCallback", "defaultAdapter not null. support bluetooth");
            if (!adapter.isEnabled()) {
                Log.w("bleScanCallback", "bleScanCallback is disable");

//                请求系统打开
//                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_ENABLE);
//                startActivityForResult(intent, REQUEST_BLUETOOTH_ENABLE);

//                强制打开
                boolean enable = adapter.enable();
                Log.w("bleScanCallback", "adapter.enable():"+enable);

//                使可被扫描，可直接是bluetooth直接处于enable状态
//                Intent intent = new Intent(BluetoothAdapter.ACTION_REQUEST_DISCOVERABLE);
//              tActivity(intent);
            } else {
                Log.w("bleScanCallback", "bleScanCallback is enable");
            }

            Set<BluetoothDevice> bondedDevices = adapter.getBondedDevices();
            for (BluetoothDevice bondedDevice : bondedDevices) {
                String address = bondedDevice.getAddress();
                String name = bondedDevice.getName();
                Log.w("bleScanCallback", "name:" + name + "  address:" + address);
            }
        }
    }

    private Handler handler = new Handler();
    private BluetoothAdapter.LeScanCallback bleScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {
            Log.w("bleScanCallback", "-------------------onLeScan:" + device.getName() + " " + device.getAddress());
        }
    };
    private void startBleScan(){
//        if (Build.VERSION.SDK_INT >= 23) {
//            if (ContextCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
//                ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.ACCESS_COARSE_LOCATION}, 10);
//                return;
//            }
//        }

        adapter .startLeScan(bleScanCallback);
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                adapter.stopLeScan(bleScanCallback);
            }
        }, 10 * 1000);
    }


    private BluetoothGattCallback bluetoothGattCallback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);

            switch (newState){
                case BluetoothProfile.STATE_CONNECTING:

                    break;
                case BluetoothProfile.STATE_CONNECTED:

                    break;
                case BluetoothProfile.STATE_DISCONNECTING:

                    break;
                case BluetoothProfile.STATE_DISCONNECTED:

                    break;
            }


        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
            BluetoothGattService service = gatt.getService(UUID.randomUUID());
            BluetoothGattCharacteristic characteristic = service.getCharacteristic(UUID.randomUUID());


        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicWrite(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicWrite(gatt, characteristic, status);
        }

        @Override
        public void onCharacteristicChanged(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic) {
            super.onCharacteristicChanged(gatt, characteristic);
        }

        @Override
        public void onDescriptorRead(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorRead(gatt, descriptor, status);
        }

        @Override
        public void onDescriptorWrite(BluetoothGatt gatt, BluetoothGattDescriptor descriptor, int status) {
            super.onDescriptorWrite(gatt, descriptor, status);
        }

        @Override
        public void onReliableWriteCompleted(BluetoothGatt gatt, int status) {
            super.onReliableWriteCompleted(gatt, status);
        }

        @Override
        public void onReadRemoteRssi(BluetoothGatt gatt, int rssi, int status) {
            super.onReadRemoteRssi(gatt, rssi, status);
        }

        @Override
        public void onMtuChanged(BluetoothGatt gatt, int mtu, int status) {
            super.onMtuChanged(gatt, mtu, status);
        }
    };

    private void connectBle(BluetoothDevice device){
        BluetoothGatt bluetoothGatt = device.connectGatt(this, false, bluetoothGattCallback);
        bluetoothGatt.connect();


        BluetoothGattCharacteristic characteristic = bluetoothGatt.getService(UUID.randomUUID()).getCharacteristic(UUID.randomUUID());
        bluetoothGatt.setCharacteristicNotification(characteristic,true);
        BluetoothGattDescriptor descriptor = characteristic.getDescriptor(UUID.randomUUID());
        descriptor.setValue(new byte[10]);
        bluetoothGatt.writeDescriptor(descriptor);

        bluetoothGatt.disconnect();
        bluetoothGatt.close();
        bluetoothGatt = null;
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_BLUETOOTH_ENABLE) {
            if (resultCode == RESULT_OK) {
                Log.w("bleScanCallback", "result----bluetooth is enable");
            } else {
                Log.w("bleScanCallback", "result----bluetooth is disable");
            }
        }
    }

    public class MyReceiver extends BroadcastReceiver {

        @Override
        public void onReceive(Context context, Intent intent) {
            Log.w("bleScanCallback", "--------------------onReceive");
            String action = intent.getAction();
            if (action.equals(BluetoothAdapter.ACTION_STATE_CHANGED)) {
                int state = intent.getIntExtra(BluetoothAdapter.EXTRA_STATE, BluetoothAdapter.STATE_OFF);
                Log.w("bleScanCallback", "broadcast receiver----state change:" + state);
            } else if (BluetoothDevice.ACTION_FOUND.equals(action)) {
                BluetoothDevice device = intent.getParcelableExtra(BluetoothDevice.EXTRA_DEVICE);
                Log.w("bleScanCallback", "broadcast receiver----device:" + device.getName());
            } else if (BluetoothAdapter.ACTION_SCAN_MODE_CHANGED.equals(action)) {
                int scan_mode = intent.getIntExtra(BluetoothAdapter.EXTRA_SCAN_MODE, BluetoothAdapter.SCAN_MODE_CONNECTABLE);
                Log.w("bleScanCallback", "broadcast receiver----SCAN MODE CHANGE:" + scan_mode);
            }
        }

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(myReceiver);
    }

    private class MyThread extends Thread {

        private BluetoothServerSocket mBSS = null;

        public MyThread() {
            BluetoothServerSocket temp = null;
            try {
                UUID uuid = UUID.randomUUID();
                uuid.toString();
                temp = adapter.listenUsingRfcommWithServiceRecord("dd", uuid);
            } catch (IOException e) {
                Log.w("bleScanCallback", "Socket's listen() method failed", e);
                e.printStackTrace();
            }
            mBSS = temp;
        }

        @Override
        public void run() {
            BluetoothSocket bs = null;
            while (run) {
                try {
                    bs = mBSS.accept();
                    Log.w("bleScanCallback", "Socket's accept() method:" + bs.getRemoteDevice().getName() + bs.getRemoteDevice().getAddress());
                } catch (IOException e) {
                    Log.w("bleScanCallback", "Socket's accept() method failed", e);
                    e.printStackTrace();
                }

                if (bs != null) {
                    try {
                        mBSS.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;
                }
            }
        }
    }

    private class ClientThread extends Thread {

        private BluetoothSocket socket;
        private BluetoothDevice device;

        public ClientThread(BluetoothDevice device) {
            BluetoothSocket temp = null;
            this.device = device;
            try {
                temp = device.createRfcommSocketToServiceRecord(UUID.randomUUID());
            } catch (IOException e) {
                e.printStackTrace();
            }
            socket = temp;
        }

        @Override
        public void run() {
            //在连接之前取消扫描
            adapter.cancelDiscovery();

            try {
                socket.connect();
            } catch (IOException e) {
                e.printStackTrace();
                try {
                    socket.close();
                } catch (IOException e1) {
                    e1.printStackTrace();
                }
            }

        }
    }

}
