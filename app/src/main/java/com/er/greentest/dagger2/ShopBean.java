package com.er.greentest.dagger2;

import android.content.Context;

import javax.inject.Inject;

/**
 * Created by THTF on 2017/9/27.
 */

public class ShopBean {
    private int id;
    private String name;

    @Inject
    public ShopBean() {
        this.name = "nameXXX";
    }

    public ShopBean(Integer integer) {
        this.name = "name default";
    }

    public ShopBean(Context context) {
        this.name = "name context";
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
