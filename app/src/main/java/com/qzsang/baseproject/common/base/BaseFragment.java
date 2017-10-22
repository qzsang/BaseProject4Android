package com.qzsang.baseproject.common.base;

import android.databinding.ViewDataBinding;
import android.os.Bundle;
import android.support.annotation.Nullable;

import com.qzsang.baselibrary.base.BPBaseFragment;
import com.qzsang.baseproject.common.rx.SubscribeTransformer;

/**
 * Created by quezhongsang on 2017/10/18.
 */

public abstract class BaseFragment<E extends ViewDataBinding> extends BPBaseFragment<E>
        implements SubscribeTransformer.SubscriptionManager {
    protected BaseFragment mContext;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mContext = this;
    }
}
