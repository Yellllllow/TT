package com.taiping.app.ui;

import android.annotation.TargetApi;
import android.app.Activity;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import com.taiping.app.AppManager;
import com.taiping.app.R;
import com.taiping.app.router.Router;
import com.taiping.app.util.SystemBarTintManager;

import java.util.Random;

public class AppStart extends Activity {

    private static final long TIME_DELAY = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Activity activity = AppManager.getActivity(MainActivity.class);
        if (activity != null && !activity.isFinishing()) {
            finish();
        }
        super.onCreate(savedInstanceState);

        //设置无标题
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        //设置全屏
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_start);

        View root = findViewById(R.id.activity_app_start);
        if (root != null && Build.VERSION.SDK_INT < Build.VERSION_CODES.LOLLIPOP
                && Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            setTranslucentStatus(true);
            SystemBarTintManager tintManager = new SystemBarTintManager(this);
            tintManager.setStatusBarTintEnabled(true);
            tintManager.setStatusBarTintResource(R.color.transparent);
        }

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Router.showMain(AppStart.this);
            }
        }, TIME_DELAY);



//
//        TypeTextView ttvSay = (TypeTextView) findViewById(R.id.ttv_say);
//        ttvSay.start(getRandomSentence());
//        ttvSay.setOnTypeViewListener(new TypeTextView.OnTypeViewListener() {
//            @Override
//            public void onTypeStart() {
//
//            }
//
//            @Override
//            public void onTypeOver() {
//                new Handler().postDelayed(new Runnable() {
//                    @Override
//                    public void run() {
//                        Router.showMain(AppStart.this);
//                    }
//                }, TIME_DELAY);
//
//            }
//        });


    }


    @TargetApi(19)
    protected void setTranslucentStatus(boolean on) {
        Window win = getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        final int bits = WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS;
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }


    private String getRandomSentence() {
        String[] array = getResources().getStringArray(R.array.sentence);
        int index = new Random().nextInt(array.length);
        //return array[index];
        return "欢迎你";
    }


}
