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

import com.er.greentest.dagger2.DaggerTestActivity;
import com.er.greentest.entity.Computer;
import com.er.greentest.entity.Road;
import com.er.greentest.entity.Test;
import com.er.greentest.entity.User;
import com.er.greentest.entity.one.Score;
import com.er.greentest.entity.one.Student;
import com.er.greentest.entity.tomany.Customer;
import com.er.greentest.entity.tomany.CustomerOrder;
import com.er.greentest.gen.ComputerDao;
import com.er.greentest.gen.CustomerDao;
import com.er.greentest.gen.CustomerOrderDao;
import com.er.greentest.gen.DaoSession;
import com.er.greentest.gen.RoadDao;
import com.er.greentest.gen.ScoreDao;
import com.er.greentest.gen.StudentDao;
import com.er.greentest.gen.UserDao;
import com.er.greentest.record.AudioActivity;
import com.er.greentest.record.RecordActivity;
import com.er.greentest.xmlparse.PullParse;

import org.greenrobot.greendao.query.LazyList;
import org.greenrobot.greendao.query.Query;
import org.greenrobot.greendao.query.QueryBuilder;
import org.greenrobot.greendao.query.WhereCondition;

import java.io.IOException;
import java.util.Date;
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
            case R.id.toMany:

                DaoSession daoSession2 = app.getDaoSession();

                CustomerDao customerDao = daoSession2.getCustomerDao();
                Customer customer1 = new Customer(null, "张三");
                Customer customer2 = new Customer(null, "李四");
                Customer customer3 = new Customer(null, "王五");
                Customer customer4 = new Customer(null, "赵六");
                Customer customer5 = new Customer(null, "周七");
//                customerDao.insertInTx(customer1, customer2, customer3, customer4, customer5);

                List<Customer> listCustomer = customerDao.queryBuilder().list();
                for (Customer customer : listCustomer) {
                    customer.resetCustomerOrderList();
                    Log.w("customer", "---------------customer:" +customer.getId()+" "+customer.getName());
                    List<CustomerOrder> customerOrderList = customer.getCustomerOrderList();
                    for (int i = 0; i < customerOrderList.size(); i++) {
                        Log.w("customer", "---------------customer order:"+ customerOrderList.get(i).getItemName());
                    }
                }

                CustomerOrderDao customerOrderDao = daoSession2.getCustomerOrderDao();
                CustomerOrder order1 = new CustomerOrder(null, "苹果", 1);
                CustomerOrder order2 = new CustomerOrder(null, "猕猴桃", 1);
                CustomerOrder order3 = new CustomerOrder(null, "香蕉", 2);
                CustomerOrder order4 = new CustomerOrder(null, "橘子", 3);
                CustomerOrder order5 = new CustomerOrder(null, "苹果", 4);
                CustomerOrder order6 = new CustomerOrder(null, "柚子", 5);
                CustomerOrder order7 = new CustomerOrder(null, "葡萄", 5);
                CustomerOrder order8 = new CustomerOrder(null, "樱桃", 5);
//                customerOrderDao.insertInTx(order1,order2,order3,order4,order5,order6,order7,order8);
//                List<CustomerOrder> listCustomerOrder = customerOrderDao.queryBuilder().list();
//                for(CustomerOrder customerOrder : listCustomerOrder){
//                    Log.w("customer", "---------------customerOrder:"+ customerOrder.getItemName()+"***************");
//                }

                break;
            case R.id.toOne:
                DaoSession daoSession1 = app.getDaoSession();
                Student a = new Student(null, "a", 16, 1);
                Student b = new Student(null, "b", 15, 3);
                Student c = new Student(null, "c", 18, 2);
                Student d = new Student(null, "d", 15, 5);
                Student e = new Student(null, "e", 17, 4);


                Student test = new Student(null, "e", 19, 4);

                StudentDao studentDao = daoSession1.getStudentDao();
//                studentDao.insertInTx(a,b,c,d,e);
//                studentDao.insert(test);
                List<Student> listStudent = studentDao.queryBuilder().list();

                for (int i = 0; i < listStudent.size(); i++) {
                    Student student = listStudent.get(i);
                    Log.w("score", "---------------student:" + student.getScore() + "  " + student.getScore().getId() + " " + student.getScore().getPoint());
                }

                Score score1 = new Score(null, 70);
                Score score2 = new Score(null, 85);
                Score score3 = new Score(null, 100);
                Score score4 = new Score(null, 99);
                Score score5 = new Score(null, 100);
                ScoreDao scoreDao = daoSession1.getScoreDao();
//                scoreDao.insertInTx(score1,score2,score3,score4,score5);

                List<Score> listScore = scoreDao.queryBuilder().list();
                for (int i = 0; i < listScore.size(); i++) {
                    Log.w("score", "---------------score:" + listScore.get(i).getId());
                }

                break;
            case R.id.add:
                DaoSession daoSession = app.getDaoSession();
//                UserDao userDao = daoSession.getUserDao();
//                userDao.insert(new User(null, "网二码子3.0", 30));
//                Log.w("GR", "userDao:" + userDao);
//                daoSession.getDeskDao().insert(new Desk(null, 18, 30, 38));
//                daoSession.getRoadDao().insert(new Road(null, "朝阳北路"));
//                daoSession.getRoadDao().insert(new Road(null, "四惠东路"));
//                List<Phone> phones = daoSession.getPhoneDao().loadAll();
//                Log.w("GR", "phones:" + phones.size());
//
//                DeskDao deskDao = daoSession.getDeskDao();
//                List<Desk> list = deskDao.queryBuilder()
//                        .where(DeskDao.Properties.Height.eq(11))
//                        .list();
//
//
//                QueryBuilder<Desk> deskQueryBuilder = deskDao.queryBuilder();
//                deskQueryBuilder.where(DeskDao.Properties.Length.gt(10),
//                        deskQueryBuilder.or(DeskDao.Properties.Id.between(20,25),DeskDao.Properties.Height.lt(18))
//                );
//                List<Desk> list1 = deskQueryBuilder.limit(10).orderAsc(DeskDao.Properties.Id).list();


                ComputerDao computerDao = daoSession.getComputerDao();

                computerDao.insert(new Computer(null, "联想", 180.9080, new Date()));
                computerDao.insert(new Computer(null, "华为", 98.76, new Date()));
                computerDao.insert(new Computer(null, "魅族", 35.90, new Date()));
                computerDao.insert(new Computer(null, "小米", 15d, new Date()));

                //事务
                computerDao.insertInTx(new Computer(null, "", 277d, new Date()));
                computerDao.deleteInTx();
                computerDao.updateInTx();


//                List<Computer> list = computerDao.queryBuilder().list();
//                StringBuilder stringBuilder = new StringBuilder();
//                for (int i = 0; i < list.size(); i++) {
//                    Computer computer = list.get(i);
//                    stringBuilder.append(i + ":" + computer + " " + computer.getId() + " " + computer.getPrice() + " " + computer.getBrand() + " " + computer.getDate());
//                    stringBuilder.append("\n");
//                }
//                Log.w("DATA", stringBuilder.toString());


//                Log.w("DATA", ComputerDao.Properties.Brand.columnName+"\n");
//                Log.w("DATA", ComputerDao.Properties.Brand.name+"\n");
//                Log.w("DATA", ComputerDao.Properties.Brand.primaryKey+"\n");
//                Log.w("DATA", ComputerDao.Properties.Brand.ordinal+"\n");


                QueryBuilder.LOG_SQL = true;
                QueryBuilder.LOG_VALUES = true;

                ComputerDao computerDao1 = daoSession.getComputerDao();
                QueryBuilder<Computer> computerQueryBuilder = computerDao1.queryBuilder();
//                computerQueryBuilder.where(ComputerDao.Properties.Brand.eq("联想"));
//                Query<Computer> query = computerQueryBuilder.build();
//
//                query.forCurrentThread();
//                query.setParameter(0, "华为");
//                List<Computer> list = query.list();
//                for (int i = 0; i < list.size(); i++) {
//                    Log.w("DATA", list.get(i).getBrand());
//                }

//                String sql = "select * from "+ComputerDao.TABLENAME;
                String sql = ComputerDao.Properties.Brand.columnName + " = \"联想\"";
                computerQueryBuilder.where(new WhereCondition.StringCondition(sql));
//                computerQueryBuilder.unique()
                LazyList<Computer> computers = computerQueryBuilder.listLazy();
                computers.close();

                Query<Computer> build = computerQueryBuilder.build();
                List<Computer> list1 = build.list();
//                List<Computer> list1 = computerQueryBuilder.list();
                for (int i = 0; i < list1.size(); i++) {
                    Log.w("DATA", list1.get(i).getBrand());
                }

                computerDao1.queryBuilder().where(ComputerDao.Properties.Brand.gt(""))
                        .orderDesc(ComputerDao.Properties.Brand)
                        .limit(10)
                        .offset(2)
                        .list();

//                去除缓存
//                daoSession.clear();
//                daoSession.getComputerDao().detach(new Computer());
//                daoSession.getComputerDao().detachAll();

                computerDao1.queryRaw("", "");
                Query<Computer> query = computerDao1.queryRawCreate("where", "p1", "p2", "p3");

//                Computer unique = query.unique();
//                List<Computer> list = query.list();
//
//                LazyList<Computer> computers = query.listLazy();
//                computers.close();
//
//                CloseableListIterator<Computer> computerCloseableListIterator = query.listIterator();
//                try {
//                    computerCloseableListIterator.close();
//                } catch (IOException e) {
//                    e.printStackTrace();
//                }


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
