package com.er.greentest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.er.greentest.adapter.ExpandAdapter;
import com.er.greentest.adapter.StrBean;

import java.util.ArrayList;

public class ExpandActivity extends AppCompatActivity {

    private RecyclerView recyclerView;
    private ArrayList<StrBean> strings;
    private static String[] contentArray = new String[]{
            "在动作类影片中，只要发生混乱，那么绝对就有木仓战。现在的技术越来越发达，电影或电视中的特效也做的越来越逼真，演员们被木仓打中的效果也很形象，我们经常能看到被木仓打中的伤口血淋林的暴露在大屏幕中，从演员的表演中我们能看到木仓击是很痛的，那么你们有想过被木仓打中到底会有多痛？什么感觉吗？网站有网友为我们分享被子弹打中的感觉1、“老实说，比我想象中的感觉要轻很多。本来我以为很痛，可是被打中后就像是被棒球击中的感觉一样，刚开始的几秒钟没什么知觉，过会才感到2、“被子弹打到的感觉就像是一直有人拿针扎你一样，刺痛刺痛的。3、“我当初大腿被木仓击中，子弹直接从我的大腿中传过去，连带着我的肌腱也被击中，那种感觉我觉得用疼痛两个字已经不足以形容4、“在我十七岁的时候，脚被木仓击中，当时我以为是被蜜蜂蛰了，因为仿佛听到了蜜蜂的声音，没过几秒钟，脚上就传来灼热感，这才知道原来是被木仓击中了。5、“我只是听到的木仓声，却没有意识到自己中木仓了。直到血流出来才意识到。所以，对我来讲，被子弹击中没什么感觉。",
            "如果只是像上面写的那样每次初始化item时去获取文本的行数，然后根据行数选择是否折叠文本的话，会引发一个问题：即已经获取过行数的position item滑出可视范围又滑回来时，根据RecyclerView的复用，TextView又会被重新测量高度行数然后是否折叠，有兴趣的同学可以试试，从列表顶部往下滑是没问题，但从底部往上滑，列表会不断跳动，在文字多的情况下甚至滑不回顶部，因为上面即将进入可视范围的item始终处于measure（展开）和超出行数折叠文本的死循环",
            "一般在社交APP中都有类似朋友圈的功能，其中发表的动态内容很长的时候不可能让它全部显示。这里就需要做一个仿微信朋友圈全文、收起功能来解决该问题。在网上看到一个例子--> http://blog.csdn.net/e042kuuw/article/details/55107537 ，写的很不错，但是有个bug，他这个Demo只有在条目固定的时候才正常，当增加、删除条目的时候会出现全文、收起显示混乱的问题。",
            "安装完成插件后，会提示重启AS，重启完后，可以写一个布局并且新建一个代码类测试下。测试的过程中要注意的是",
            "最近在看GitHub上的一些代码时，发现很多工程都用到了Butter Knife这个框架，能节省很多代码量。像findViewById这种代码就不用再出现了，而且这个框架也提供了很多其他有用的注解。\n" +
                    "抱着学习的心态看了官网上的文档，挺简单，也很实用，决定以后就用这个库了。\n" +
                    "下面是我翻译的官方文档，诸位看官轻喷。官方文档也挺简单，英语好的不好的，都建议去看看原文。\n" +
                    "\n" +
                    "作者：545a3c856c5f\n" +
                    "链接：http://www.jianshu.com/p/9ad21e548b69\n" +
                    "來源：简书\n" +
                    "著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。",
            "另外注意，这个库的版本更新挺快的，我第一次用到的时候是7.1.0，而现在的最新版本已经是8.5.1了，也就是说大家可能需要去ButterKnife的Github查看最近的版本。",
            "抱着学习的心态看了官网上的文档，挺简单，也很实用，决定以后就用这个库了。\n" +
                    "下面是我翻译的官方文档，诸位看官轻喷。官方文档也挺简单，英语好的不好的，都建议去看看原文。",
            "使用ButterKnife.bind(this)绑定一个布局的子布局。如果你在布局中使用了<merge>标签并且在自定义的控件构造时inflate这个布局，你可以在inflate之后立即调用它。或者，你可以在onFinishInflate()回调中使用它。",
            "Fragment的生命周期与Activity不同。在Fragment中，如果你在onCreateView中使用绑定，那么你需要在onDestroyView中设置所有view为null。为此，ButterKnife返回一个Unbinder实例以便于你进行这项处理。在合适的生命周期回调中调用unbind函数就可完成重置。\n" +
                    "\n" +
                    "作者：545a3c856c5f\n" +
                    "链接：http://www.jianshu.com/p/9ad21e548b69\n" +
                    "來源：简书\n" +
                    "著作权归作者所有。商业转载请联系作者获得授权，非商业转载请注明出处。"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_expand);
        strings = new ArrayList<>();
        for (int i = 0; i < contentArray.length; i++) {
            strings.add(new StrBean(i,"这是" + i+"__"+contentArray[i]));

        }
        recyclerView = (RecyclerView) findViewById(R.id.rc);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        recyclerView.setAdapter(new ExpandAdapter(this, strings));
    }
}
