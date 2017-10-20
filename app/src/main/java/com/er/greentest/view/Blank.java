package com.er.greentest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by THTF on 2017/10/18.
 * Desc:
 */

public class Blank extends View {
    private int w;
    private int h;
    private float x = -1;
    private float y = -1;

    private float oldX = -1;
    private float oldY = -1;
    private Paint paint;
    private Region gloableRegion;

    public Blank(Context context) {
        this(context, null);
    }

    public Blank(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setStrokeWidth(8);
        paint.setColor(Color.RED);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        this.w = w;
        this.h = h;

        gloableRegion = new Region(-w, -h, w, h);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int action = event.getAction();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
            case MotionEvent.ACTION_MOVE:
               Log.w("contains", "MotionEvent:" + action+" "+event.getX()+" "+event.getY());
                oldX = x = event.getX();
                oldY = y = event.getY();
                invalidate();
                break;

            case MotionEvent.ACTION_CANCEL:
            case MotionEvent.ACTION_UP:
                Log.w("contains", "-----------MotionEvent:UP"+action+" "+event.getX()+" "+event.getY());
                x = y = -1;
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Log.w("contains", "^^^^^^^^^^^^^^^^^^^^onDraw");
        canvas.drawLine(0, h / 2, w, h / 2, paint);
        canvas.drawLine(w / 2, 0, w / 2, h, paint);
        if (x == -1 || y == -1) return;
        canvas.translate(w / 2, h / 2);

        float[] points = {x, y};
        Matrix matrix = new Matrix();
        matrix.preTranslate(-w / 2, -h / 2);
        matrix.mapPoints(points);
        canvas.drawCircle(points[0], points[1], 30, paint);

        Path path = new Path();
        path.addRect(-100,-100,100,100, Path.Direction.CW);
        Region region = new Region();
        region.setPath(path, gloableRegion);
        boolean contains = region.contains(((int) points[0]), ((int) points[1]));
//        Log.w("contains", "x:" + x + " y:" + y + " " + contains);
        canvas.drawPath(path,paint);

//        Matrix matrix0 = canvas.getMatrix();
//        canvas.translate(w / 2, h / 2);
//        Matrix matrix2 = new Matrix();
//        Matrix matrix1 = canvas.getMatrix();
//        matrix1.invert(matrix2);
//        Log.w("contains", "matrix0:" + matrix0);
//        Log.w("contains", "matrix1:" + matrix1);
//        Log.w("contains", "matrix2:" + matrix2);
//
//
//        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
//        Log.w("contains", "displayMetrics density" + displayMetrics.density+"  "+displayMetrics.densityDpi);

    }

}
