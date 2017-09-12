package com.er.greentest.record;

import android.Manifest;
import android.app.Activity;
import android.content.pm.PackageManager;
import android.media.MediaRecorder;
import android.os.Bundle;
import android.os.Environment;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;

import com.er.greentest.R;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class RecordActivity extends AppCompatActivity {
    private Handler d;
    Activity a;

    private MediaRecorder mediaRecorder;
    private boolean isRecording = false;
    private File file;//保存音频的文件
    private static final int REQUEST_CODE = 2011;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_record);
        grantPermission();
    }

    public void onRecordActivityClick(View view) {
        switch (view.getId()) {
            case R.id.startRecordAudio:
                startRecord();
            break;
            case R.id.stopRecordAudio:
                stopRecord();
                break;
        }
    }


    private void grantPermission(){
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.RECORD_AUDIO) != PackageManager.PERMISSION_GRANTED) {
            Log.w("RA", "没有权限");
            ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.RECORD_AUDIO}, REQUEST_CODE);
        } else {
            Log.w("RA", "有权限");
            initRecord();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == REQUEST_CODE) {
            if (grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                Log.w("RA", "获取到了权限");
                initRecord();
            } else {
                Log.w("RA", "没有获取到权限");
//                grantPermission();
            }
        }
    }

    private void initRecord() {
        String fileName = new SimpleDateFormat("yyyyMMddHHmmssSSS").format(new Date());
        String basePath = Environment.getExternalStorageDirectory().getAbsolutePath();
        file = new File(basePath + File.separator + fileName + ".mp3");

        mediaRecorder = new MediaRecorder();
        //设置音频源
        mediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);
        //设置输出转换格式
        mediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.AMR_NB);
        //设置编码格式
        mediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);
        //存储文件
        mediaRecorder.setOutputFile(file.getAbsolutePath());
        //准备
        try {
            mediaRecorder.prepare();
        } catch (IOException e) {
            e.printStackTrace();
        }


    }

    private void startRecord() {
        if (mediaRecorder != null && !isRecording) {
            mediaRecorder.start();
            isRecording = true;
            mediaRecorder.setOnErrorListener(new MediaRecorder.OnErrorListener() {
                @Override
                public void onError(MediaRecorder mr, int what, int extra) {
                    mediaRecorder.stop();
                    mediaRecorder.release();
                    mediaRecorder = null;
                    isRecording = false;
                }
            });
        }
    }

    private void stopRecord() {
        if (mediaRecorder != null && isRecording) {
            mediaRecorder.stop();
            mediaRecorder.release();
            mediaRecorder = null;
            isRecording = false;
        }
    }
}
