package com.er.greentest.dagger2;

import dagger.Component;

/**
 * Created by THTF on 2017/9/27.
 * Desc:
 */
@PerActivity
@Component(dependencies = ApplicationComponent.class, modules = TestModule.class)
public interface DaggerTestComponent {
    void inject(DaggerTestActivity activity);
    void xnject(DaggerTestActivity activity);

//    void inject(SecondActivity activity);
}
