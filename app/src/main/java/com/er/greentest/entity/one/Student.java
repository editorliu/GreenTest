package com.er.greentest.entity.one;

import com.er.greentest.gen.DaoSession;
import com.er.greentest.gen.ScoreDao;
import com.er.greentest.gen.StudentDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToOne;

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
    @ToOne(joinProperty = "scoreId")
    private Score score;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 1943931642)
    private transient StudentDao myDao;
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
    @Generated(hash = 1752064941)
    private transient Long score__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1449118759)
    public Score getScore() {
        long __key = this.scoreId;
        if (score__resolvedKey == null || !score__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            ScoreDao targetDao = daoSession.getScoreDao();
            Score scoreNew = targetDao.load(__key);
            synchronized (this) {
                score = scoreNew;
                score__resolvedKey = __key;
            }
        }
        return score;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 546298979)
    public void setScore(@NotNull Score score) {
        if (score == null) {
            throw new DaoException(
                    "To-one property 'scoreId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.score = score;
            scoreId = score.getId();
            score__resolvedKey = scoreId;
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
    @Generated(hash = 1701634981)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getStudentDao() : null;
    }
}
