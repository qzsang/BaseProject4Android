package com.qzsang.baseproject.face.simple;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.qzsang.baselibrary.util.LogUtil;
import com.qzsang.baseproject.R;
import com.qzsang.baseproject.common.base.BaseActivity;
import com.qzsang.baseproject.common.rx.BaseSubscriber;
import com.qzsang.baseproject.common.rx.SubscribeTransformer;
import com.qzsang.baseproject.databinding.ActivitySimpleRxjavaBinding;

import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class SimpleRxjavaActivity extends BaseActivity<ActivitySimpleRxjavaBinding> {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_simple_rxjava);
    }

    //可以参考 https://github.com/qzsang/RxjavaGuideDemo    但是  subscribe 部分 应该全部改成  下面这种
    public void onClick (View view) {
        //稍后 再进行二次封装  优化掉  .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread()) 这部分
        Observable.just("1","2")
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .compose(new SubscribeTransformer<String>(mContext, new BaseSubscriber<String>() {
                    @Override
                    public void onNext(String response) {
                        super.onNext(response);

                        LogUtil.e("rxjava", response + "");
                        toast(response );
                    }
                }));
    }
}
