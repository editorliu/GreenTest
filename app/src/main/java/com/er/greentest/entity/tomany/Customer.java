package com.er.greentest.entity.tomany;

import com.er.greentest.gen.CustomerDao;
import com.er.greentest.gen.CustomerOrderDao;
import com.er.greentest.gen.DaoSession;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;

/**
 * Created by THTF on 2017/10/9.
 * Desc:
 */
@Entity
public class Customer {
    @Id
    private Long id;
    private String name;

    @ToMany(referencedJoinProperty = "customerId")
    private List<CustomerOrder> customerOrderList;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1697251196)
    private transient CustomerDao myDao;

    @Generated(hash = 855149052)
    public Customer(Long id, String name) {
        this.id = id;
        this.name = name;
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

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 481219403)
    public List<CustomerOrder> getCustomerOrderList() {
        if (customerOrderList == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CustomerOrderDao targetDao = daoSession.getCustomerOrderDao();
            List<CustomerOrder> customerOrderListNew = targetDao
                    ._queryCustomer_CustomerOrderList(id);
            synchronized (this) {
                if (customerOrderList == null) {
                    customerOrderList = customerOrderListNew;
                }
            }
        }
        return customerOrderList;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 1750318618)
    public synchronized void resetCustomerOrderList() {
        customerOrderList = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 462117449)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getCustomerDao() : null;
    }

}
