package com.er.greentest.dagger2;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.er.greentest.R;

import javax.inject.Inject;

public class SecondActivity extends AppCompatActivity {

    @Inject
    SecondBean secondBean1;
    @Inject
    SecondBean secondBean2;

//    @Inject
//    SecondMap secondMap1;
//    @Inject
//    SecondMap secondMap2;

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        DaggerSecondComponent.builder()
                .secondModule(new SecondModule())
                .build()
                .inject(this);
        Log.w("Dg", "SecondActivity  --------------   secondBean1:" + secondBean1 + "  secondBean2:" + secondBean2);
//        Log.w("Dg", "SecondActivity  --------------   secondMap1:" + secondMap1 + "  secondMap2:" + secondMap2);
    }
}
