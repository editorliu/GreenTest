package com.er.greentest;

import android.content.Intent;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.graphics.Rect;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.er.greentest.dagger2.DaggerTestActivity;
import com.er.greentest.encreyption.AESUtil;
import com.er.greentest.entity.Test;
import com.er.greentest.entity.tomany.Comment;
import com.er.greentest.entity.tomany.Customer;
import com.er.greentest.gen.CommentDao;
import com.er.greentest.gen.CustomerDao;
import com.er.greentest.gen.DaoSession;
import com.er.greentest.record.AudioActivity;
import com.er.greentest.record.RecordActivity;
import com.er.greentest.xmlparse.PullParse;

import org.greenrobot.greendao.query.QueryBuilder;

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
        Log.w("G", "test");

        String intentLast = getIntent().getStringExtra("intentLast");
        String intent = getIntent().getStringExtra("intent");
        Log.w("MyService", "MainActivity intentLast=" + intentLast + "  inent=" + intent);


        String s1 = "abc" + "d";
        String s2 = "ab" + "cd";
        Log.w("Sing", "s1:" + s1 + " " + s1.hashCode() + " s2:" + s2 + " " + s2.hashCode());

        Test.Inner inner = new Test.Inner();


        Test test = new Test();
        Test.Inner2 inner2 = test.new Inner2();
        String gender = inner2.gender;


        Test test1 = new Test();
        test1.hashSet();


        final LinearLayout viewById = (LinearLayout) findViewById(R.id.scrollTest);
        viewById.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                viewById.getLocationOnScreen(screen);
                viewById.getLocationInWindow(window);
                Log.w("kktext", "getLocationOnScreen:" + screen[0] + "  " + screen[1]+"  getLocationInWindow:" + window[0] + "  " + window[1]+" "+viewById.getTop());

//                viewById.scrollBy(getResources().getDimension(), -10);
                viewById.scrollTo(((int) getResources().getDimension(R.dimen.scroll)), ((int) getResources().getDimension(R.dimen.scroll)));
            }
        });

        isSupportMultiTouch();

        AESUtil.test();
    }

    public void isSupportMultiTouch() {
        PackageManager pm = getPackageManager();
        boolean MULTITOUCH = pm.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH);
        boolean DISTINCT = pm.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH_DISTINCT);
        boolean JAZZHAND = pm.hasSystemFeature(PackageManager.FEATURE_TOUCHSCREEN_MULTITOUCH_JAZZHAND);
        Log.w("MULTI", "--------------MULTITOUCH:"+MULTITOUCH);
        Log.w("MULTI", "--------------DISTINCT:"+DISTINCT);
        Log.w("MULTI", "--------------JAZZHAND:"+JAZZHAND);
    }


    @Override
    protected void onResume() {
        super.onResume();
        Rect rect = new Rect();
        getWindow().getDecorView().getWindowVisibleDisplayFrame(rect);
        Log.w("contains", "top" + rect.top);
    }

    private int[] screen = new int[2];
    private int[] window = new int[2];

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
                break;
            case R.id.notificationActivity:
                startActivity(new Intent(this, NotificationActivity.class));
                break;
            case R.id.dagger:
                startActivity(new Intent(this, DaggerTestActivity.class));
                break;
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
            case R.id.rx2:
                startActivity(new Intent(this, RxActivity.class));
                break;
            case R.id.HG:
                startActivity(new Intent(this, HGActivity.class));
                break;
            case R.id.toOne:
                DaoSession daoSession = app.getDaoSession();

                CustomerDao customerDao = daoSession.getCustomerDao();
                CommentDao commentDao = daoSession.getCommentDao();

//                insert(customerDao, commentDao);
                query(customerDao, commentDao);
//                join(customerDao, commentDao);
                break;

        }
    }

    private void join(final CustomerDao customerDao, final CommentDao commentDao) {
//        QueryBuilder<Customer> customerQueryBuilder = customerDao.queryBuilder();
//        customerQueryBuilder.join(Comment.class, CommentDao.Properties.CustomerId)
//                .where(CommentDao.Properties.Content.eq("你好呀"));
//        List<Customer> list = customerQueryBuilder.list();
//        for (int i = 0; i < list.size(); i++) {
//            Customer customer = list.get(i);
//            Log.w("join", " **************join customer:  "+customer.getId() + " " + customer.getName());
//        }


        QueryBuilder<Comment> commentQueryBuilder = commentDao.queryBuilder();
        commentQueryBuilder.join(CommentDao.Properties.CustomerId, Customer.class, CustomerDao.Properties.Id)
                .where(CustomerDao.Properties.Type.eq(1), CustomerDao.Properties.Address.eq("北京"));
        commentQueryBuilder.where(CommentDao.Properties.ViewCount.eq(100));


        List<Comment> list1 = commentQueryBuilder.list();
        Log.w("join", "--list1:" + list1.size());
        for (int i = 0; i < list1.size(); i++) {
            final Comment comment = list1.get(i);
            Log.w("join", " **************join customer:  " + comment.getContent() + " " + comment.getViewCount() + " " + comment.getCustomer().getName());

            DaoSession daoSession = app.getDaoSession();
            daoSession.runInTx(new Runnable() {
                @Override
                public void run() {
                    Customer customer = new Customer((long) 7, "孙九", 1, "北京");
                    comment.setCustomer(customer);
                    customerDao.insert(customer);
                    commentDao.update(comment);
                    Log.w("join", "--end runInTx");
                }
            });
        }


    }

    private void query(CustomerDao customerDao, CommentDao commentDao) {
        List<Customer> list = customerDao.queryBuilder().list();
        for (int i = 0; i < list.size(); i++) {
            Customer customer = list.get(i);
            Log.w("join", " ------------customer:  " + customer.getId() + " " + customer.getName() + " " + customer.getType() + " " + customer.getAddress());
        }
        List<Comment> list2 = commentDao.queryBuilder().list();
        for (int i = 0; i < list2.size(); i++) {
            Comment comment = list2.get(i);
            Log.w("join", " ------------comment:  " + comment.getId() + " " + comment.getContent() + " " + comment.getCustomerId());
        }
    }

    private void insert(CustomerDao customerDao, CommentDao commentDao) {
        Customer c1 = new Customer(null, "张三", 1, "湖北");
        Customer c2 = new Customer(null, "李四", 1, "北京");
        Customer c3 = new Customer(null, "王五", 2, "北京");
        Customer c4 = new Customer(null, "赵六", 2, "湖北");
        Customer c5 = new Customer(null, "冯七", 1, "北京");
        Customer c6 = new Customer(null, "周八", 1, "湖北");
        customerDao.insertInTx(c1, c2, c3, c4, c5, c6);

        Comment co1 = new Comment(null, "你好呀", 1, 10);
        Comment co2 = new Comment(null, "你真的是一个好人呀", 1, 15);
        Comment co3 = new Comment(null, "好好工作", 1, 18);
        Comment co4 = new Comment(null, "明天会不会下雨", 1, 30);
        Comment co5 = new Comment(null, "晚上几点睡觉？", 1, 40);

        Comment co6 = new Comment(null, "冯七写的评论这是？", 5, 40);
        Comment co7 = new Comment(null, "不可思议的是你们都没有来", 5, 80);
        Comment co8 = new Comment(null, "这个电影真的好看吗？", 5, 12);
        Comment co9 = new Comment(null, "小学生", 5, 100);

        Comment co10 = new Comment(null, "你好呀", 2, 25);
        Comment co11 = new Comment(null, "你好呀", 2, 18);
        Comment co12 = new Comment(null, "这个世界真好", 2, 20);
        commentDao.insertInTx(co1, co2, co3, co4, co5, co6, co7, co8, co9, co10, co11, co12);
    }
}
