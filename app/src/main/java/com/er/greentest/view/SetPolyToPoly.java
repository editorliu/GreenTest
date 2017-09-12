package com.er.greentest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import com.er.greentest.R;

/**
 * Created by Administrator on 2017/9/5.
 * Description：
 * Version：
 */

public class SetPolyToPoly  extends View {
    private static final String TAG = "SetPolyToPoly";

    private int testPoint = 4;
    private int triggerRadius = 180;    // 触发半径为180px

    private Bitmap mBitmap;             // 要绘制的图片
    private Matrix mPolyMatrix;         // 测试setPolyToPoly用的Matrix

    private float[] src = new float[8];
    private float[] dst = new float[8];

    private Paint pointPaint;

    public SetPolyToPoly(Context context) {
        this(context, null);
    }

    public SetPolyToPoly(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SetPolyToPoly(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initBitmapAndMatrix();
    }

    private void initBitmapAndMatrix() {
        mBitmap = BitmapFactory.decodeResource(getResources(), R.drawable.q);

        float[] temp = {0, 0,                                    // 左上
                mBitmap.getWidth(), 0,                          // 右上
                mBitmap.getWidth(), mBitmap.getHeight(),        // 右下
                0, mBitmap.getHeight()};                        // 左下
        src = temp.clone();
        dst = temp.clone();

        pointPaint = new Paint();
        pointPaint.setAntiAlias(true);
        pointPaint.setStrokeWidth(50);
        pointPaint.setColor(0xffd19165);
        pointPaint.setStrokeCap(Paint.Cap.ROUND);

        mPolyMatrix = new Matrix();
        mPolyMatrix.setPolyToPoly(src, 0, src, 0, 4);

    }

    private  boolean isMatrix = false;
    private int indexX = 0;
    private int indexY = 0;
    @Override
    public boolean onTouchEvent(MotionEvent event) {

        switch (event.getAction()){
            case MotionEvent.ACTION_DOWN:
                float tempX1 = event.getX();
                float tempY1 = event.getY();

                // 根据触控位置改变dst
                for (int i=0; i<testPoint*2; i+=2 ) {
                    if (Math.abs(tempX1 - dst[i]) <= triggerRadius && Math.abs(tempY1 - dst[i+1]) <= triggerRadius){
                        dst[i]   = tempX1-100;
                        dst[i+1] = tempY1-100;
                        indexX = i;
                        indexY = i+1;
                        isMatrix = true;
                        break;  // 防止两个点的位置重合
                    }
                }
                break;
            case MotionEvent.ACTION_MOVE:
//                float tempX = event.getX();
//                float tempY = event.getY();
//
//                // 根据触控位置改变dst
//                for (int i=0; i<testPoint*2; i+=2 ) {
//                    if (Math.abs(tempX - dst[i]) <= triggerRadius && Math.abs(tempY - dst[i+1]) <= triggerRadius){
//                        dst[i]   = tempX-100;
//                        dst[i+1] = tempY-100;
//                        break;  // 防止两个点的位置重合
//                    }
//                }
//
//                resetPolyMatrix(testPoint);
//                invalidate();
                if (isMatrix) {
                    float tempX = event.getX();
                    float tempY = event.getY();
                    dst[indexX]   = tempX-100;
                    dst[indexY] = tempY-100;
                    resetPolyMatrix(testPoint);
                    invalidate();
                }
                break;
            case MotionEvent.ACTION_UP:
                isMatrix = false;
                break;
        }

        return true;
    }

    public void resetPolyMatrix(int pointCount){
        mPolyMatrix.reset();
        // 核心要点
        mPolyMatrix.setPolyToPoly(src, 0, dst, 0, pointCount);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.translate(100,100);

        // 绘制坐标系
//        CanvasAidUtils.setCoordinateLen(900, 0, 1200, 0);
//        CanvasAidUtils.drawCoordinateSpace(canvas);

        // 根据Matrix绘制一个变换后的图片
        canvas.drawBitmap(mBitmap, mPolyMatrix, null);

        float[] dst = new float[8];
        mPolyMatrix.mapPoints(dst,src);

        // 绘制触控点
        for (int i=0; i<testPoint*2; i+=2 ) {
            canvas.drawPoint(dst[i], dst[i+1],pointPaint);
        }
    }

    public void setTestPoint(int testPoint) {
        this.testPoint = testPoint > 4 || testPoint < 0 ? 4 : testPoint;
        dst = src.clone();
        resetPolyMatrix(this.testPoint);
        invalidate();
    }
}