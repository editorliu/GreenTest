package com.er.greentest.entity;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by Administrator on 2017/8/9.
 */

@Entity
public class Teacher {
    @Id
    private Long id;
    private String name;
    private int workAge;
    @Generated(hash = 274037526)
    public Teacher(Long id, String name, int workAge) {
        this.id = id;
        this.name = name;
        this.workAge = workAge;
    }
    @Generated(hash = 1630413260)
    public Teacher() {
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
    public int getWorkAge() {
        return this.workAge;
    }
    public void setWorkAge(int workAge) {
        this.workAge = workAge;
    }
}

