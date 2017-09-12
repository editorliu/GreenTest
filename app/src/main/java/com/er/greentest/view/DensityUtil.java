package com.er.greentest.view;

import android.content.Context;

/**
 * Created by Administrator on 2017/8/28.
 * Description：
 * Version：
 */

public class DensityUtil {
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }
}
