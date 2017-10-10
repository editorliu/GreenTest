package com.er.greentest.entity.one;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by THTF on 2017/10/9.
 * Desc:
 */
@Entity
public class Score {
    @Id
    private Long id;
    private int point;
    @Generated(hash = 1688621918)
    public Score(Long id, int point) {
        this.id = id;
        this.point = point;
    }
    @Generated(hash = 226049941)
    public Score() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public int getPoint() {
        return this.point;
    }
    public void setPoint(int point) {
        this.point = point;
    }
}
