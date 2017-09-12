package com.er.greentest.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Camera;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;
import android.view.animation.DecelerateInterpolator;

/**
 * Created by Administrator on 2017/8/30.
 * Description：
 * Version：
 */

public class LearnView extends View {

    private final Paint paint;
    private Path path;
    private Camera camera;
    private Path path1;

    private RectF bigRect;
    private RectF smallRect;
    private Path rightPath;
    private Path downPath;
    private Path leftPath;
    private Path topPath;

    private int sweepAngle = 80;
    private int sweepAngleSmall = -70;
    private int radius;

    public LearnView(Context context) {
        this(context, null);
    }

    public LearnView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStrokeWidth(3);
        paint.setColor(Color.CYAN);
        paint.setStyle(Paint.Style.STROKE);

        path = new Path();
        path1 = new Path();
        camera = new Camera();
        Log.w("LearnView", "-------------LearnView");
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        Log.w("LearnView", "-------------onMeasure");
    }

    private int x;
    private int y;

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        Log.w("LearnView", "-------------onSizeChanged");

        int min = Math.min(w, h);
        x = w / 2;
        y = h / 2;
        radius = ((int) (min * 0.8 / 2));

        rightPath = new Path();
        downPath = new Path();
        leftPath = new Path();
        topPath = new Path();

        bigRect = new RectF(x - radius, y - radius, x + radius, y + radius);
        smallRect = new RectF(x - radius / 2, y - radius / 2, x + radius / 2, y + radius / 2);

        rightPath.addArc(bigRect, -40, sweepAngle);
        rightPath.arcTo(smallRect, 40, sweepAngleSmall);
        rightPath.close();

        downPath.addArc(bigRect, 50, sweepAngle);
        downPath.arcTo(smallRect, 130, sweepAngleSmall);
        downPath.close();

        leftPath.addArc(bigRect, 140, sweepAngle);
        leftPath.arcTo(smallRect, 220, sweepAngleSmall);
        leftPath.close();

        topPath.addArc(bigRect, 230, sweepAngle);
        topPath.arcTo(smallRect, 310, sweepAngleSmall);
        topPath.close();

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
//        int measuredWidth = getMeasuredWidth();
//        int measuredHeight = getMeasuredHeight();
//        int min = Math.min(measuredWidth, measuredHeight);
//        int x = measuredWidth / 2;
//        int y = measuredHeight / 2;
//        int radius = min / 2;

        canvas.drawLine(x, y - radius, x, y + radius, paint);
        canvas.drawLine(x - radius, y, x + radius, y, paint);


        canvas.drawPath(rightPath, paint);
        canvas.drawPath(downPath, paint);
        canvas.drawPath(leftPath, paint);
        canvas.drawPath(topPath, paint);

//        paint.setStrokeWidth(30);
//        paint.setStrokeJoin(Paint.Join.MITER);
//        canvas.drawLine(100,100,500,500,paint);
//        canvas.drawLine(500,500,500,0,paint);

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.a);
//        paint.setMaskFilter(new BlurMaskFilter(200, BlurMaskFilter.Blur.INNER));
//        canvas.drawBitmap(bitmap,0,0,paint);

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
//        canvas.save();
//        path.addCircle(x, y, radius, Path.Direction.CW);
//        path.setFillType(Path.FillType.INVERSE_WINDING);
//        canvas.clipPath(path);
//        canvas.drawBitmap(bitmap,0,0,paint);
//        canvas.restore();

//        canvas.save();
//        Matrix matrix = new Matrix();
//        matrix.postRotate(45);
//        canvas.concat(matrix);
//        canvas.drawLine(0, 0, 100, 0, paint);
//        canvas.restore();


//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
//        canvas.save();
//        Matrix matrix = new Matrix();
//        float[] src = {0, 0};
//        int DX = 20;
//        float[] dst = {0 + DX, 0 + DX};
//        matrix.setPolyToPoly(src, 0, dst, 0, 1);
//        canvas.drawBitmap(bitmap, matrix, paint);
//        canvas.restore();

//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
//        canvas.save();
//        Matrix matrix = new Matrix();
//        int bw = bitmap.getWidth();
//        int bh = bitmap.getHeight();
//        float[] src = {bw / 2, bh / 2, bw, 0};
//        float[] dst = {bw / 2, bh / 2, bw / 2 + bh / 2, bh / 2 + bw / 2};
//        matrix.setPolyToPoly(src, 0, dst, 0, 2);
//        canvas.drawBitmap(bitmap, matrix, paint);
//        canvas.restore();


//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
//        canvas.save();
//        camera.save();
//        camera.rotateX(degree);
//        camera.applyToCanvas(canvas);
//        camera.restore();
//        canvas.drawBitmap(bitmap,0,0,paint);
//        canvas.restore();


//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
//        canvas.save();
//        camera.save();
//        camera.rotateX(degree);
////        camera.setLocation(0,0,30);
//        canvas.translate(bitmap.getWidth()/2,bitmap.getHeight()/2);
//        camera.applyToCanvas(canvas);
//        canvas.translate(-bitmap.getWidth()/2,-bitmap.getHeight()/2);
//        camera.restore();
//        canvas.drawBitmap(bitmap,0,0,paint);
//        canvas.restore();

        //翻页效果
//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);
//        int bitmapWidth = bitmap.getWidth();
//        int bitmapHeight = bitmap.getHeight();
//        int centerX = getWidth() / 2;
//        int centerY = getHeight() / 2;
//        int xPoint = centerX - bitmapWidth / 2;
//        int yPoint = centerY - bitmapHeight / 2;
//
//        // 第一遍绘制：上半部分
//        canvas.save();
//        canvas.clipRect(0, 0, getWidth(), centerY);
//        canvas.drawBitmap(bitmap, xPoint, yPoint, paint);
//        canvas.restore();
//
//        // 第二遍绘制：下半部分
//        canvas.save();
//
//        if (degree < 90) {
//            canvas.clipRect(0, centerY, getWidth(), getHeight());
//        } else {
//            canvas.clipRect(0, 0, getWidth(), centerY);
//        }
//        camera.save();
//        camera.rotateX(degree);
//        canvas.translate(centerX, centerY);
//        camera.applyToCanvas(canvas);
//        canvas.translate(-centerX, -centerY);
//        camera.restore();
//
//        canvas.drawBitmap(bitmap, xPoint, yPoint, paint);
//        canvas.restore();


//        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.q);
//        Matrix matrix = new Matrix();
//        RectF rectSrc = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
//        RectF rectDst = new RectF(0, 0, getMeasuredWidth(), getMeasuredHeight());
//        matrix.setRectToRect(rectSrc, rectDst, Matrix.ScaleToFit.START);
//        canvas.drawBitmap(bitmap, matrix, paint);

//        Matrix matrix = new Matrix();
//        matrix.postTranslate(-100, -100);
//        Matrix matrix1 = new Matrix();
//        matrix.invert(matrix1);
//        canvas.concat(matrix1);
//        canvas.drawLine(0,0,300, 0,paint);

//        float[] src = new float[]{100,100,80,90,30,10,0,0};
//        float[] dst = new float[6];
//        Matrix matrix = new Matrix();
//        matrix.preTranslate(100, 200);
//        matrix.mapPoints(dst, 0,src,0,2);
//        Log.w("Ma", "mapPoints变换后：" + Arrays.toString(dst));
//        canvas.drawLine(0, 0, 300, 0, paint);

//        float[] src = new float[9];
//        canvas.translate(100,100);
//        Matrix matrix = canvas.getMatrix();
//        matrix.getValues(src);
//        int[] location1 = new int[2];
//        location1[0] = (int) src[2];
//        location1[1] = (int) src[5];
//        Log.w("Ma", "矩阵1：" + Arrays.toString(location1));


//        Log.w("Ma", "getWidth：" + getWidth());
//        float[] src = new float[]{0, 0, getWidth(), 0, getWidth(), getHeight(), 0, getHeight()};
//        float[] dst = new float[]{0, 0, getWidth()+50, 0, getWidth(), getHeight(), 0, getHeight()};
//        Matrix matrix = new Matrix();
//        matrix.setPolyToPoly(src, 0, dst, 0, src.length >> 1);
//        canvas.concat(matrix);


    }

    private float degree;

    public void setAnimator() {
        degree = 0;
        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 90);
        valueAnimator.setDuration(1500);
        valueAnimator.setInterpolator(new DecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                degree = (float) animation.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }


}

