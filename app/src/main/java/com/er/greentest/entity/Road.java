package com.er.greentest.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/8/9.
 */

@Entity
public class Road {
    @Id
    private Long id;
    private String name;
    @Generated(hash = 1719920900)
    public Road(Long id, String name) {
        this.id = id;
        this.name = name;
    }
    @Generated(hash = 1783458210)
    public Road() {
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
}
