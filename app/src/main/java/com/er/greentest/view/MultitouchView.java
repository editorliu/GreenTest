package com.er.greentest.view;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.PropertyValuesHolder;
import android.animation.TypeEvaluator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import static android.icu.text.RelativeDateTimeFormatter.Direction.THIS;

/**
 * Created by Administrator on 2017/9/7.
 * Description：multi touch view
 * Version：1
 */

public class MultitouchView extends View {

    private Paint paint;
    private RectF rectF;

    public MultitouchView(Context context) {
        this(context, null);
    }

    public MultitouchView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        rectF = new RectF(200, 200, 400, 400);
    }

    private void setTransX(float x) {

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int actionMasked = event.getActionMasked();
        int pointerCount = event.getPointerCount();
        switch (actionMasked) {
            case MotionEvent.ACTION_DOWN:
                Log.w("MultiTouch", "--------------ACTION_DOWN  " + pointerCount);
                break;
            case MotionEvent.ACTION_MOVE:
                Log.w("MultiTouch", "--------------ACTION_MOVE  " + pointerCount);
                break;
            case MotionEvent.ACTION_UP:
                Log.w("MultiTouch", "--------------ACTION_UP  " + pointerCount);
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                Log.w("MultiTouch", "--------------ACTION_POINTER_DOWN  " + pointerCount);
                break;
            case MotionEvent.ACTION_POINTER_UP:
                Log.w("MultiTouch", "--------------ACTION_POINTER_UP  " + pointerCount);
                break;
        }
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paint.reset();

//        paint.setColor(Color.RED);
//
//        paint.setTextAlign(Paint.Align.CENTER);
//        paint.setTextSize(50);
//        canvas.drawText(T + "%", rectF.centerX(), rectF.centerY() + paint.descent(), paint);
//
//        paint.setStrokeWidth(20);
//        paint.setStyle(Paint.Style.STROKE);
//        paint.setAntiAlias(true);
//        paint.setStrokeCap(Paint.Cap.ROUND);
//        canvas.drawArc(rectF, 0, T, false, paint);

        canvas.drawColor(color);
    }

    private int T = 0;

    public void startAnimator() {
//        ObjectAnimator t = ObjectAnimator.ofInt(this, "T", 0, 120);
//        t.setDuration(1000);
//        t.setInterpolator(new OvershootInterpolator());
//        t.start();

        ObjectAnimator animator = ObjectAnimator.ofInt(this, "color", 0xffff0000, 0xff00ff00);
        animator.setDuration(5000);
//        animator.setEvaluator(new ArgbEvaluator());
        animator.setEvaluator(new HsvEvaluator());
        animator.start();

        PropertyValuesHolder propertyValuesHolder = PropertyValuesHolder.ofFloat("scaleX", 0, 1);
        PropertyValuesHolder propertyValuesHolder1 = PropertyValuesHolder.ofFloat("scaleY", 1, 2);
        ObjectAnimator.ofPropertyValuesHolder(propertyValuesHolder, propertyValuesHolder1);


        ObjectAnimator t = ObjectAnimator.ofInt(this, "T", 1, 2);
        ObjectAnimator color = ObjectAnimator.ofInt(this, "color", 1, 2);
        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.playSequentially(t, color);
        animatorSet.start();


    }

    private int color;

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        this.color = color;
        invalidate();
    }

    public int getT() {
        return T;
    }

    public void setT(int t) {
        T = t;
        invalidate();
    }

    class HsvEvaluator implements TypeEvaluator<Integer> {
        private float[] startHsv = new float[3];
        private float[] endHsv = new float[3];
        float[] outHsv = new float[3];

        @Override
        public Integer evaluate(float fraction, Integer startValue, Integer endValue) {
            Color.colorToHSV(startValue, startHsv);
            Color.colorToHSV(endValue, endHsv);

            if (endHsv[0] - startHsv[0] > 180) {
                endHsv[0] -= 360;
            } else if (endHsv[0] - startHsv[0] < -180) {
                endHsv[0] += 360;
            }
            outHsv[0] = startHsv[0] + (endHsv[0] - startHsv[0]) * fraction;
            if (outHsv[0] > 360) {
                outHsv[0] -= 360;
            } else if (outHsv[0] < 0) {
                outHsv[0] += 360;
            }
            outHsv[1] = startHsv[1] + (endHsv[1] - startHsv[1]) * fraction;
            outHsv[2] = startHsv[2] + (endHsv[2] - startHsv[2]) * fraction;

            // 计算当前动画完成度（fraction）所对应的透明度
            int alpha = startValue >> 24 + (int) ((endValue >> 24 - startValue >> 24) * fraction);


            return Color.HSVToColor(alpha, outHsv);
        }
    }

}
