package com.taiping.rxlife;

import rx.Observable;


public interface FragmentLifeOperator {
    <T> Observable.Transformer<T, T> bindUntilEvent(final FragmentEvent bindEvent);
}
