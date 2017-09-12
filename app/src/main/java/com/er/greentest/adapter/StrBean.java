package com.er.greentest.adapter;

/**
 * Created by Administrator on 2017/8/14.
 */

public class StrBean {
    private String name;
    private int id;

    public StrBean(int id,String name) {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
}
