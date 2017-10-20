package com.qzsang.baseproject.face.simple;

import android.os.Bundle;
import android.webkit.WebView;

import com.qzsang.baseproject.R;
import com.qzsang.baseproject.common.base.BaseWebActivity;
import com.qzsang.baseproject.databinding.ActivitySimpleWebBinding;

public class SimpleWebActivity extends BaseWebActivity<ActivitySimpleWebBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_web);
    }

    @Override
    public WebView getWebView() {
        return binding.webView;
    }

    @Override
    protected void init() {
        super.init();
        loadUrl("http://map.baidu.com");
    }
}
