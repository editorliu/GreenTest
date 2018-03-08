package com.er.greentest;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.KeyEvent;
import android.view.View;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import static com.er.greentest.R.id.wv;

public class AndroidActivity extends AppCompatActivity {

    private WebView webView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_android);
        webView = ((WebView) findViewById(wv));
        WebSettings settings = webView.getSettings();

        webView.loadUrl("http://www.baidu.com");
        webView.setWebChromeClient(new WebChromeClient());
        webView.setWebViewClient(new WebViewClient());

    }
    public void onWVClick(View view){
        switch (view.getId()){
            case R.id.wv:
                webView.reload();
                break;
            case R.id.clearHistory:
                webView.clearHistory();
//                webView.loadUrl("https://www.csdn.net/");
//                webView.clearHistory();
                break;
        }

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if (keyCode == KeyEvent.KEYCODE_BACK && webView.canGoBack()) {
            webView.goBack();
            return true;
        }
        return super.onKeyDown(keyCode, event);
    }



    
}
