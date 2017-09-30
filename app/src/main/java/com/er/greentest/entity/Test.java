package com.er.greentest.entity;

import android.util.Log;

import java.util.HashSet;
import java.util.Set;

/**
 * Created by THTF on 2017/9/25.
 */

public class Test {
    public static String outerUi;
    public String outerName;
    private int outerAge;

    private void getx() {
        Inner inner = new Inner();
        String phone = inner.innerPhone;
        String gender = inner.innerGender;
    }


    public void hashSet() {
        Set<School> set = new HashSet<School>();
        School p1 = new School("唐僧", 25);
        School p2 = new School("孙悟空", 26);
        School p3 = new School("猪八戒", 27);
        Log.w("HASH_SET","初始化对象:" + p1 +" hashcode："+p1.hashCode());
        Log.w("HASH_SET","初始化对象:" + p2 +" hashcode："+p2.hashCode());
        Log.w("HASH_SET","初始化对象:" + p3 +" hashcode："+p3.hashCode());

        set.add(p1);
        set.add(p2);
        set.add(p3);
        Log.w("HASH_SET","添加后，总共有:" + set.size() + " 个元素!"); //结果：总共有:3 个元素!
        p3.setCode(2); //修改p3的年龄,此时p3元素对应的hashcode值发生改变
        p3.setName("中国");
        Log.w("HASH_SET", "设置p3："+" hashcode："+p3.hashCode());

        boolean remove = set.remove(p3);//此时remove不掉，造成内存泄漏
        Log.w("HASH_SET", "删除"+(remove ? "成功！" : "失败")); //结果：总共有:4 个元素!
        Log.w("HASH_SET","删除后，总共有:" + set.size() + " 个元素!"); //结果：总共有:4 个元素!
        for (School school : set) {
            Log.w("HASH_SET","删除后  School: "+school);
        }

        set.add(p3); //重新添加，居然添加成功
        Log.w("HASH_SET","删除后，再添加，总共有:" + set.size() + " 个元素!"); //结果：总共有:4 个元素!
        for (School school : set) {
            Log.w("HASH_SET","再添加 School: "+school);
        }
    }

    public static class Inner {
        private String innerPhone = "ddddddd";
        public String innerGender = "male";
        private static int a = 10;

        public void innenrMethod() {
            innerGender = outerUi;
        }

        public static void kjl() {
//            int innner = outerAge;
        }
    }

    public class Inner2 {
        private String phone = "ddddddd";
        public String gender = "male";
        public String outerName;

        public void innenrMethod() {
            String outerName = this.outerName;
            String outerName1 = Test.this.outerName;
            phone = outerUi;
            gender = this.outerName;
            int a = outerAge;
        }
    }

}
