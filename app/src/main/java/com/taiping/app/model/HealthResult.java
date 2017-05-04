package com.taiping.app.model;

import com.taiping.app.model.response.BaseResult;

import java.util.ArrayList;

/**
 * Created by zhoujy on 2017/1/21.
 */

public class HealthResult  implements BaseResult<ArrayList<HealthInfo>> {
    boolean status;
    int total;
    ArrayList<HealthInfo> tngou;
    private int code;

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    public int getTotal() {
        return total;
    }

    public void setTotal(int total) {
        this.total = total;
    }

    public ArrayList<HealthInfo> getTngou() {
        return tngou;
    }

    public void setTngou(ArrayList<HealthInfo> tngou) {
        this.tngou = tngou;
    }


    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    @Override
    public boolean isOk() {
        return code == 200;
    }

    @Override
    public ArrayList<HealthInfo> getData() {
        return tngou;
    }
}
