package com.er.greentest.dagger2;

import android.util.Log;

import javax.inject.Inject;

/**
 * Created by THTF on 2017/9/27.
 */

public class DaggerTestPresenter implements DaggerTestContract.Presenter {
    private DaggerTestContract.View view;

    @Inject
    public DaggerTestPresenter(DaggerTestContract.View view) {
        this.view = view;
    }

    @Override
    public void getData(String params) {
        Log.w("Dg", "------presenter input params is :" + params);
        view.refreshUi("------view method "+params);
    }
}
