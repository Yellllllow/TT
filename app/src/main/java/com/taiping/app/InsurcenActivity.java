package com.taiping.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ScrollView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.taiping.app.cryptoUtil.AesAndRsaMixtureCryptoUtil;
import com.taiping.app.api.remote.ApiFactory;
import com.taiping.app.base.adapter.MyRecyclerAdapter;
import com.taiping.app.base.ui.BaseActivity;
import com.taiping.app.model.response.fir.LifeInsurance;
import com.taiping.app.model.response.fir.LifeInsurances;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.Serializable;
import java.util.ArrayList;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhoujy on 2017/1/18.
 */

public class InsurcenActivity extends BaseActivity {



    private static String PUBLIC_KEY = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEApgmxgkwuBOEcZveYv0POVZoZJSWtNqNU" + "\r"+
            "MrJnvaMb7IzfcKL59rh2DkrHwwdWVNkeNC+zU1BoJsN87F0YCaVrpdhbJeZFT3loO3T2MIz3h0HU" + "\r"+
            "DjJxaAkqQNdVi+JfmRk8dztPhdTYEPxy3vzDm/AdAmkGuSeom3154mzlnxtN2vpCX34WhVSJQHv7" + "\r"+
            "KpMYhnKzoMbGS9zFTTvltcsvWqgKqVmmfkS4812EJueJh6Po51O/N00tlZTaXzyLCOPgLpN5ucjl" + "\r"+
            "bgHgnnz0H/NR8oU/gVMBHAZhFYeonQJs7TahgsMZfn9TbhvP4KjxDyB7CsTlH1f5BM2Nb0FDYO+p" + "\r"+
            "ifdqqwIDAQAB" + "\r";

    private static String PRIVATE_KEY = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQCmCbGCTC4E4Rxm95i/Q85Vmhkl" + "\r"+
            "Ja02o1Qysme9oxvsjN9wovn2uHYOSsfDB1ZU2R40L7NTUGgmw3zsXRgJpWul2Fsl5kVPeWg7dPYw" + "\r"+
            "jPeHQdQOMnFoCSpA11WL4l+ZGTx3O0+F1NgQ/HLe/MOb8B0CaQa5J6ibfXnibOWfG03a+kJffhaF" + "\r"+
            "VIlAe/sqkxiGcrOgxsZL3MVNO+W1yy9aqAqpWaZ+RLjzXYQm54mHo+jnU783TS2VlNpfPIsI4+Au" + "\r"+
            "k3m5yOVuAeCefPQf81HyhT+BUwEcBmEVh6idAmztNqGCwxl+f1NuG8/gqPEPIHsKxOUfV/kEzY1v" + "\r"+
            "QUNg76mJ92qrAgMBAAECggEAP6yl+28Vkt91kfNQC8GIJoHjNMC/LZ3zU0Hh0PL1aYEYy0xlCf3x" + "\r"+
            "oEeDVkLBE+bz01Wbss66Mmtzw/1rk39xyfcv9VfaqsDHfU28gB//aUqQdSVZImmpX5Z1AyE8Qi1Q" + "\r"+
            "Z/VB6PQYLGNz9hn/NhUeOudmIamY2pCDJpdWc+73q4sBkaOL2+W3E0cZT75vzoZQ90S7h6sfkX0G" + "\r"+
            "Erjw/9nUBJBy8+V1w7BSoK4+fcvrLqo8ffVo0PSYVNC57c/jd3+hogo7iKorvTsqwm4NMXTsto5x" + "\r"+
            "vXQxjvcfav0znZiN0Cc2wTMROkw47DBC1dZYbGuPO+w6Six5e6x0ZU/qWKDZQQKBgQDXiOsK/Szi" + "\r"+
            "WHfLvx2EDycqRlY1Kii3RTz3kcE5FI9HPa5isjNbUSG/F3wSNoVg/0AdzeWQbSixkZNau94Wur6i" + "\r"+
            "+97KLwD2qq8J6i/noZIv/P8OQvo/vwHYR/IFbyQf6e2CiK3FhhGyws151PQoqirsmyTlJJZAZWLf" + "\r"+
            "iZTZpe1/ywKBgQDFNddTXixeSBOz0Qjm49V1JhurlILa3JaKUol4iDbvYOh0t1CsjUfGjrkunDHX" + "\r"+
            "6P/T5ACOX+PmnzkjHW0ZSPAddx1A9Hy34o+MGON0GX8nj4p4YoalIW1k/jlGFcctkSoFVFg/RKop" + "\r"+
            "QQGz+XNde3Yr0UK7J0EXzlVRnwYmrbSkoQKBgErxrOjRR74cjOsntReqPTAR7P/nfOjdBmn/IHS8" + "\r"+
            "lWVsKSrgU8M43scXX2jl1FL57k1uvpgNnMzBlb9C++JjZM4/TiR3W5pplxuXdrjQEYjmK7nFyEZK" + "\r"+
            "IFYYDiudja4bJR7yb5nzGExUOCZYyd0p7mr/N0EGC8iweETKDhvv+jkpAoGAOR9iUoSZp2mLQ2+N" + "\r"+
            "+4sM3lT+eNGYoZp5hHFp3l7eQrI4Qu6CUKjPnITkwMp/aYHU8GQ/gP3nfnqqSzCP1F4bJv3EnHb0" + "\r"+
            "1TKrz7G52Hw2J5hdTIeFZrlq/XDh2BogymMc39RRh2n1O+PXgXEE6JQFY6XGhX1WTnX2oqDEYFm6" + "\r"+
            "VGECgYAcLBMZQyRUdKL9XYSdgFLm9YZOu/RGJYoXaHHZlacx5qXwcgz/hhxBnMy9UrMfGvdvx3Lj" + "\r"+
            "lJcAveFt+LgEMGsjU/LntN0QEM1cNrQVOl2MwtV2IeUqTLPJDwoFU1ZRPnzD4ehEidllyui6x2Ri" + "\r"+
            "hozmpCbTKFYi/pK6GNJLEOLpMw==" + "\r";


    EditText editText;
    Button button;
    RecyclerView recycler;
    MyRecyclerAdapter recycleAdapter;
    ArrayList<LifeInsurance> mDatas=new ArrayList<>();

    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);

        editText= (EditText) findViewById(R.id.ev);
        button= (Button) findViewById(R.id.bt);
        recycler= (RecyclerView) findViewById(R.id.recycleView);
        recycleAdapter= new MyRecyclerAdapter(mDatas,InsurcenActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recycler.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recycler.setAdapter( recycleAdapter);
        button.setOnClickListener(this);
        recycleAdapter.setOnItemClickLitener(new MyRecyclerAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                LifeInsurance lifeInsurance=mDatas.get(position);
                Intent mIntent=new Intent(InsurcenActivity.this,InsuranceDetailActivity.class);
                mIntent.putExtra("type",1);
                mIntent.putExtra("data", lifeInsurance);
                startActivity(mIntent);
                ApiFactory.getFirApi()
                        .getList("https://lifecloud.life.cntaiping.com/internet-insurance-platform/getProductList","10000")
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Subscriber<String>() {

                            @Override
                            public void onCompleted() {
                                hideWaitDialog();
                            }

                            @Override
                            public void onError(Throwable e) {
                                hideWaitDialog();

                            }

                            @Override
                            public void onNext(String response) {


                                System.out.println("---*******----"+response);

                                try {
                                    JSONObject jsonObject=new JSONObject(response);
                                    jsonObject.toString();
                                    System.out.println("---xxxxxxx----"+response);
                                } catch (JSONException e) {
                                    e.printStackTrace();
                                }

                            }
                        });









            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });

    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_insurance;
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    public void initView() {


    }


    @Override
    public void onClick(View v) {
        super.onClick(v);
        showWaitDialog("正在查询数据");
        String code=editText.getText().toString();
        ScrollView s;
        ApiFactory.getFirApi()
                .getLifeInsurance("http://wxtest.life.cntaiping.com/taipingwx/insurance-policy/life-insurance",code)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<String>() {

                    @Override
                    public void onCompleted() {
                        hideWaitDialog();
                    }

                    @Override
                    public void onError(Throwable e) {
                        hideWaitDialog();

                    }

                    @Override
                    public void onNext(String response) {

                        String data= response;
                        String decrypt=null;
                        try {
                            decrypt = AesAndRsaMixtureCryptoUtil.decrypt(data,PRIVATE_KEY);
                            LifeInsurances lifeInsurances=new Gson().fromJson(decrypt,LifeInsurances.class);
                            if(lifeInsurances!=null&&lifeInsurances.getErrorCode()==null) {
                                ArrayList<LifeInsurance> list = lifeInsurances.getData();
                                if (list!=null ){
                                    if(list.size()==0){
                                        Toast.makeText(InsurcenActivity.this,"没查询到保单",Toast.LENGTH_LONG).show();
                                    }else{
                                        mDatas.clear();
                                        mDatas.addAll(list);
                                        recycleAdapter.notifyDataSetChanged();
                                    }

                                }
                            }


                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                        System.out.println("---*******----"+decrypt);

                    }
                });

    }

    @Override
    public void initData() {
        setActionBarTitle("保单查询");
    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.recent_read, menu);
        menu.findItem(R.id.clear_recent_read);
        return super.onCreateOptionsMenu(menu);

    }



}
