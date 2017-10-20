package com.er.greentest.entity.tomany;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by THTF on 2017/10/9.
 * Desc:
 */
@Entity
public class Customer {
    @Id
    private Long id;
    private String name;
    private int type;
    private  String address;
    @Generated(hash = 1651276)
    public Customer(Long id, String name, int type, String address) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.address = address;
    }
    @Generated(hash = 60841032)
    public Customer() {
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
    public int getType() {
        return this.type;
    }
    public void setType(int type) {
        this.type = type;
    }
    public String getAddress() {
        return this.address;
    }
    public void setAddress(String address) {
        this.address = address;
    }
    
}
