package com.er.greentest.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

import com.er.greentest.R;

/**
 * Created by THTF on 2017/10/19.
 * Desc:
 */

public class MultiView extends View {

    private Bitmap bitmap;
    private RectF rectF;
    private Paint paint;
    private Matrix matrix;

    public MultiView(Context context) {
        this(context, null);
    }

    public MultiView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.d);
        rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
        matrix = new Matrix();

        paint = new Paint();

        point = new PointF(0,0);

    }

//    @Override
//    public boolean onTouchEvent(MotionEvent event) {
//        int actionMasked = event.getActionMasked();
////        int pointerCount = event.getPointerCount();
////        Log.w("MULTI", "--------------pointerCount:"+pointerCount);
//        switch (actionMasked) {
//            case MotionEvent.ACTION_DOWN:
//                Log.w("MULTI", "--------------ACTION_DOWN");
//
//                break;
//            case MotionEvent.ACTION_UP:
//                Log.w("MULTI", "--------------ACTION_UP");
//                break;
//            case MotionEvent.ACTION_MOVE:
//                Log.w("MULTI", "--------------ACTION_MOVE");
//                int count = event.getPointerCount();
//                for (int i = 0; i < count; i++) {
//
//
//                    int actionIndex = event.getActionIndex();
//                    int pointerId = event.getPointerId(actionIndex);
//                    int pointerIndex = event.findPointerIndex(pointerId);
//
//                }
//
//                break;
//            case MotionEvent.ACTION_POINTER_DOWN:
//
//                Log.w("MULTI", "--------------ACTION_POINTER_DOWN:");
//                break;
//            case MotionEvent.ACTION_POINTER_UP:
//                Log.w("MULTI", "--------------ACTION_POINTER_UP");
//                break;
//
//        }
//        return true;
//
//    }

private boolean canDrag= false;
    private PointF point;
    @Override
    public boolean onTouchEvent(MotionEvent event) {
//        int action = event.getAction();
        int action = event.getActionMasked();
        switch (action) {
            case MotionEvent.ACTION_DOWN:
                Log.w("MULTI", "--------------ACTION_DOWN");
                if(rectF.contains(event.getX(),event.getY())){
                    canDrag = true;
                    point.set(event.getX(), event.getY());
                }else{
                    Log.w("MULTI", "--------------ACTION_DOWN  FALSE");
                }
                break;
            case MotionEvent.ACTION_UP:
                Log.w("MULTI", "--------------ACTION_UP");
                canDrag = false;
                break;
            case MotionEvent.ACTION_MOVE:
                Log.w("MULTI", "--------------ACTION_MOVE");
                if (canDrag) {
                    matrix.preTranslate(event.getX()-point.x, event.getY()-point.y);
                    point.set(event.getX(), event.getY());

                    rectF = new RectF(0, 0, bitmap.getWidth(), bitmap.getHeight());
                    matrix.mapRect(rectF);
                    invalidate();
                }
                break;

        }
        return true;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawBitmap(bitmap,matrix,paint);
    }
}
