package com.qzsang.baseproject.common.rx;


import rx.Observable;
import rx.Subscriber;
import rx.Subscription;

/**
 * Created by quezhongsang on 2017/10/22.
 */

public class SubscribeTransformer<T> implements Observable.Transformer<T,Void> {

    private Subscriber<T> subscribe;
    private SubscriptionManager subscriptionManager;

    public SubscribeTransformer(SubscriptionManager subscriptionManager, Subscriber<T> subscribe ) {
       this.subscribe = subscribe;
        this.subscriptionManager = subscriptionManager;
    }



    @Override
    public Observable<Void> call(Observable<T> tObservable) {
        Subscription subscription = tObservable.subscribe(subscribe);

        if (subscriptionManager != null) {
            subscriptionManager.addSubscription(subscription);
        }

        return null;
    }

    public interface SubscriptionManager {
        void addSubscription (Subscription subscription);
    }


}
