package com.er.greentest.entity.convert;

import org.greenrobot.greendao.annotation.Convert;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Generated;

/**
 * Created by THTF on 2017/9/29.
 * Desc:
 */

@Entity
public class ConvertSource {
    @Id
    private Long id;
    private String name;
    @Convert(columnType = Integer.class, converter = PropertyConvertImp.class)
    private ConvertDes convertDes;
    @Generated(hash = 1347180671)
    public ConvertSource(Long id, String name, ConvertDes convertDes) {
        this.id = id;
        this.name = name;
        this.convertDes = convertDes;
    }
    @Generated(hash = 1946278859)
    public ConvertSource() {
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
    public ConvertDes getConvertDes() {
        return this.convertDes;
    }
    public void setConvertDes(ConvertDes convertDes) {
        this.convertDes = convertDes;
    }
}
