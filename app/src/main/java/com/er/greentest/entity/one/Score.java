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
public class Score {
    @Id
    private Long id;
    private int point;
    private long studentId;
    @ToOne(joinProperty = "studentId")
    private Student student;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 302717168)
    private transient ScoreDao myDao;
    @Generated(hash = 690830948)
    public Score(Long id, int point, long studentId) {
        this.id = id;
        this.point = point;
        this.studentId = studentId;
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
    public long getStudentId() {
        return this.studentId;
    }
    public void setStudentId(long studentId) {
        this.studentId = studentId;
    }
    @Generated(hash = 79695740)
    private transient Long student__resolvedKey;
    /** To-one relationship, resolved on first access. */
    @Generated(hash = 1299365531)
    public Student getStudent() {
        long __key = this.studentId;
        if (student__resolvedKey == null || !student__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            StudentDao targetDao = daoSession.getStudentDao();
            Student studentNew = targetDao.load(__key);
            synchronized (this) {
                student = studentNew;
                student__resolvedKey = __key;
            }
        }
        return student;
    }
    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1384923540)
    public void setStudent(@NotNull Student student) {
        if (student == null) {
            throw new DaoException(
                    "To-one property 'studentId' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.student = student;
            studentId = student.getId();
            student__resolvedKey = studentId;
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
    @Generated(hash = 339145390)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getScoreDao() : null;
    }
}
