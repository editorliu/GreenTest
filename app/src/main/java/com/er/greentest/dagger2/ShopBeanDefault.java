package com.er.greentest.dagger2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Qualifier;

/**
 * Created by THTF on 2017/9/27.
 */
@Qualifier
@Retention(RetentionPolicy.RUNTIME)
public @interface ShopBeanDefault {
}
