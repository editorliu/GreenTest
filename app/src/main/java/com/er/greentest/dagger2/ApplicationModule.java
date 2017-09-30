package com.er.greentest.dagger2;

import android.content.Context;

import dagger.Module;
import dagger.Provides;

/**
 * Created by THTF on 2017/9/28.
 * Desc:
 */
@Module
public class ApplicationModule {
    private final Context context;

    public ApplicationModule(Context context) {
        this.context = context;
    }

    @Provides
    public Context provideContext() {
        return context;
    }
}
