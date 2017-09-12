package com.er.greentest.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/8/9.
 */
@Entity
public class Room {
    @Id
    Long id;
    private String name;
    private double area;
    @Generated(hash = 1203440928)
    public Room(Long id, String name, double area) {
        this.id = id;
        this.name = name;
        this.area = area;
    }
    @Generated(hash = 703125385)
    public Room() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getName() {
        return this.name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public double getArea() {
        return this.area;
    }
    public void setArea(double area) {
        this.area = area;
    }
}
