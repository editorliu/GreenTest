package com.er.greentest.gen;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.er.greentest.entity.convert.ConvertDes;
import com.er.greentest.entity.convert.PropertyConvertImp;

import com.er.greentest.entity.convert.ConvertSource;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "CONVERT_SOURCE".
*/
public class ConvertSourceDao extends AbstractDao<ConvertSource, Long> {

    public static final String TABLENAME = "CONVERT_SOURCE";

    /**
     * Properties of entity ConvertSource.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property Name = new Property(1, String.class, "name", false, "NAME");
        public final static Property ConvertDes = new Property(2, Integer.class, "convertDes", false, "CONVERT_DES");
    }

    private final PropertyConvertImp convertDesConverter = new PropertyConvertImp();

    public ConvertSourceDao(DaoConfig config) {
        super(config);
    }
    
    public ConvertSourceDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"CONVERT_SOURCE\" (" + //
                "\"_id\" INTEGER PRIMARY KEY ," + // 0: id
                "\"NAME\" TEXT," + // 1: name
                "\"CONVERT_DES\" INTEGER);"); // 2: convertDes
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"CONVERT_SOURCE\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, ConvertSource entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        ConvertDes convertDes = entity.getConvertDes();
        if (convertDes != null) {
            stmt.bindLong(3, convertDesConverter.convertToDatabaseValue(convertDes));
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, ConvertSource entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        String name = entity.getName();
        if (name != null) {
            stmt.bindString(2, name);
        }
 
        ConvertDes convertDes = entity.getConvertDes();
        if (convertDes != null) {
            stmt.bindLong(3, convertDesConverter.convertToDatabaseValue(convertDes));
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public ConvertSource readEntity(Cursor cursor, int offset) {
        ConvertSource entity = new ConvertSource( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1), // name
            cursor.isNull(offset + 2) ? null : convertDesConverter.convertToEntityProperty(cursor.getInt(offset + 2)) // convertDes
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, ConvertSource entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setName(cursor.isNull(offset + 1) ? null : cursor.getString(offset + 1));
        entity.setConvertDes(cursor.isNull(offset + 2) ? null : convertDesConverter.convertToEntityProperty(cursor.getInt(offset + 2)));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(ConvertSource entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(ConvertSource entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(ConvertSource entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
