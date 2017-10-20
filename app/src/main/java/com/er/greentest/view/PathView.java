package com.er.greentest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.View;

import com.er.greentest.R;

/**
 * Created by THTF on 2017/10/11.
 * Desc:
 */

public class PathView extends View {

    private Paint paint;
    private Path path;
    private PathMeasure pathMeasure;
    private RectF rectF;
    private Bitmap bitmap;
    float currentPercent = 0.000005f;

    public PathView(Context context) {
        this(context, null);
    }

    public PathView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();

    }

    private void init() {
        paint = new Paint();
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);
        paint.setAntiAlias(true);
        paint.setColor(Color.BLUE);

        path = new Path();

        pathMeasure = new PathMeasure();

        rectF = new RectF();


//        bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.ic_launcher);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.maps);

//        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, 1);
//        valueAnimator.setDuration(2000);
//        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
//            @Override
//            public void onAnimationUpdate(ValueAnimator animation) {
//                currentPercent = (float) animation.getAnimatedValue();
//                invalidate();
//            }
//        });
//        valueAnimator.start();
    }

//    @Override
//    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
//        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
//        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
//        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
//
//
//        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
//        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
//
//
//    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

//        path.lineTo(100,200);
//        path.arcTo(new RectF(0,0,100,100),0,270);
//        path.arcTo(new RectF(0,0,100,100),0,270,true);
//        pathMeasure.setPath(path,false);
//        canvas.drawPath(path,paint);


//        canvas.drawPoint(50,50,paint);
//        canvas.drawPoint(200,50,paint);
//        paint.setColor(Color.BLACK);
//        paint.setStrokeWidth(3);
//        path.moveTo(125,50);
//        path.cubicTo(50,50,200,50,200,200);
//        canvas.drawPath(path,paint);

//        paint.setStrokeWidth(5);
//        path.lineTo(100,300);
//        path.lineTo(200,200);
//        path.addCircle(250,250,100, Path.Direction.CW);
//        path.computeBounds(rectF,true);
//        canvas.drawPath(path,paint);
//        canvas.drawRect(rectF,paint);


//        path.lineTo(39,180);
//        path.addArc(new RectF(100,100,300,300),0,120);
//        path.addRect(new RectF(100,100,300,300), Path.Direction.CW);
////        path.arcTo(new RectF(100,100,300,300),0,120,true);
//        canvas.drawPath(this.path,paint);
//
//        pathMeasure.setPath(path,false);
//        Path dstPath = new Path();
//        dstPath.lineTo(50,50);
//        pathMeasure.getSegment(100, 400, dstPath, false);
//        paint.setColor(Color.RED);
//        canvas.drawPath(dstPath,paint);


        paint.setStrokeWidth(4);
        float[] pos = new float[2];
        float[] tan = new float[2];
        float centerX = 400;
        float centerY = 400;
        float radius = 300;

        path.addCircle(centerX, centerY, radius, Path.Direction.CW);
        pathMeasure.setPath(path, false);

//
//        path.moveTo(0, 100);
//        path.lineTo(70, 100);
//        path.moveTo(0, 300);
//        path.lineTo(300, 300);
//        pathMeasure.setPath(path, false);
//        Log.w("pathMeasure", "pathMeasure:" + pathMeasure.getLength());
//        int count = 0;
//        while (pathMeasure.nextContour()) {
//            count++;
//            Log.w("pathMeasure", "pathMeasure:" + pathMeasure.getLength());
//        }
//        Log.w("pathMeasure", "count:" + count);
//        canvas.drawPath(path, paint);


        float distance = pathMeasure.getLength() * currentPercent;

//        pathMeasure.getPosTan(distance, pos, tan);
//        double degree = Math.atan2(tan[1], tan[0]) * 180 / Math.PI;
//        Log.w("posTan", "pos:" + pos[0] + "  " + pos[1]);
//        Log.w("posTan", "tan:" + tan[0] + "  " + tan[1]);
//        Log.w("posTan", "degree:" +degree);

        canvas.drawPath(path, paint);
//        canvas.drawLine(centerX,centerY,centerX+radius,centerY,paint);
//
        Matrix matrix = new Matrix();
        distance = 50;
//        pathMeasure.getMatrix(distance, matrix, PathMeasure.POSITION_MATRIX_FLAG | PathMeasure.TANGENT_MATRIX_FLAG);
        pathMeasure.getMatrix(distance, matrix, PathMeasure.TANGENT_MATRIX_FLAG);
//        pathMeasure.getMatrix(distance, matrix, PathMeasure.POSITION_MATRIX_FLAG );
        Log.w("pathMeasure", "matrix:" +matrix.toString());
        matrix.preTranslate(-bitmap.getWidth() / 2, -bitmap.getHeight() / 2);
        canvas.drawBitmap(bitmap, matrix, paint);
//
//
//        Matrix matrix1 = new Matrix();
//
//        Log.w("matrix", "" + matrix1.toString());

//        float[] src = {0,0,bitmap.getWidth(),0,bitmap.getWidth(),bitmap.getHeight(),0,bitmap.getHeight()};
//        float[] dst = {0+20,0+80,bitmap.getWidth(),0,bitmap.getWidth(),bitmap.getHeight(),0,bitmap.getHeight()};
//        Matrix matrix = new Matrix();
//        matrix.setPolyToPoly(src, 0, dst, 0, 2);
//
//        canvas.drawBitmap(bitmap,matrix,paint);

//
//        Camera camera = new Camera();
////        camera.translate(0,10,0);
//        camera.rotateX(20);
//
//
//
//        canvas.restore();
//
//        canvas.save();


    }


}
