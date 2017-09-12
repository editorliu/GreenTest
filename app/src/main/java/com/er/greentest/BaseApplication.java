package com.er.greentest;

import android.app.Application;

import com.er.greentest.gen.DaoMaster;
import com.er.greentest.gen.DaoSession;

import org.greenrobot.greendao.database.Database;

/**
 * Created by Administrator on 2017/8/9.
 */

public class BaseApplication extends Application {
    public static final String DB_NAME = "users-db";
    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper devOpenHelper = new DaoMaster.DevOpenHelper(this, DB_NAME);
        Database writableDb = devOpenHelper.getWritableDb();

        DaoMaster daoMaster = new DaoMaster(writableDb);
        daoSession = daoMaster.newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
