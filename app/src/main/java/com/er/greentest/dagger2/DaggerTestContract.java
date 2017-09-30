package com.er.greentest.dagger2;

/**
 * Created by THTF on 2017/9/27.
 *
 */

public interface DaggerTestContract {
     interface View {
         void refreshUi(String ui);
    }

    interface Presenter {
        void getData(String params);
    }

}
