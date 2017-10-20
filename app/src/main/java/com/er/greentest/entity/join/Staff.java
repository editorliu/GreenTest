package com.er.greentest.entity.join;

import com.er.greentest.gen.CompanyDao;
import com.er.greentest.gen.DaoSession;
import com.er.greentest.gen.StaffDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;

import java.util.Date;

/**
 * Created by THTF on 2017/10/16.
 * Desc:
 */
@Entity
public class Staff {
    @Id
    private Long id;
    private String name;
    private String position;
    private Date date;
    private long companyId;
    @ToOne(joinProperty = "companyId")
    private Company company;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1940714421)
    private transient StaffDao myDao;
    @Generated(hash = 780600429)
    public Staff(Long id, String name, String position, Date date, long companyId) {
        this.id = id;
        this.name = name;
        this.position = position;
        this.date = date;
        this.companyId = companyId;
    }
    @Generated(hash = 1774984890)
    public Staff() {
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
    public String getPosition() {
        return this.position;
    }
    public void setPosition(String position) {
        this.position = position;
    }
    public Date getDate() {
        return this.date;
    }
    public void setDate(Date date) {
        this.date = date;
    }
    public long getCompanyId() {
        return this.companyId;
    }
    public void setCompanyId(long companyId) {
        this.companyId = companyId;
    }
    @Generated(hash = 1496811699)
    private transient Long company__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1266421621)
    public Company getCompany() {
        long __key = this.companyId;
        if (company__resolvedKey == null || !company__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            CompanyDao targetDao = daoSession.getCompanyDao();
            Company companyNew = targetDao.load(__key);
            synchronized (this) {
                company = companyNew;
                company__resolvedKey = __key;
            }
        }
        return company;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 474861392)
    public void setCompany(@NotNull Company company) {
        if (company == null) {
            throw new DaoException(
                    "To-one property 'companyId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.company = company;
            companyId = company.getId();
            company__resolvedKey = companyId;
        }
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
    @Generated(hash = 1573905378)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStaffDao() : null;
    }

}
