package com.er.greentest.nfc;

import android.nfc.tech.IsoDep;
import android.util.Log;

import java.io.IOException;

/**
 * Created by THTF on 2017/11/1.
 * Desc:
 */

public class MyNFC {
    // 获取随机数的APDU命令
    private static final byte[] GET_RANDOM = {0x00, (byte)0x84, 0x00, 0x00, 0x08};

    private final IsoDep tag;

    public MyNFC(IsoDep tag)throws IOException  {
        this.tag = tag;
        tag.setTimeout(5 * 1000);
        tag.connect();
    }

    public String send() throws IOException {
        byte[] resp = tag.transceive(GET_RANDOM);
        String s = new String(resp,"ISO-8859-1");
        Log.w("foreground", "==================tag.transceive():"+s);
        return s;
    }
}
