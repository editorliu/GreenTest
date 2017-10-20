package com.er.greentest.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by THTF on 2017/10/10.
 * Desc:
 */
@Entity
public class T {
    @Id
    private Long id;
    private float df;
    @Generated(hash = 1246426439)
    public T(Long id, float df) {
        this.id = id;
        this.df = df;
    }
    @Generated(hash = 621198702)
    public T() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public float getDf() {
        return this.df;
    }
    public void setDf(float df) {
        this.df = df;
    }
}
