package com.taiping.rxlife;

import rx.Observable;


public interface ActivityLifeOperator {
    <T> Observable.Transformer<T, T> bindUntilEvent(final ActivityEvent bindEvent);
}
