package com.er.greentest.dagger2;

import javax.inject.Inject;
import javax.inject.Singleton;

/**
 * Created by THTF on 2017/9/28.
 * Desc:
 */
@Singleton
public class SecondBean {
    private int id;
    private String name;

    @Inject
    public SecondBean() {
        this.id = 100;
        this.name = "默认";
    }

//    @Inject
//    public SecondBean(int id, String name) {
//        this.id = id;
//        this.name = name;
//    }


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
