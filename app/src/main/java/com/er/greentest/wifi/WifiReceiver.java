package com.er.greentest.wifi;

import android.app.Activity;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.wifi.WifiManager;
import android.net.wifi.p2p.WifiP2pConfig;
import android.net.wifi.p2p.WifiP2pDevice;
import android.net.wifi.p2p.WifiP2pDeviceList;
import android.net.wifi.p2p.WifiP2pManager;
import android.os.AsyncTask;
import android.util.Log;

import java.util.Collection;
import java.util.Iterator;

/**
 * Created by THTF on 2017/11/7.
 * Desc:
 */

public class WifiReceiver extends BroadcastReceiver {
    private WifiP2pManager wifiP2pManager;
    private WifiP2pManager.Channel channel;
    private Activity activity;


    public WifiReceiver(WifiP2pManager wifiP2pManager, WifiP2pManager.Channel channel, Activity activity) {

        this.wifiP2pManager = wifiP2pManager;
        this.channel = channel;
        this.activity = activity;
    }

    @Override
    public void onReceive(Context context, Intent intent) {
        String action = intent.getAction();
        if (WifiP2pManager.WIFI_P2P_STATE_CHANGED_ACTION.equals(action)) {
            int enable = intent.getIntExtra(WifiP2pManager.EXTRA_WIFI_STATE, -1);
            if (enable == WifiManager.WIFI_STATE_ENABLED) {
                Log.d("WIFI", "WIFI STATE ENABLE");
            } else {
                Log.d("WIFI", "WIFI STATE DISABLE");
            }
        } else if (WifiP2pManager.WIFI_P2P_PEERS_CHANGED_ACTION.equals(action)) {
            Log.d("WIFI", "WIFI_P2P_PEERS_CHANGED_ACTION");
            wifiP2pManager.requestPeers(channel, new WifiP2pManager.PeerListListener() {
                @Override
                public void onPeersAvailable(WifiP2pDeviceList peers) {
                    Collection<WifiP2pDevice> deviceList = peers.getDeviceList();
                    Iterator<WifiP2pDevice> iterator = deviceList.iterator();
                    while (iterator.hasNext()) {
                        WifiP2pDevice device = iterator.next();
                        WifiP2pConfig wifiP2pConfig = new WifiP2pConfig();
                        wifiP2pConfig.deviceAddress = device.deviceAddress;
                        wifiP2pManager.connect(channel, wifiP2pConfig, new WifiP2pManager.ActionListener() {
                            @Override
                            public void onSuccess() {
                                Log.d("WIFI", "connect onSuccess");
                            }

                            @Override
                            public void onFailure(int reason) {
                                Log.d("WIFI", "connect onFailure");
                            }
                        });
                    }


                }
            });
        }

        AsyncTask<String, String, String> asyncTask = new AsyncTask<String, String, String>() {
            @Override
            protected String doInBackground(String... params) {
                return null;
            }
        };
        asyncTask.execute("ddd");
    }
}
