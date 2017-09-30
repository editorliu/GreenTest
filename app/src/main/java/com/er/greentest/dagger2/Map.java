package com.er.greentest.dagger2;

import javax.inject.Inject;

/**
 * Created by THTF on 2017/9/27.
 */
@PerActivity
public class Map {
    private int id;
    private String type;


    @Inject
    public Map() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
