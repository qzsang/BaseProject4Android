package com.qzsang.baseproject.common.rx;

import com.qzsang.baselibrary.util.LogUtil;

import rx.Subscriber;

/**
 * Created by quezhongsang on 2017/10/19.
 */

public abstract class NetSubscriber<T> extends Subscriber<T> {


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        e.printStackTrace();
        LogUtil.e(NetSubscriber.class, e.getMessage() + ".");

    }

    @Override
    public void onNext(T response) {

    }
}
