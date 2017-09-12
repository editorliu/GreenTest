package com.er.greentest.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/8/9.
 */
@Entity
public class Phone {
    @Id
    private Long id;
    private String name;
    private double price;
    @Generated(hash = 1302671922)
    public Phone(Long id, String name, double price) {
        this.id = id;
        this.name = name;
        this.price = price;
    }
    @Generated(hash = 429398894)
    public Phone() {
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
    public double getPrice() {
        return this.price;
    }
    public void setPrice(double price) {
        this.price = price;
    }
}
