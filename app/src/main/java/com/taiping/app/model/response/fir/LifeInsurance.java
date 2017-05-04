package com.taiping.app.model.response.fir;

import java.io.Serializable;

/**
 * Created by zhoujy on 2017/1/17.
 */

public class LifeInsurance implements Serializable {
    private String productName;
    private String policyCode;
    private String validateDate;
    private String holderName;
    private String insuredName;
    private String beneName;
    private String amount;
    private String liabilityState;
    private String endDate;
    private String chargeYear;
    private String unit;
    private String periodPrem;
    private String sumPeriodPrem;
    private String payToDate;
    private String agentCode;

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getPolicyCode() {
        return policyCode;
    }

    public void setPolicyCode(String policyCode) {
        this.policyCode = policyCode;
    }

    public String getValidateDate() {
        return validateDate;
    }

    public void setValidateDate(String validateDate) {
        this.validateDate = validateDate;
    }

    public String getHolderName() {
        return holderName;
    }

    public void setHolderName(String holderName) {
        this.holderName = holderName;
    }

    public String getInsuredName() {
        return insuredName;
    }

    public void setInsuredName(String insuredName) {
        this.insuredName = insuredName;
    }

    public String getBeneName() {
        return beneName;
    }

    public void setBeneName(String beneName) {
        this.beneName = beneName;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public String getLiabilityState() {
        return liabilityState;
    }

    public void setLiabilityState(String liabilityState) {
        this.liabilityState = liabilityState;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getChargeYear() {
        return chargeYear;
    }

    public void setChargeYear(String chargeYear) {
        this.chargeYear = chargeYear;
    }

    public String getUnit() {
        return unit;
    }

    public void setUnit(String unit) {
        this.unit = unit;
    }

    public String getPeriodPrem() {
        return periodPrem;
    }

    public void setPeriodPrem(String periodPrem) {
        this.periodPrem = periodPrem;
    }

    public String getSumPeriodPrem() {
        return sumPeriodPrem;
    }

    public void setSumPeriodPrem(String sumPeriodPrem) {
        this.sumPeriodPrem = sumPeriodPrem;
    }

    public String getPayToDate() {
        return payToDate;
    }

    public void setPayToDate(String payToDate) {
        this.payToDate = payToDate;
    }

    public String getAgentCode() {
        return agentCode;
    }

    public void setAgentCode(String agentCode) {
        this.agentCode = agentCode;
    }
}
