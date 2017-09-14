package com.er.greentest;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.er.greentest.entity.Desk;
import com.er.greentest.entity.Phone;
import com.er.greentest.entity.Road;
import com.er.greentest.entity.User;
import com.er.greentest.gen.DaoSession;
import com.er.greentest.gen.RoadDao;
import com.er.greentest.gen.UserDao;
import com.er.greentest.record.AudioActivity;
import com.er.greentest.record.RecordActivity;
import com.er.greentest.xmlparse.PullParse;

import java.io.IOException;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private BaseApplication app;
    private TextView version;
    private TextView showView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        app = ((BaseApplication) getApplication());
        version = ((TextView) findViewById(R.id.version));
        PackageManager pm = getPackageManager();//context为当前Activity上下文
        PackageInfo pi = null;
        try {
            pi = pm.getPackageInfo(getPackageName(), 0);
            version.setText("版本号：" + pi.versionCode);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }

        showView = ((TextView) findViewById(R.id.view));
    }

    public static final String[] NAMES = {"张三", "李四", "王五", "赵六",};

    public void onBtnClick(View view) {
        switch (view.getId()) {
            case R.id.PARSE:
//                try {
//                    new SAXParse().parse(getAssets().open("books"));
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }

                try {
                    new PullParse().parse(getAssets().open("books"));
                } catch (IOException e) {
                    e.printStackTrace();
                }


                break;
            case R.id.EX:
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=1531392329,2869685141&fm=200&gp=0.jpg"));
                startActivity(intent);


//                startActivity(new Intent(this, ExpandActivity.class));
                break;
            case R.id.View:
                startActivity(new Intent(this, ViewActivity.class));
            case R.id.service:
                startActivity(new Intent(this, ServiceActivity.class));
                break;
            case R.id.audio:
                startActivity(new Intent(this, AudioActivity.class));
                break;
            case R.id.record:
                startActivity(new Intent(this, RecordActivity.class));
                break;
            case R.id.TP:
                startActivity(new Intent(this, TakePictureActivity.class));
                break;
            case R.id.HG:
                startActivity(new Intent(this, HGActivity.class));
                break;
            case R.id.add:
                DaoSession daoSession = app.getDaoSession();
                UserDao userDao = daoSession.getUserDao();
                userDao.insert(new User(null, "网二码子3.0", 30));
                Log.w("GR", "userDao:" + userDao);
                daoSession.getDeskDao().insert(new Desk(null, 18, 30, 38));
                daoSession.getRoadDao().insert(new Road(null, "朝阳北路"));
                daoSession.getRoadDao().insert(new Road(null, "四惠东路"));
                List<Phone> phones = daoSession.getPhoneDao().loadAll();
                Log.w("GR", "phones:" + phones.size());

                break;
            case R.id.query:
                UserDao userDao1 = app.getDaoSession().getUserDao();
                Log.w("GR", "userDao1:" + userDao1);
                List<User> users = userDao1.loadAll();
                StringBuffer stringBuffer = new StringBuffer();
                for (int i = 0; i < users.size(); i++) {
                    User user = users.get(i);
                    stringBuffer.append(user.getId())
                            .append(" ")
                            .append(user.getName())
                            .append(" ")
                            .append(user.getAge())
                            .append("\n");
                }

                RoadDao roadDao = app.getDaoSession().getRoadDao();
                List<Road> roads = roadDao.loadAll();
                for (int i = 0; i < roads.size(); i++) {
                    Road road = roads.get(i);
                    stringBuffer.append("路  ")
                            .append(road.getId())
                            .append(" ")
                            .append(road.getName())
                            .append("\n");
                }

                showView.setText(stringBuffer.toString() + "");


                break;
        }
    }
}
