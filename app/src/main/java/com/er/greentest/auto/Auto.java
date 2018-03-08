package com.er.greentest.auto;

import android.util.Log;
import android.view.View;

/**
 * Created by THTF on 2018/1/5.
 * Desc:
 */

public class Auto implements View.OnClickListener {
    private View[] views;

    public void autoClick(View... views) {
        this.views = views;
        for (int i = 0; i < views.length; i++) {
            views[i].setTag(i);
            views[i].setOnClickListener(this);
        }
        views[0].performClick();
    }

    @Override
    public void onClick(View v) {
        int tag = (int) v.getTag();
        switch (tag) {
            case 0:
                Log.w("autoClick", "执行按钮A的业务逻辑，执行完后调用点击按钮B");
                break;
            case 1:
                Log.w("autoClick", "执行按钮B");
                break;
            case 2:
                Log.w("autoClick", "执行按钮C");
                break;
        }

        if ((tag + 1) < views.length) {
            views[tag + 1].performClick();
        }
    }
}
