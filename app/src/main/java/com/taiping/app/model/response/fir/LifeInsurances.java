package com.taiping.app.model.response.fir;

import java.util.ArrayList;

/**
 * Created by zhoujy on 2017/1/17.
 */

public class LifeInsurances {

    ArrayList<LifeInsurance> data;
    private String errorCode;
    private String errorInfo;

    public ArrayList<LifeInsurance> getData() {
        return data;
    }

    public void setData(ArrayList<LifeInsurance> data) {
        this.data = data;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorInfo() {
        return errorInfo;
    }

    public void setErrorInfo(String errorInfo) {
        this.errorInfo = errorInfo;
    }
}
