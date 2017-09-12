package com.er.greentest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.er.greentest.view.CircleLoadingView;
import com.er.greentest.view.LearnView;
import com.er.greentest.view.MultitouchView;

import static com.er.greentest.R.id.multiTouchView;


public class ViewActivity extends AppCompatActivity {

    private CircleLoadingView circleLoadingView;
    private LearnView learnView;
    private MultitouchView multitouchView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view);
        circleLoadingView = ((CircleLoadingView) findViewById(R.id.circle));
        circleLoadingView.setProgress(60);
//        circleLoadingView.startDotAnimator();

        learnView = ((LearnView) findViewById(R.id.learnView));
        multitouchView = ((MultitouchView) findViewById(multiTouchView));
    }

    public void onMyViewClick(View view) {
        switch (view.getId()) {
            case R.id.progress:
//                circleLoadingView.setProgressWithAnimator(70);

//                multitouchView.startAnimator();
//                multitouchView.animate()
////                        .translationX(200)
//                        .translationXBy(50)
//                        .alpha(0.5f);

                multitouchView.startAnimator();

                break;
            case R.id.degree:
                learnView.setAnimator();
                break;
        }
    }
}
