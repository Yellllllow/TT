package com.taiping.app.model.response;


public interface BaseResult<T> {
    boolean isOk();

    T getData();
}
