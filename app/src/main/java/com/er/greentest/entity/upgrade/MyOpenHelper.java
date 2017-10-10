package com.er.greentest.entity.upgrade;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

import com.er.greentest.gen.CustomerDao;
import com.er.greentest.gen.CustomerOrderDao;
import com.er.greentest.gen.DaoMaster;

import org.greenrobot.greendao.database.Database;

/**
 * Created by THTF on 2017/10/10.
 * Desc:
 */

public class MyOpenHelper extends DaoMaster.OpenHelper {

    public MyOpenHelper(Context context, String name) {
        super(context, name);
    }

    public MyOpenHelper(Context context, String name, SQLiteDatabase.CursorFactory factory) {
        super(context, name, factory);
    }

//    @Override
//    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
//        super.onUpgrade(db, oldVersion, newVersion);
//        MigrationHelper.getInstance().migrate(db, CustomerDao.class,OrderDao.class);
//    }

    @Override
    public void onUpgrade(Database db, int oldVersion, int newVersion) {
        Log.i("greenDAO", "Upgrading old:" + oldVersion + " new:" + newVersion);
        super.onUpgrade(db, oldVersion, newVersion);
        MigrationHelper.getInstance().migrate(db, CustomerDao.class, CustomerOrderDao.class);
    }
}
