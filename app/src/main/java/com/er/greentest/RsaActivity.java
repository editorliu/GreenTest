package com.er.greentest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Base64;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.security.InvalidKeyException;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.NoSuchAlgorithmException;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

import javax.crypto.BadPaddingException;
import javax.crypto.Cipher;
import javax.crypto.IllegalBlockSizeException;
import javax.crypto.NoSuchPaddingException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class RsaActivity extends AppCompatActivity {

    @BindView(R.id.encode)
    Button encode;
    @BindView(R.id.decode)
    Button decode;
    @BindView(R.id.show)
    TextView showText;

    private final String TAG = "RSA_LOG";
    private final String txt = "你好呀，同学1234567890";
    private String algorithm = "RSA";
    private int keySize = 1024;
//d7348346ceb7725d5da50dbbd0a1a4d852cc304fcca8a808a54d51093e0ff000e4a809be5c48c42522288ed3e9058f0e45c03036d2f07c3886287e8f5d173f3b,publicExponent=100
//d7348346ceb7725d5da50dbbd0a1a4d852cc304fcca8a808a54d51093e0ff000e4a809be5c48c42522288ed3e9058f0e45c03036d2f07c3886287e8f5d173f3b,publicExponent=10001

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rsa);
        ButterKnife.bind(this);
//        String encryption = RSAUtil.encryption("这是待加密的数据1234567890测试+-*/nihao");
        //加密数据不能太长    加密数据长度（byte字节）不能超过keySize（bit）/8-11
        String data = "秋天，无论在什么地方的秋天，总是好的；可是啊，北国的秋，却特别地来得清，来得静，来得悲凉。我的不远千里，要从杭州赶上青岛，更要从青岛赶上北平来的理由，也不过想饱尝一尝这“秋”，这故都的秋味。江南，秋当然也是有的，但草木凋得慢，空气来得润，天的颜色显得淡，并且又时常多雨而少风；一个人夹在苏州上海杭州，或厦门香港广州的市民中间，混混沌沌地过去，只能感到一点点清凉，秋的味，秋的色，秋的意境与姿态，总看不饱，尝不透，赏玩不到十足。秋并不是名花，也并不是美酒，那一种半开、半醉的状态，在领略秋的过程上，是不合适的。\n" +
                "不逢北国之秋，已将近十余年了。在南方每年到了秋天，总要想起陶然亭（1）的芦花，钓鱼台（2）的柳影，西山（3）的虫唱，玉泉（4）的夜月，潭柘寺（5）的钟声。在北平即使不出门去吧，就是在皇城人海之中，租人家一椽（6）破屋来住着，早晨起来，泡一碗浓茶，向院子一坐，你也能看得到很高很高的碧绿的天色，听得到青天下驯鸽的飞声。从槐树叶底，朝东细数着一丝一丝漏下来的日光，或在破壁腰中，静对着像喇叭似的牵牛花（朝荣）的蓝朵，自然而然地也能够感觉到十分的秋意。说到了牵牛花，我以为以蓝色或白色者为佳，紫黑色次之，淡红色最下。最好，还要在牵牛花底，叫长着几根疏疏落落的尖细且长的秋草，使作陪衬。\n" +
                "北国的槐树，也是一种能使人联想起秋来的点缀。像花而又不是花的那一种落蕊，早晨起来，会铺得满地。脚踏上去，声音也没有，气味也没有，只能感出一点点极微细极柔软的触觉。扫街的在树影下一阵扫后，灰土上留下来的一条条扫帚的丝纹，看起来既觉得细腻，又觉得清闲，潜意识下并且还觉得有点儿落寞（7），古人所说的梧桐一叶而天下知秋（8）的遥想，大约也就在这些深沉的地方。\n" +
                "秋蝉的衰弱的残声，更是北国的特产，因为北平处处全长着树，屋子又低，所以无论在什么地方，都听得见它们的啼唱。在南方是非要上郊外或山上去才听得到的。这秋蝉的嘶叫，在北方可和蟋蟀耗子一样，简直像是家家户户都养在家里的家虫。\n" +
                "还有秋雨哩，北方的秋雨，也似乎比南方的下得奇，下得有味，下得更像样。在灰沉沉的天底下，忽而来一阵凉风，便息列索落地下起雨来了。一层雨过，云渐渐地卷向了西去，天又晴了，太阳又露出脸来了，着（9）着很厚的青布单衣或夹袄的都市闲人，咬着烟管，在雨后的斜桥影里，上桥头树底下去一立，遇见熟人，便会用了缓慢悠闲的声调，微叹着互答着地说：\n" +
                "“唉，天可真凉了——”（这了字念得很高，拖得很长。）“可不是吗？一层秋雨一层凉了！”北方人念阵字，总老像是层字，平平仄仄起来（10），这念错的歧韵，倒来得正好。北方的果树，到秋天，也是一种奇景。第一是枣子树，屋角，墙头，茅房边上，灶房门口，它都会一株株地长大起来。像橄榄又像鸽蛋似的这枣子颗儿，在小椭圆形的细叶中间，显出淡绿微黄的颜色的时候，正是秋的全盛时期，等枣树叶落，枣子红完，西北风就要起来了，北方便是沙尘灰土的世界，只有这枣子、柿子、葡萄，成熟到八九分的七八月之交，是北国的清秋的佳日，是一年之中最好也没有的Golden Days（11）。\n" +
                "有些批评家说，中国的文人学士，尤其是诗人，都带着很浓厚的颓废的色彩，所以中国的诗文里，赞颂秋的文字的特别的多。但外国的诗人，又何尝不然？我虽则外国诗文念的不多，也不想开出帐来，做一篇秋的诗歌散文钞（12），但你若去一翻英德法意等诗人的集子，或各国的诗文的Anthology来（13），总能够看到许多并于秋的歌颂和悲啼。各著名的大诗人的长篇田园诗或四季诗里，也总以关于秋的部分，写得最出色而最有味。足见有感觉的动物，有情趣的人类，对于秋，总是一样地特别能引起深沉，幽远、严厉、萧索的感触来的。不单是诗人，就是被关闭在牢狱里的囚犯，到了秋天，我想也一定能感到一种不能自已的深情，秋之于人，何尝有国别，更何尝有人种阶级的区别呢？不过在中国，文字里有一个“秋士”（14）的成语，读本里又有着很普遍的欧阳子的《秋声》（15）与苏东坡的《赤壁赋》等，就觉得中国的文人，与秋和关系特别深了，可是这秋的深味，尤其是中国的秋的深味，非要在北方，才感受得到底。\n" +
                "南国之秋，当然也是有它的特异的地方的，比如廿四桥的明月，钱塘江的秋潮，普陀山的凉雾，荔枝湾（16）的残荷等等，可是色彩不浓，回味不永。比起北国的秋来，正像是黄酒之与白干，稀饭之与馍馍，鲈鱼之与大蟹，黄犬之与骆驼。\n" +
                "秋天，这北国的秋天，若留得住的话，我愿把寿命的三分之二折去，换得一个三分之一的零头。一九三四年八月在北平";
//        Log.d(TAG, "原文长度："+data.length());
//        String encryption = RSAUtil.encryption(data);
//        Log.d(TAG, "加密结果："+encryption);
//        String decryption = RSAUtil.decryptionSplit(encryption);
//        Log.d(TAG, "解密结果："+decryption);
//        Log.d(TAG, "解密结果长度："+decryption.length());
//        showText.setText(decryption);

//        String encrypt = MD5Util.encrypt(data);
//        String encrypt2 = MD5Util.encrypt2(data);
//        Log.w(TAG, "MD5加密后1：" + encrypt+" 长度："+encrypt.length());
//        Log.w(TAG, "MD5加密后2：" + encrypt2+" 长度："+encrypt2.length());


//        try {
//            InputStream books = getAssets().open("qinghai.apk");
//            String s = MD5Util.encryptFile(books);
//            Log.w(TAG, "MD5加密后文件：" + s+" 长度："+s.length());
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


//        String s = MD5Util.encryptSalt(data);
//        boolean b = MD5Util.verifySalt(data, s);
//        Log.w(TAG, "加盐加密：" + s+" 验证："+b);


        AesUtil.key("324");
    }


    @OnClick({R.id.encode, R.id.decode})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.encode:

                try {
                    KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance(algorithm);
                    keyPairGenerator.initialize(keySize);//密钥长度，范围：512～2048 一般1024

                    KeyPair keyPair = keyPairGenerator.generateKeyPair();
                    RSAPrivateKey aPrivate = ((RSAPrivateKey) keyPair.getPrivate());
                    RSAPublicKey aPublic = ((RSAPublicKey) keyPair.getPublic());

                    Log.d(TAG, "private key:"+aPrivate);
                    Log.d(TAG, "public key:"+aPublic);


                    X509EncodedKeySpec x509EncodedKeySpec = new X509EncodedKeySpec(aPublic.getEncoded());
                    KeyFactory keyFactory = KeyFactory.getInstance(algorithm);
                    try {
                        PublicKey publicKey = keyFactory.generatePublic(x509EncodedKeySpec);

                        try {
                            Cipher cipher = Cipher.getInstance("RSA/ECB/PKCS1Padding");//Cipher.getInstance()参数为填充模式
                            try {
                                cipher.init(Cipher.ENCRYPT_MODE,publicKey);
                                try {
                                    byte[] bytes = cipher.doFinal(txt.getBytes());
                                    Log.d(TAG, "加密结果："+ Base64.encodeToString(bytes,Base64.DEFAULT));


                                    PKCS8EncodedKeySpec pkcs8EncodedKeySpec = new PKCS8EncodedKeySpec(aPrivate.getEncoded());
                                    KeyFactory keyFactory1 = KeyFactory.getInstance(algorithm);
                                    PrivateKey privateKey = keyFactory1.generatePrivate(pkcs8EncodedKeySpec);
                                    Cipher cipher1 = Cipher.getInstance(algorithm);
                                    cipher1.init(Cipher.DECRYPT_MODE,privateKey);
                                    byte[] bytes1 = cipher1.doFinal(bytes);
                                    Log.d(TAG, "解密："+new String(bytes1));


                                } catch (IllegalBlockSizeException e) {
                                    e.printStackTrace();
                                } catch (BadPaddingException e) {
                                    e.printStackTrace();
                                }
                            } catch (InvalidKeyException e) {
                                e.printStackTrace();
                            }

                        } catch (NoSuchPaddingException e) {
                            e.printStackTrace();
                        }

                    } catch (InvalidKeySpecException e) {
                        e.printStackTrace();
                    }


                } catch (NoSuchAlgorithmException e) {
                    e.printStackTrace();
                }
                break;
            case R.id.decode:





                break;
        }
    }
}
