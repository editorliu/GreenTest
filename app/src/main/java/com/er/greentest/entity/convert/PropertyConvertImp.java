package com.er.greentest.entity.convert;

import org.greenrobot.greendao.converter.PropertyConverter;

/**
 * Created by THTF on 2017/9/29.
 * Desc:
 */

public class PropertyConvertImp implements PropertyConverter<ConvertDes,Integer>{
    @Override
    public ConvertDes convertToEntityProperty(Integer databaseValue) {
        return new ConvertDes(databaseValue);
    }

    @Override
    public Integer convertToDatabaseValue(ConvertDes entityProperty) {
        return entityProperty.getId();
    }
}
