package com.er.greentest;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.text.SimpleDateFormat;
import java.util.Date;

public class TakePictureActivity extends AppCompatActivity {
    private static final int CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE = 100;
    private static final int CAPTURE_VIDEO_ACTIVITY_REQUEST_CODE = 200;

    private ImageView img;
    private Uri fileUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_take_picture);
        img = (ImageView) findViewById(R.id.img);

    }

    public static final int PER_CAMERA = 100;

    public void onTPClick(View view) {
        switch (view.getId()) {
            case R.id.takePic:
                if (ContextCompat.checkSelfPermission(this, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED || ContextCompat.checkSelfPermission(this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CAMERA, Manifest.permission.CALL_PHONE, Manifest.permission.WRITE_EXTERNAL_STORAGE}, PER_CAMERA);
                } else {
                    takePhoto();
                }

                break;
            case R.id.pickPic:
                File outputMediaStoreFile = getOutputMediaStoreFile(MEDIA_TYPE_IMAGE);
                fileUri = Uri.fromFile(outputMediaStoreFile);
                pickPhoto(fileUri);
                break;
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == PER_CAMERA) {
            int count = 0;
            int length = permissions.length;
            for (int i = 0; i < length; i++) {
                if (grantResults[i] == PackageManager.PERMISSION_GRANTED) {
                    count++;
                }
            }
            if (count == length) {
                //申请成功，可以拍照
                takePhoto();
            } else {
                Toast.makeText(this, "CAMERA PERMISSION DENIED", Toast.LENGTH_SHORT).show();
            }
        }
    }

    public static final int SELECT_PIC_BY_PICK_PHOTO = 3020;

    private void pickPhoto(Uri fileUri) {
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("image/*");
        intent.putExtra("crop", true);

        //裁剪的宽高的比例为1:1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        //输出图片的宽高均为150
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);

        intent.putExtra("scale", true);

        //裁剪之后的数据是通过Intent返回
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        startActivityForResult(intent, SELECT_PIC_BY_PICK_PHOTO);
    }

    private void takePhoto() {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        File outputMediaStoreFile = getOutputMediaStoreFile(MEDIA_TYPE_IMAGE);
        if (outputMediaStoreFile != null) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                fileUri = FileProvider.getUriForFile(this, "com.er.gt.provider", outputMediaStoreFile);
                intent.setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION);
                intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
            } else {
                fileUri = Uri.fromFile(outputMediaStoreFile);
            }
            intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);
            startActivityForResult(intent, CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE);
        } else {
            Log.w("TP", "outputMediaStoreFile 文件为空");
        }
    }

    public static final int MEDIA_TYPE_IMAGE = 1;
    public static final int MEDIA_TYPE_VIDEO = 2;

    @Nullable
    private File getOutputMediaStoreFile(int type) {
        File outFileDir = null;
//        outFileDir = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES), "GreenPic");
        outFileDir = new File(getExternalFilesDir(Environment.DIRECTORY_PICTURES), "GreenPic");
        if (!outFileDir.exists()) {
            if (!outFileDir.mkdirs()) {
                Log.w("TP", "创建目录失败");
                return null;
            }
        }

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmssSSS");
        if (type == MEDIA_TYPE_IMAGE) {
            return new File(outFileDir, "IMG_" + sdf.format(new Date()) + ".jpg");
        } else if (type == MEDIA_TYPE_VIDEO) {
            return new File(outFileDir, "VID_" + sdf.format(new Date()) + ".mp4");
        } else {
            return null;
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        Log.w("TP", "TakePictureActivity onActivityResult");
        if (CAPTURE_IMAGE_ACTIVITY_REQUEST_CODE == requestCode) {
            if (RESULT_OK == resultCode) {
                //data 为空 表明 文件被保存在指定的位置
                // data 不为空 表明：文件默认保存，返回一个thumbnail
                if (data != null) {
                    Log.w("TP", "data不为空，文件保存路径：" + data.getData());
//                    if (fileUri != null) {
//                        setImgFromUri(fileUri);
//                    } else if (data.hasExtra("data")) {
//                        Bitmap thumbnail = data.getParcelableExtra("data");
//                        img.setImageBitmap(thumbnail);
//                    }

                    if (data.hasExtra("data")) {//没有设置默认路径
//                        Bitmap thumbnail = data.getParcelableExtra("data");
//                        img.setImageBitmap(thumbnail);
                        Bitmap thumbnail = data.getParcelableExtra("data");
                        File outputMediaStoreFile = getOutputMediaStoreFile(MEDIA_TYPE_IMAGE);
                        try {
                            FileOutputStream fileOutputStream = new FileOutputStream(outputMediaStoreFile);
                            thumbnail.compress(Bitmap.CompressFormat.JPEG, 80, fileOutputStream);
                            fileUri = Uri.fromFile(outputMediaStoreFile);
                            startZoomPhoto(fileUri);
                        } catch (FileNotFoundException e) {
                            e.printStackTrace();
                        }

                    } else {
                        Log.w("TP", "data 不包含extra-“data”");
                        if (fileUri != null) {
//                            setImgFromUri(fileUri);
                            startZoomPhoto(fileUri);
                        }
                    }
                } else {
                    Log.w("TP", "data为空，文件保存路径：" + (fileUri != null ? fileUri.getPath() : null));
//                    setImgFi(fileUri);
                    startZoomPhoto(fileUri);
                }
            } else if (resultCode == RESULT_CANCELED) {
                Log.w("TP", "onActivityResult RESULT_CANCELED");
            }
        } else if (CROP_CODE == requestCode) {
            if (fileUri != null) {
                setImgFromUri(fileUri);
            }
        } else if (SELECT_PIC_BY_PICK_PHOTO == requestCode) {
//            if (data == null) {
//                Toast.makeText(getApplicationContext(), "选择图片文件出错1", Toast.LENGTH_SHORT).show();
//                return;
//            }
//            fileUri = data.getData();
//            if (fileUri == null) {
//                Toast.makeText(getApplicationContext(), "选择图片文件出错2", Toast.LENGTH_SHORT).show();
//                return;
//            }
            setImgFromUri(fileUri);
        }
    }

    public static final int CROP_CODE = 1002;

    private void startZoomPhoto(Uri fileUri) {
        Intent intent = new Intent("com.android.camera.action.CROP");
        intent.setDataAndType(fileUri, "image/*");
        intent.putExtra("crop", "true");

        //裁剪的宽高的比例为1:1
        intent.putExtra("aspectX", 1);
        intent.putExtra("aspectY", 1);

        //输出图片的宽高均为150
        intent.putExtra("outputX", 150);
        intent.putExtra("outputY", 150);

        intent.putExtra("scale", true);

        //裁剪之后的数据是通过Intent返回
        intent.putExtra("return-data", false);
        intent.putExtra(MediaStore.EXTRA_OUTPUT, fileUri);

        startActivityForResult(intent, CROP_CODE);
    }

    private void setImgFromUri(Uri fileUri) {
        int width = img.getWidth();
        int height = img.getHeight();

        Environment.getExternalStorageDirectory();
        Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES);
        getExternalCacheDir();
        getExternalFilesDir(Environment.DIRECTORY_PICTURES);

        getCacheDir();
        getFilesDir();


        BitmapFactory.Options factoryOptions = new BitmapFactory.Options();

        factoryOptions.inJustDecodeBounds = true;
        BitmapFactory.decodeFile(fileUri.getPath(), factoryOptions);

        int imageWidth = factoryOptions.outWidth;
        int imageHeight = factoryOptions.outHeight;

        // Determine how much to scale down the image
        int scaleFactor = Math.min(imageWidth / width, imageHeight
                / height);

        // Decode the image file into a Bitmap sized to fill the
        // ViewUI
        factoryOptions.inJustDecodeBounds = false;
        factoryOptions.inSampleSize = scaleFactor;
        factoryOptions.inPurgeable = true;

        Bitmap bitmap = BitmapFactory.decodeFile(fileUri.getPath(),
                factoryOptions);

        img.setImageBitmap(bitmap);
    }

    // data不为空，文件保存路径：null  BUZHIDING OUT
}

