package com.taiping.app;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.taiping.app.base.ui.BaseActivity;
import com.taiping.app.model.response.fir.CareInsurance;
import com.taiping.app.model.response.fir.LifeInsurance;

/**
 * Created by zhoujy on 2017/1/19.
 */

public class InsuranceDetailActivity extends BaseActivity {

    TextView mTextview;


    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        Intent mIntent = getIntent();
        mTextview = (TextView) findViewById(R.id.insurance_detail_tv);
        if (mIntent != null) {
            String text = null;
            int type = mIntent.getIntExtra("type", 0);

            if (type == 1) {
                LifeInsurance lifeInsurance = (LifeInsurance) mIntent.getSerializableExtra("data");
                if (lifeInsurance != null) {
                    text = "险种名称： " + lifeInsurance.getProductName() + "\n" +
                            "保单号： " + lifeInsurance.getPolicyCode() + "\n" +
                            "投保人： " + lifeInsurance.getHolderName() + "\n" +
                            "被保人： " + lifeInsurance.getInsuredName() + "\n" +
                            "受益人： " + lifeInsurance.getBeneName() + "\n" +
                            "基本保额：" + lifeInsurance.getAmount() + "\n" +
                            "缴费年限：" + lifeInsurance.getChargeYear() + "\n" +
                            "份数：" + lifeInsurance.getUnit() + "\n" +
                            "当前缴费：" + lifeInsurance.getPeriodPrem() + "\n" +
                            "累计缴费：" + lifeInsurance.getSumPeriodPrem() + "\n" +
                            "业务员工号：" + lifeInsurance.getAgentCode() + "\n";
                }

            } else {
                CareInsurance careInsurance = (CareInsurance) mIntent.getSerializableExtra("data");
                text = careInsurance.toString();
            }
            mTextview.setText(text);
        }

    }

    @Override
    protected boolean hasBackButton() {
        System.out.println("BBBBBBBBBBBBBB");
        return true;
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_insurance_detail;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {

        setActionBarTitle("保单详情");

    }
}
