package com.er.greentest.entity;

/**
 * Created by THTF on 2017/9/25.
 */

public class School {
    private String name;
    private int code;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public School(String name, int code) {
        this.name = name;
        this.code = code;
    }
}
