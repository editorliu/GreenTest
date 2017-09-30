package com.er.greentest.dagger2;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by THTF on 2017/9/27.
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {

}
