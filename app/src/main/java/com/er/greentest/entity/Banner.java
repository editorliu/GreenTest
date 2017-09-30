package com.er.greentest.entity;

import android.content.DialogInterface;

/**
 * Created by THTF on 2017/9/29.
 * Desc:
 */

public class Banner extends Fruit implements DialogInterface.OnClickListener{
    private String name;

    public Banner() {

    }

    public Banner(String name) {
        this.name = name;
    }

    @Override
    public void name() {
        System.out.println(name);
    }

    @Override
    public void onClick(DialogInterface dialog, int which) {

    }

    class inner{
        private String name;

    }

//    “is-a” 是一个  extends 是一个
    //功能性
}
