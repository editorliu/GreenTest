package com.er.greentest.record;

import android.media.AudioFormat;
import android.media.AudioRecord;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.er.greentest.R;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class AudioActivity extends AppCompatActivity {
    private int sampleRateInHz = 44100;//采样率
    private int channelConfig = AudioFormat.CHANNEL_IN_MONO;//采集通道数
    private int audioFormat = AudioFormat.ENCODING_PCM_16BIT;//位数
    private int minBufferSize;//彩金缓冲区大小
    private AudioRecord audioRecord;
    private boolean isRecording = true;

    private byte[] buffer;
    private String filePath;

    private boolean isInit = true;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_audio);
        init();
    }

    private void init() {
        //初始化record
        minBufferSize = AudioRecord.getMinBufferSize(sampleRateInHz, channelConfig, audioFormat);
        audioRecord = new AudioRecord(MediaRecorder.AudioSource.MIC, sampleRateInHz, channelConfig, audioFormat, minBufferSize);
        //缓存数组
        buffer = new byte[minBufferSize];

        new Thread(new WriteRunnable()).start();
    }

    public void onAudioActivityClick(View view) {
        switch (view.getId()) {
            case R.id.startAudio:
                start();
                break;
            case R.id.stopAudio:
                stop();
                break;
        }
    }

    private void start() {
        isInit = false;
        isRecording = true;
        audioRecord.startRecording();
    }

    private void stop() {
        isRecording = false;
        audioRecord.stop();
    }

    private class WriteRunnable implements Runnable {

        @Override
        public void run() {
            writeToFile();
        }
    }

    private void writeToFile() {
        //文件存储路径
        filePath = Environment.getExternalStorageDirectory().getAbsolutePath() + File.separator + new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date()) + ".raw";
        Log.w("RE", "路径：" + filePath);
        int read = 0;
        File file = new File(filePath);
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(file);
            while (isRecording) {
                if (null != audioRecord) {
                    read = audioRecord.read(buffer, 0, minBufferSize);
                    if (read == AudioRecord.ERROR_INVALID_OPERATION || read == AudioRecord.ERROR_BAD_VALUE) {
                        continue;
                    }
                    if (read>0) {
                        fos.write(buffer, 0, read);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            Log.w("RE", "关闭流");
            if (fos != null) {
                try {
                    fos.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
