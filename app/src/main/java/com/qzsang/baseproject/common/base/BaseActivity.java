package com.qzsang.baseproject.common.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.qzsang.baselibrary.base.BPBaseActivity;
import com.qzsang.baseproject.common.rx.SubscribeTransformer;

/**
 * Created by quezhongsang on 2017/10/18.
 */

public class BaseActivity<E extends ViewDataBinding> extends BPBaseActivity<E>
        implements SubscribeTransformer.SubscriptionManager {

    protected BaseActivity mContext;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }
}
