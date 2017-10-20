package com.er.greentest.view;

import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.PathMeasure;
import android.graphics.Point;
import android.graphics.RectF;
import android.graphics.Region;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * Created by THTF on 2017/10/11.
 * Desc:
 */

public class CircleStartView extends View {

    private Paint paint;
    private PathMeasure pathMeasure;
    private Path path;
    private Path desPath;
    private float animatedValue;
    private float length;

    public CircleStartView(Context context) {
        this(context,null);
    }

    public CircleStartView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        paint = new Paint();
        paint.setColor(Color.BLUE);
        paint.setStyle(Paint.Style.STROKE);
        paint.setStrokeWidth(10);

        pathMeasure = new PathMeasure();
        desPath = new Path();
        path = new Path();
        path.addArc(new RectF(200, 200, 600, 600), 0, 359.6f);

        for (int i = 0; i < 6; i++) {
            Point point = getPoint(200, -144 * i);
            path.lineTo(400 + point.x, 400 + point.y);
        }

        path.close();

        pathMeasure.setPath(path,false);

        length = pathMeasure.getLength();

        ValueAnimator valueAnimator = ValueAnimator.ofFloat(0, length);
        valueAnimator.setDuration(1500);
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                animatedValue = (float)valueAnimator.getAnimatedValue();
                invalidate();
            }
        });
        valueAnimator.start();
    }

    private Point getPoint(float radius, float angle) {
        int x = (int) ((radius) * Math.cos(angle * Math.PI / 180f));
        int y = (int) ((radius) * Math.sin(angle * Math.PI / 180f));
        Point p = new Point(x, y);
        return p;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        desPath.reset();

        desPath.lineTo(0,0);

        pathMeasure.getSegment(0, animatedValue, desPath, true);

        canvas.drawPath(desPath,paint);


    }


    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float rawX = event.getRawX();
        float x = event.getX();


        int left = getLeft();//相对于父布局的初始位置，不随translateX变化
        float x1 = getX();//当view没有发生位移的时候，getX=getLeft
        getTranslationX();
        setX(29);
        setTranslationX(100);
        getScrollX();

        scrollTo(1000,100);


        int actionIndex = event.getActionIndex();
        Region region = new Region();
        region.setPath(new Path(), region);


        return super.onTouchEvent(event);
    }
}
