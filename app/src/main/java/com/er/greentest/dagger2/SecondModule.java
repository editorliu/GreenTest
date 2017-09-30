package com.er.greentest.dagger2;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;

/**
 * Created by THTF on 2017/9/28.
 * Desc:
 */
@Module
public class SecondModule {

    @Provides
    @Singleton
    public SecondMap provideSecondMap() {
        return new SecondMap("103","108");
    }

}
