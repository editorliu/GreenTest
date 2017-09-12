package com.er.greentest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Administrator on 2017/9/4.
 * Description：仪表盘
 * Version：1
 */

public class VelocityPanel extends View {

    public static final int LONG_LINE = 40;
    public static final int SHORT_LINE = 20;
    private Paint outCirclePaint;
    private Paint innerPaint;
    private Paint txtPaint;
    private RectF rectF;
    private float scaleWidth;

    public VelocityPanel(Context context) {
        this(context, null);
    }

    public VelocityPanel(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        outCirclePaint = new Paint();
        outCirclePaint.setStyle(Paint.Style.STROKE);
        outCirclePaint.setStrokeWidth(5);
        outCirclePaint.setColor(Color.BLUE);

        txtPaint = new Paint();
        txtPaint.setStyle(Paint.Style.STROKE);
        txtPaint.setStrokeWidth(5);
        txtPaint.setTextSize(30);
        txtPaint.setColor(Color.BLUE);

        innerPaint = new Paint();
        innerPaint.setStyle(Paint.Style.STROKE);
        innerPaint.setStrokeWidth(5);
        innerPaint.setColor(Color.GRAY);

        rectF = new RectF();

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int measuredWidth = getMeasuredWidth();
        int measuredHeight = getMeasuredHeight();
        int min = Math.min(measuredWidth, measuredHeight);

        Paint paint = new Paint();
        paint.setColor(Color.CYAN);
        paint.setStrokeWidth(80);
        paint.setStyle(Paint.Style.STROKE);
//        canvas.drawLine(0,10,100,10,paint);
//        canvas.drawLine(0,0,100,0,paint);
        canvas.drawLine(0,45,100,45,paint);

        canvas.drawCircle(min/2,min/2,min/2,paint);

//        scaleWidth = outCirclePaint.getStrokeWidth();
//        float radius = min / 2 - scaleWidth;
//
//        canvas.save();
//        rectF.set(scaleWidth, scaleWidth, min - scaleWidth, min - scaleWidth);
//        canvas.drawArc(rectF, -210, 240, false, outCirclePaint);
////        canvas.drawArc
//        canvas.restore();
//
//        canvas.save();
//        canvas.translate(radius, radius);
//        canvas.rotate(-30f);
//        for (int i = 0; i < 41; i++) {
//            if (i != 0) {
//                canvas.rotate(6);
//            }
//            int lineLength = i % 5 == 0 ? LONG_LINE : SHORT_LINE;
////            canvas.drawLine(-radius + scaleWidth, 0, -radius + scaleWidth + lineLength, 0, outCirclePaint);
//            canvas.drawLine(-radius, 0, -radius+ lineLength, 0, outCirclePaint);
//        }
//        canvas.restore();

    }
}
