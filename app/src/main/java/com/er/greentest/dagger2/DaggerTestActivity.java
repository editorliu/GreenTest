package com.er.greentest.dagger2;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.er.greentest.BaseApplication;
import com.er.greentest.R;

import javax.inject.Inject;

public class DaggerTestActivity extends AppCompatActivity implements DaggerTestContract.View {

    @Inject
    DaggerTestPresenter presenter;
    @Inject
    Integer integer;
//    @ShopBeanForParams
//    @Inject
//    ShopBean shopBean1;
    @ShopBeanDefault
    @Inject
    ShopBean shopBean2;
    @Inject
    ShopBean shopBean3;

    @Inject
    Map map1;
    @Inject
    Map map2;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dagger_test);
        DaggerTestComponent component = DaggerDaggerTestComponent.builder()
                .applicationComponent(((BaseApplication) getApplication()).getApplicationComponent())
                .testModule(new TestModule(this))
                .build();
        component.inject(this);
    }

    public void onDaggerClick(View view) {
        switch (view.getId()) {
            case R.id.data:

//                if (shopBean1 != null) {
//                    Log.w("Dg", "shopBean1:不空  " + shopBean1.getName() + "  " + shopBean1.toString());
//                } else {
//                    Log.w("Dg", "shopBean1:空");
//                }
//
//                if (shopBean2 != null) {
//                    Log.w("Dg", "shopBean2:不空  " + shopBean2.getName() + "  " + shopBean2.toString());
//                } else {
//                    Log.w("Dg", "shopBean2:空");
//                }
//                if (shopBean3 != null) {
//                    Log.w("Dg", "shopBean3:不空  " + shopBean3.getName() + "  " + shopBean3.toString());
//                } else {
//                    Log.w("Dg", "shopBean3:空");
//                }

//
//                if (presenter != null) {
//                    presenter.getData("你好，中国");
//                }

                Log.w("Dg", "map1:" + map1 + "  map2:" + map2);
                startActivity(new Intent(this, SecondActivity.class));
                break;
        }
    }

    @Override
    public void refreshUi(String ui) {
        Toast.makeText(this, "activity get ui refresh:" + ui, Toast.LENGTH_SHORT).show();
    }
}
