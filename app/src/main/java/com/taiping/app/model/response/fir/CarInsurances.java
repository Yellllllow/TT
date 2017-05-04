package com.taiping.app.model.response.fir;

import java.util.ArrayList;

/**
 * Created by zhoujy on 2017/1/17.
 */

public class CarInsurances {

    ArrayList<CareInsurance> carPolicyList;
    private String  totalCount;
    private String currentPageIndex;


    public ArrayList<CareInsurance> getCarPolicyList() {
        return carPolicyList;
    }

    public void setCarPolicyList(ArrayList<CareInsurance> carPolicyList) {
        this.carPolicyList = carPolicyList;
    }

    public String getTotalCount() {
        return totalCount;
    }

    public void setTotalCount(String totalCount) {
        this.totalCount = totalCount;
    }

    public String getCurrentPageIndex() {
        return currentPageIndex;
    }

    public void setCurrentPageIndex(String currentPageIndex) {
        this.currentPageIndex = currentPageIndex;
    }
}
