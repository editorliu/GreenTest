package com.er.greentest.entity.tomany;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by THTF on 2017/10/9.
 * Desc:
 */
@Entity
public class CustomerOrder {
    @Id
    private Long id;
    private String itemName;
    private long customerId;
    @Generated(hash = 1231678496)
    public CustomerOrder(Long id, String itemName, long customerId) {
        this.id = id;
        this.itemName = itemName;
        this.customerId = customerId;
    }
    @Generated(hash = 878784061)
    public CustomerOrder() {
    }
    public Long getId() {
        return this.id;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public String getItemName() {
        return this.itemName;
    }
    public void setItemName(String itemName) {
        this.itemName = itemName;
    }
    public long getCustomerId() {
        return this.customerId;
    }
    public void setCustomerId(long customerId) {
        this.customerId = customerId;
    }

}
