package com.er.greentest.entity.one;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by THTF on 2017/10/9.
 * Desc:
 */
@Entity
public class Student {
    @Id
    private Long id;
    private String name;
    private int age;
    private long scoreId;
    @Generated(hash = 980626006)
    public Student(Long id, String name, int age, long scoreId) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.scoreId = scoreId;
    }
    @Generated(hash = 1556870573)
    public Student() {
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
    public int getAge() {
        return this.age;
    }
    public void setAge(int age) {
        this.age = age;
    }
    public long getScoreId() {
        return this.scoreId;
    }
    public void setScoreId(long scoreId) {
        this.scoreId = scoreId;
    }
}
