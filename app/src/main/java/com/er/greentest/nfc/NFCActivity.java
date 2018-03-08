package com.er.greentest.nfc;

import android.content.Intent;
import android.nfc.FormatException;
import android.nfc.NdefMessage;
import android.nfc.NdefRecord;
import android.nfc.NfcAdapter;
import android.nfc.NfcEvent;
import android.nfc.Tag;
import android.nfc.cardemulation.HostApduService;
import android.nfc.tech.Ndef;
import android.nfc.tech.NdefFormatable;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.er.greentest.R;

import java.io.IOException;
import java.nio.charset.Charset;

public class NFCActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_nfc);
//        NdefMessage
        NdefRecord ndefRecord = new NdefRecord(NdefRecord.TNF_WELL_KNOWN, null, null, null);


//        NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(this);
//        if (defaultAdapter.isEnabled()) {
//            defaultAdapter.enable
//        }

        Intent intent = getIntent();
        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
            Log.w("nfc", "---------------------nfc_tech_filter");
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
            String[] techList = tag.getTechList();
            for (String s : techList) {
                Log.w("nfc", "tech list:" + s);
            }

            if (tag == null) {
                return;
            }

            NdefRecord applicationRecord = NdefRecord.createApplicationRecord("com.android.mms");
            NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]{applicationRecord});


            Ndef ndef = Ndef.get(tag);
            if (ndef != null) {
//                ndef.connect();
                boolean writable = ndef.isWritable();
                int maxSize = ndef.getMaxSize();
                Log.w("nfc", "isWritable():" + writable + " maxSize:" + maxSize);

            } else {//未格式化  新的nfc卡片  或者没有分区
                NdefFormatable ndefFormatable = NdefFormatable.get(tag);
                if (ndefFormatable == null) {
                    Log.w("nfc", "ndefFormatable null");
                } else {
                    Log.w("nfc", "ndefFormatable not null");
                }
            }
        }
    }

    @Override
    protected void onNewIntent(Intent intent) {
        super.onNewIntent(intent);
        Log.w("nfc", "-----------onNewIntent");
//        if (NfcAdapter.ACTION_TECH_DISCOVERED.equals(intent.getAction())) {
//            Log.w("nfc", "---------------------nfc_tech_filter");
//            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);
//            String[] techList = tag.getTechList();
//            for (String s : techList) {
//                Log.w("nfc", "tech list:" + s);
//            }
//        }
    }


    //创建各种类型nfc的ndef类型数据
    private void NDEF() {

//        NdefRecord.TNF_ABSOLUTE_URI
        byte[] type = "http://news.baidu.com/guonei".getBytes(Charset.forName("utf-8"));
        NdefRecord ndefRecord = new NdefRecord(NdefRecord.TNF_ABSOLUTE_URI, type, new byte[0], new byte[0]);

//        NdefRecord.TNF_MIME_MEDIA
        byte[] mimeData = "hello nfc".getBytes(Charset.forName("utf-8"));
        NdefRecord.createMime("application/vnd.com.example.android.beam", mimeData);

//        NdefRecord.TNF_EXTERNAL_TYPE
        String domain = "com.er.domain";
        String externalType = "externalType123";
        byte[] data = {1, 2, 3, 4, 5};
//        vnd.android.nfc://ext/<domain>:<service>.
        NdefRecord externalRecord = NdefRecord.createExternal(domain, externalType, data);


//        new NdefRecord(NdefRecord.TNF_WELL_KNOWN,NdefRecord.RTD_TEXT,new byte[0],);

    }

    private void read(Intent intent) {
        if (intent != null && intent.getAction().equals(NfcAdapter.ACTION_NDEF_DISCOVERED)) {
            Parcelable[] parcelableArrayExtra = intent.getParcelableArrayExtra(NfcAdapter.EXTRA_NDEF_MESSAGES);
            if (parcelableArrayExtra != null) {
                NdefMessage[] messages = new NdefMessage[parcelableArrayExtra.length];
                for (int i = 0; i < parcelableArrayExtra.length; i++) {
                    messages[i] = (NdefMessage) parcelableArrayExtra[i];
                    NdefMessage message = messages[i];
                    message.getRecords()[0].getPayload();
                }
            }
        }
    }

    private void write(Intent intent) {

        if (intent != null) {
            Tag tag = intent.getParcelableExtra(NfcAdapter.EXTRA_TAG);

            NdefRecord external = NdefRecord.createExternal("domain", "service", "hell".getBytes());
            NdefMessage ndefMessage = new NdefMessage(new NdefRecord[]{external});
            Ndef ndef = Ndef.get(tag);
            if (ndef != null) {
                try {
                    ndef.connect();
                    ndef.writeNdefMessage(ndefMessage);
                } catch (FormatException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                    Log.w("dd", "ddd");
                } finally {
                    try {
                        ndef.close();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            } else {
                NdefFormatable ndefFormatable = NdefFormatable.get(tag);
                if (ndefFormatable != null) {
                    try {
                        ndefFormatable.connect();
                        ndefFormatable.format(ndefMessage);
                    } catch (IOException e) {
                        e.printStackTrace();
                    } catch (FormatException e) {
                        e.printStackTrace();
                        Log.w("dd", "#####ddd");
                    }

                }

            }
        }
    }


    /**
     * p2p mode
     */
    private void p2p() {
        NfcAdapter defaultAdapter = NfcAdapter.getDefaultAdapter(this);

//        defaultAdapter.setNdefPushMessage();

        final NdefRecord mimeRecord = NdefRecord.createMime("application/vnd.com.example.android.beam", "hello android".getBytes());
        defaultAdapter.setNdefPushMessageCallback(new NfcAdapter.CreateNdefMessageCallback() {
            @Override
            public NdefMessage createNdefMessage(NfcEvent event) {
                return new NdefMessage(mimeRecord);
            }
        }, this);


        defaultAdapter.setOnNdefPushCompleteCallback(new NfcAdapter.OnNdefPushCompleteCallback() {
            @Override
            public void onNdefPushComplete(NfcEvent event) {
                Log.v("p2p NFC", "onNdefPushComplete");

            }
        }, this);
    }


    class MyService extends HostApduService {

        @Override
        public byte[] processCommandApdu(byte[] commandApdu, Bundle extras) {
            return new byte[0];
        }

        @Override
        public void onDeactivated(int reason) {

        }
    }

}
