package com.qzsang.baselibrary.base;


import android.databinding.ViewDataBinding;
import android.net.http.SslError;
import android.text.TextUtils;
import android.webkit.SslErrorHandler;
import android.webkit.WebChromeClient;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;



/**
 * Created by qzsang on 2017/4/28 0028.
 * 参考 ：http://blog.csdn.net/carson_ho/article/details/52693322
 */

public abstract class BBaseWebAcivity<T extends ViewDataBinding> extends BBaseActivity<T> {
    protected WebView webView;
    protected WebViewClient webViewClient;
    protected WebChromeClient webChromeClient;


    @Override
    protected void init() {
        super.init();
        init(getWebView());

    }

    public abstract WebView getWebView ();


    protected void init (WebView webView) {
        this.webView = webView;
        //声明WebSettings子类
        WebSettings webSettings = webView.getSettings();
        //如果访问的页面中要与Javascript交互，则webview必须设置支持Javascript
        webSettings.setJavaScriptEnabled(true);

        //设置自适应屏幕，两者合用
        webSettings.setUseWideViewPort(true); //将图片调整到适合webview的大小
        webSettings.setLoadWithOverviewMode(true); // 缩放至屏幕的大小

        //缩放操作
        webSettings.setSupportZoom(true); //支持缩放，默认为true。是下面那个的前提。
        webSettings.setBuiltInZoomControls(true); //设置内置的缩放控件。若为false，则该WebView不可缩放
        webSettings.setDisplayZoomControls(false); //隐藏原生的缩放控件

        //其他细节操作
        webSettings.setCacheMode(WebSettings.LOAD_NO_CACHE); //关闭webview中缓存
        webSettings.setAllowFileAccess(true); //设置可以访问文件
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true); //支持通过JS打开新窗口
        webSettings.setLoadsImagesAutomatically(true); //支持自动加载图片
        webSettings.setDefaultTextEncodingName("utf-8");//设置编码格式

        //支持百度地图
        webSettings.setDatabaseEnabled(true);
        webSettings.setGeolocationEnabled(true);
        String dir = this.getCacheDir()+"/webdata";
        webSettings.setGeolocationDatabasePath(dir);
        webSettings.setDomStorageEnabled(true);
       // webSettings.setJavaScriptEnabled(true);// 允许请求JS
        //webSettings.setBuiltInZoomControls(true);

        webViewClient = new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                return super.shouldOverrideUrlLoading(view,url);
            }

            @Override
            public void onReceivedSslError(WebView view, SslErrorHandler handler, SslError error) {
                handler.proceed();
            }

        };

        webView.setWebViewClient(webViewClient);
        webChromeClient = new WebChromeClient();
        webView.setWebChromeClient(webChromeClient);

    }


    protected void loadUrl (String url) {
        if (TextUtils.isEmpty(url) || webView == null)
            return;

        webView.loadUrl(url);
    }
}
