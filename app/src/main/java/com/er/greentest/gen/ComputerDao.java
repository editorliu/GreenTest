package com.er.greentest.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.er.greentest.entity.Computer;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "COMPUTER".
*/
public class ComputerDao extends AbstractDao<Computer, Long> {

    public static final String TABLENAME = "COMPUTER";

    /**
     * Properties of entity Computer.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Brand = new Property(1, String.class, "brand", false, "BRAND");
        public final static Property Price = new Property(2, Double.class, "price", false, "PRICE");
        public final static Property Date = new Property(3, java.util.Date.class, "date", false, "DATE");
    }

    private DaoSession daoSession;


    public ComputerDao(DaoConfig config) {
        super(config);
    }
    
    public ComputerDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"COMPUTER\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"BRAND\" TEXT," + // 1: brand
                "\"PRICE\" REAL," + // 2: price
                "\"DATE\" INTEGER);"); // 3: date
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"COMPUTER\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, Computer entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String brand = entity.getBrand();
        if (brand != null) {
            stmt.bindString(2, brand);
        }
 
        Double price = entity.getPrice();
        if (price != null) {
            stmt.bindDouble(3, price);
        }
 
        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(4, date.getTime());
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, Computer entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String brand = entity.getBrand();
        if (brand != null) {
            stmt.bindString(2, brand);
        }
 
        Double price = entity.getPrice();
        if (price != null) {
            stmt.bindDouble(3, price);
        }
 
        java.util.Date date = entity.getDate();
        if (date != null) {
            stmt.bindLong(4, date.getTime());
        }
    }

    @Override
    protected final void attachEntity(Computer entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public Computer readEntity(Cursor cursor, int offset) {
        Computer entity = new Computer( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // brand
            cursor.isNull(offset + 2) ? null : cursor.getDouble(offset + 2), // price
            cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)) // date
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, Computer entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setBrand(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setPrice(cursor.isNull(offset + 2) ? null : cursor.getDouble(offset + 2));
        entity.setDate(cursor.isNull(offset + 3) ? null : new java.util.Date(cursor.getLong(offset + 3)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(Computer entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(Computer entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(Computer entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}