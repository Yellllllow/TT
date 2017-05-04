package com.taiping.app.model;

import com.taiping.app.model.base.BaseResponseInfo;
import com.taiping.app.model.base.PageInfo;


public class FeedbackInfo extends BaseResponseInfo {

    PageInfo<FeedbackDetail> result;

    public PageInfo<FeedbackDetail> getResult() {
        return result;
    }

    public void setResult(PageInfo<FeedbackDetail> result) {
        this.result = result;
    }
}
