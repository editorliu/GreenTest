package com.er.greentest.nfc;

import android.app.PendingIntent;
import android.content.Intent;
import android.content.IntentFilter;
import android.nfc.NfcAdapter;
import android.nfc.Tag;
import android.nfc.tech.IsoDep;
import android.nfc.tech.MifareClassic;
import android.nfc.tech.MifareUltralight;
import android.nfc.tech.Ndef;
import android.nfc.tech.NfcA;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.er.greentest.R;

import java.io.IOException;

public class NFCForegroundActivity extends AppCompatActivity {

    private NfcAdapter defaultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfcforeground);
        defaultAdapter = NfcAdapter.getDefaultAdapter(this);
//        defaultAdapter.enableForegroundDispatch(this,pending,intentFilter,techlist);
    }

    @Override
    protected void onResume() {
        super.onResume();
        PendingIntent pending = PendingIntent.getActivity(this, 1200, new Intent(this, getClass()).addFlags(Intent.FLAG_ACTIVITY_SINGLE_TOP), PendingIntent.FLAG_UPDATE_CURRENT);
        IntentFilter intentFilter [] = new IntentFilter[]{new IntentFilter(NfcAdapter.ACTION_TECH_DISCOVERED)};
        String[][] techLists = new String[][]{new String[]{IsoDep.class.getName()}};
        defaultAdapter.enableForegroundDispatch(this,pending,intentFilter,techLists);
    }

    @Override
    protected void onPause() {
        super.onPause();
        defaultAdapter.disableForegroundDispatch(this);
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.w("foreground", "==================onNewIntent");
        Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);


        MifareClassic mifareClassic = MifareClassic.get(tag);
        Ndef ndef = Ndef.get(tag);
        NfcA nfcA = NfcA.get(tag);
        MifareUltralight mifareUltralight = MifareUltralight.get(tag);



        if (tag != null) {
            MyNFC myNFC = null;
            try {
                myNFC = new MyNFC(IsoDep.get(tag));
                myNFC.send();
            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }
}
