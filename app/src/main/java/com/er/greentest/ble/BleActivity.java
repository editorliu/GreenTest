package com.er.greentest.ble;

import android.bluetooth.BluetoothAdapter;
import android.bluetooth.BluetoothDevice;
import android.bluetooth.BluetoothGatt;
import android.bluetooth.BluetoothGattCallback;
import android.bluetooth.BluetoothGattCharacteristic;
import android.bluetooth.BluetoothGattDescriptor;
import android.bluetooth.BluetoothProfile;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ScrollView;
import android.widget.TextView;

import com.er.greentest.R;

public class BleActivity extends AppCompatActivity {

    private Handler handler = new Handler();
    private TextView console;
    private StringBuilder log;

    private BluetoothAdapter defaultAdapter;

    private ScrollView scrollView;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ble);
        scrollView = ((ScrollView) findViewById(R.id.scrollView));
        console = (TextView) findViewById(R.id.console);

        log = new StringBuilder();
        defaultAdapter = BluetoothAdapter.getDefaultAdapter();


    }

    private BluetoothAdapter.LeScanCallback leScanCallback = new BluetoothAdapter.LeScanCallback() {
        @Override
        public void onLeScan(BluetoothDevice device, int rssi, byte[] scanRecord) {

            double power = (Math.abs(rssi)-59)/(10*2.0);
            int distance = ((int) Math.pow(10, power));

            String s = device.getName() + "  " + device.getAddress()+" " + distance;
            Log.w("ble", "---------------scan device:" + s+"   Thread.currentThread():"+Thread.currentThread());
            console.append(s+"\n");
            scrollView.fullScroll(View.FOCUS_DOWN);
        }
    };

    public void onBLEClick(View v) {
        switch (v.getId()) {
            case R.id.enable:
                if (!defaultAdapter.isEnabled()) {
                    Log.w("ble","---------------defaultAdapter is disable!");
                    defaultAdapter.enable();
                } else {
                    Log.w("ble", "---------------defaultAdapter is enable!");
                }
                break;
            case R.id.bleScan:
                defaultAdapter.startLeScan(leScanCallback);
                handler.postDelayed(new Runnable() {
                    @Override
                    public void run() {
                        defaultAdapter.stopLeScan(leScanCallback);
                    }
                }, 5 * 1000);
                break;
        }
    }


    private BluetoothGattCallback callback = new BluetoothGattCallback() {
        @Override
        public void onConnectionStateChange(BluetoothGatt gatt, int status, int newState) {
            super.onConnectionStateChange(gatt, status, newState);
            if (status == BluetoothGatt.GATT_SUCCESS) {

            }

            if(newState == BluetoothProfile.STATE_CONNECTED){

            }

        }

        @Override
        public void onServicesDiscovered(BluetoothGatt gatt, int status) {
            super.onServicesDiscovered(gatt, status);
        }

        @Override
        public void onCharacteristicRead(BluetoothGatt gatt, BluetoothGattCharacteristic characteristic, int status) {
            super.onCharacteristicRead(gatt, characteristic, status);


            int properties = characteristic.getProperties();
            Integer intValue = characteristic.getIntValue(BluetoothGattCharacteristic.FORMAT_UINT16, 1);


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
    private void connectDevice(BluetoothDevice device) {
        BluetoothGatt bluetoothGatt = device.connectGatt(this, false, callback);
        bluetoothGatt.connect();
        bluetoothGatt.close();
    }





}
