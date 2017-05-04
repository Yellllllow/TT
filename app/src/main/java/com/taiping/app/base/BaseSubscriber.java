package com.taiping.app.base;

import android.content.Context;

import com.taiping.app.api.exception.ApiErrorCode;
import com.taiping.app.api.exception.ApiErrorHelper;
import com.taiping.app.api.exception.ApiException;
import com.taiping.app.model.ReadRecord;
import com.taiping.app.util.OSUtil;

import okhttp3.Call;
import retrofit2.Callback;
import retrofit2.Response;
import rx.Subscriber;
import rx.observers.Subscribers;
import rx.subjects.PublishSubject;
import rx.subjects.Subject;

public class BaseSubscriber<T> extends Subscriber<T> {
    private Context mContext;

    public BaseSubscriber() {
    }

    public BaseSubscriber(Context context) {
        mContext = context;
    }

    @Override
    public void onStart() {
        if (!OSUtil.hasInternet()) {
            this.onError(new ApiException(ApiErrorCode.ERROR_NO_INTERNET, "network interrupt"));
            return;
        }

    }


    @Override
    public void onCompleted() {

    }

    @Override
    public void onError(Throwable e) {
        ApiErrorHelper.handleCommonError(mContext, e);
    }

    @Override
    public void onNext(T t) {

    }
}
