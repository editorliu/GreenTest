package com.er.greentest.dagger2;

import javax.inject.Singleton;

import dagger.Component;

/**
 * Created by THTF on 2017/9/28.
 * Desc:
 */
@Singleton
@Component(modules = SecondModule.class)
public interface SecondComponent {
    void inject(SecondActivity activity);
}
