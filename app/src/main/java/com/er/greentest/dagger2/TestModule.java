package com.er.greentest.dagger2;

import dagger.Module;
import dagger.Provides;

/**
 * Created by THTF on 2017/9/27.
 *
 */
@Module
public class TestModule {

    private DaggerTestContract.View view;

    public TestModule(DaggerTestContract.View view) {
        this.view = view;
    }

    @Provides
    public DaggerTestContract.View provideDaggerTestView() {
        return view;
    }

    @Provides
    public Integer provideInteger() {
        return 100;
    }

//    @ShopBeanForParams
//    @Provides
//    @PerActivity
//    public ShopBean provideShopByContext(Context context) {
//        return new ShopBean(context);
//    }

    @ShopBeanDefault
    @Provides
    public ShopBean provideShopDefault(Integer integer) {
        return new ShopBean(integer);
    }
}
