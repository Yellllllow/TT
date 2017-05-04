package com.taiping.app.ui.feedback;

import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.taiping.app.R;
import com.taiping.app.api.remote.ApiFactory;
import com.taiping.app.base.ui.BaseActivity;
import com.taiping.app.model.FeedbackDetail;
import com.taiping.app.model.FeedbackInfo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class FeedBackActivity extends BaseActivity implements OnClickListener {

    private EditText etFeedbackContent;

    private TextView tvContentSize;

    private ListView lvFeedbacks;

    private Handler mHandler;

    private FeedbackInfo mFeedbackInfo;

    private ArrayList<FeedbackDetail> mInfos;

    private FeedbackAdapter adapter;



    private int totalPage = 0;

    private int currentPage = 1;

    private FeedbackDetail currentFeedbackDetail;


    @Override
    protected void init(Bundle savedInstanceState) {
        super.init(savedInstanceState);
        initViews();
        initListener();
        loadFeedbacks();
    }

    @Override
    protected int getLayoutId() {
        return R.layout.activity_feedback;
    }

    @Override
    protected boolean hasActionBar() {
        return super.hasActionBar();
    }

    @Override
    public void initView() {

    }

    @Override
    public void initData() {
        setActionBarTitle("意见反馈");

    }


    /**
     * 初始化控件
     */
    public void initViews() {
        LinearLayout header = (LinearLayout) View.inflate(this, R.layout.feedback_list_header, null);

        etFeedbackContent = (EditText) header.findViewById(R.id.et_feedback_content);
        tvContentSize = (TextView) header.findViewById(R.id.tv_content_size);

        mInfos = new ArrayList<FeedbackDetail>();
        lvFeedbacks = (ListView) findViewById(R.id.lv_feedback);
        lvFeedbacks.addHeaderView(header);

        adapter = new FeedbackAdapter(this, mInfos);
        lvFeedbacks.setAdapter(adapter);


    }


    /**
     * 初始化监听
     */
    private void initListener() {

        findViewById(R.id.tv_commit_suggestion).setOnClickListener(this);

//
//        lvFeedbacks.setOnClickListener(new OnClickListener() {
//
//            @Override
//            public void onClick(View v) {
//
//              //  MarketAPI.getFeedbacks(FeedBackActivity.this, FeedBackActivity.this, currentPage, mHandler);
//            }
//        });

        etFeedbackContent.addTextChangedListener(new TextWatcher() {

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                int size = 200;
                if (!TextUtils.isEmpty(s)) {
                    size -= s.length();
                }
                tvContentSize.setText(String.valueOf(size));
            }
        });
    }

    /**
     * 从服务端获取用户反馈
     */
    private void loadFeedbacks() {
        ApiFactory.getFirApi()
                .getFeedback("http://10.100.19.80:8080/feedbacks/",1,20)
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
                        if(data!=null){
                            FeedbackInfo feedbackInfo=new Gson().fromJson(data,FeedbackInfo.class);
                            if(feedbackInfo.getResult()!=null&&feedbackInfo.getResult().getData()!=null){
                                if(feedbackInfo.getResult().getData().size()>0){
                                    mInfos.addAll(feedbackInfo.getResult().getData());
                                    adapter.notifyDataSetChanged();
                                }
                            }
                        }

                        System.out.println("---*******----"+data);

                    }
                });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {

            case R.id.tv_commit_suggestion:
                String content = etFeedbackContent.getText().toString().trim();
                if (TextUtils.isEmpty(content)) {
                    Toast.makeText(this, "请输入您要留言的内容！", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (content.length() < 3) {
                    Toast.makeText(this, "请输入最少3个字的留言内容！", Toast.LENGTH_SHORT).show();
                    return;
                }

                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm.isActive()) {
                    imm.hideSoftInputFromWindow(etFeedbackContent.getWindowToken(), 0);
                }



                currentFeedbackDetail = new FeedbackDetail();
                currentFeedbackDetail.user = "我";
                currentFeedbackDetail.comment = content;
                currentFeedbackDetail.comment_time = System.currentTimeMillis()+"";

                etFeedbackContent.setText("");

                //MarketAPI.commitFeedback(this, this, content, mHandler);

                Map<String ,String > parmars=new HashMap<String,String>();
                parmars.put("app_id","om.taiping.lxjk");
                parmars.put("app_platform","android");
                parmars.put("app_version","1.0");
                parmars.put("device_id",  Build.DEVICE);
                parmars.put("phone_no","18217090238");
                parmars.put("email","zjywangyi@163.com");
                parmars.put("comment",content);

                ApiFactory.getFirApi()
                        .commitFeedback("http://10.100.19.80:8080/feedback/addFeedback", parmars)
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

                                mInfos.add(0,currentFeedbackDetail);
                                adapter.notifyDataSetChanged();
                                System.out.println("反馈数据"+response);

                            }
                        });

                break;
            default:
                break;
        }
    }




}
