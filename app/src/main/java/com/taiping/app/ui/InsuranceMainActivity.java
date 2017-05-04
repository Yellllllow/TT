package com.taiping.app.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.View;

import com.taiping.app.R;
import com.taiping.app.base.adapter.productListAdapter;
import com.taiping.app.base.ui.BaseActivity;
import com.taiping.app.model.ProductInfo;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;

/**
 * Created by zhoujy on 2017/1/18.
 */

public class InsuranceMainActivity extends BaseActivity {

    RecyclerView recycler;
    productListAdapter recycleAdapter;
    ArrayList<ProductInfo> mDatas = new ArrayList<>();


    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);

        recycler = (RecyclerView) findViewById(R.id.recycleView);
        recycleAdapter = new productListAdapter(mDatas, InsuranceMainActivity.this);
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        //设置布局管理器
        recycler.setLayoutManager(layoutManager);
        //设置为垂直布局，这也是默认的
        layoutManager.setOrientation(OrientationHelper.VERTICAL);
        //设置Adapter
        recycler.setAdapter(recycleAdapter);

        recycleAdapter.setOnItemClickLitener(new productListAdapter.OnItemClickLitener() {
            @Override
            public void onItemClick(View view, int position) {
                Intent mIntent = new Intent(InsuranceMainActivity.this, QQSpeakActivity.class);
                InsuranceMainActivity.this.startActivity(mIntent);
            }

            @Override
            public void onItemLongClick(View view, int position) {

            }
        });
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main_insurance;
    }

    @Override
    protected boolean hasBackButton() {
        return true;
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        setActionBarTitle(getResources().getString(R.string.app_name));
        createDate();
    }


    private String readTextFromSDcard(InputStream is) throws Exception {
        InputStreamReader reader = new InputStreamReader(is);
        BufferedReader bufferedReader = new BufferedReader(reader);
        StringBuffer buffer = new StringBuffer("");
        String str;
        while ((str = bufferedReader.readLine()) != null) {
            buffer.append(str);
            buffer.append("\n");
        }
        return buffer.toString();
    }

    private void createDate() {
        ProductInfo info1 = new ProductInfo();
        info1.setName("太平爱爸妈骨折综合意外伤害保险");
        info1.setAge("投保年龄：50-70岁");
        info1.setLable("少儿保险| 意外医疗| 住院津贴");
        info1.setPrice("35元起");
        info1.setPicId(R.drawable.product_one);

        ProductInfo info2 = new ProductInfo();
        info2.setName("太平爱宝贝综合意外伤害保险");
        info2.setAge("投保年龄：50-70岁");
        info2.setLable("老年意外| 意外医疗| 住院津贴");
        info2.setPrice("35元起");
        info2.setPicId(R.drawable.produce_two);
        mDatas.add(info1);
        mDatas.add(info2);
        recycleAdapter.notifyDataSetChanged();

        String  test=  "{\"products\":[{\"name\":\"太平爱宝贝综合意外伤害保险\",\"code\":\"3141\",\"nickname\":\"爱宝贝\",\"imgs\":[{\"small\":\"http://url\"},{\"big\":\"http://url\"}],\"title\":\"爱宝贝\",\"labels\":[{\"label\":\"少儿保险\"},{\"label\":\"意外险\"},{\"label\":\"医疗保障\"}],\"source\":\"太平寿险\",\"dealer\":\"\",\"price\":\"45元／100元\",\"saleQuantity\":\"0\"},{\"name\":\"太平爱爸妈骨折综合意外伤害保险\",\"code\":\"3142\",\"nickname\":\"爱爸妈\",\"imgs\":[{\"small\":\"http://url\"},{\"big\":\"http://url\"}],\"title\":\"爱爸妈\",\"labels\":[{\"label\":\"老人保险\"},{\"label\":\"意外险\"},{\"label\":\"医疗保障\"}],\"source\":\"太平寿险\",\"dealer\":\"\",\"price\":\"45元／100元\",\"saleQuantity\":\"0\"}]}";

        try {
            InputStream is = getAssets().open("config.json");
            String text = readTextFromSDcard(is);
            Log.e("------>>>",test);
            Log.e("------****",text);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.my_center, menu);
        menu.findItem(R.id.my_center);
        return super.onCreateOptionsMenu(menu);

    }


}
