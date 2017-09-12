package com.er.greentest.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.er.greentest.entity.Desk;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "DESK".
*/
public class DeskDao extends AbstractDao<Desk, Long> {

    public static final String TABLENAME = "DESK";

    /**
     * Properties of entity Desk.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Length = new Property(1, double.class, "length", false, "LENGTH");
        public final static Property Width = new Property(2, double.class, "width", false, "WIDTH");
        public final static Property Height = new Property(3, double.class, "height", false, "HEIGHT");
    }


    public DeskDao(DaoConfig config) {
        super(config);
    }
    
    public DeskDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"DESK\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"LENGTH\" REAL NOT NULL ," + // 1: length
                "\"WIDTH\" REAL NOT NULL ," + // 2: width
                "\"HEIGHT\" REAL NOT NULL );"); // 3: height
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"DESK\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Desk entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindDouble(2, entity.getLength());
        stmt.bindDouble(3, entity.getWidth());
        stmt.bindDouble(4, entity.getHeight());
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Desk entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
        stmt.bindDouble(2, entity.getLength());
        stmt.bindDouble(3, entity.getWidth());
        stmt.bindDouble(4, entity.getHeight());
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Desk readEntity(Cursor cursor, int offset) {
        Desk entity = new Desk( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.getDouble(offset + 1), // length
            cursor.getDouble(offset + 2), // width
            cursor.getDouble(offset + 3) // height
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Desk entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setLength(cursor.getDouble(offset + 1));
        entity.setWidth(cursor.getDouble(offset + 2));
        entity.setHeight(cursor.getDouble(offset + 3));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Desk entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Desk entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Desk entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}