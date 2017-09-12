package com.er.greentest.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/8/9.
 */
@Entity
public class Desk {
    @Id
    private Long id;
    private double length;
    private double width;
    private double height;
    @Generated(hash = 1228135563)
    public Desk(Long id, double length, double width, double height) {
        this.id = id;
        this.length = length;
        this.width = width;
        this.height = height;
    }
    @Generated(hash = 258074748)
    public Desk() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public double getLength() {
        return this.length;
    }
    public void setLength(double length) {
        this.length = length;
    }
    public double getWidth() {
        return this.width;
    }
    public void setWidth(double width) {
        this.width = width;
    }
    public double getHeight() {
        return this.height;
    }
    public void setHeight(double height) {
        this.height = height;
    }
}
