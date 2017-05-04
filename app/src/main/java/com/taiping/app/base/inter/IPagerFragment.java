package com.taiping.app.base.inter;

import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;

import com.taiping.app.base.adapter.BaseViewPagerAdapter;

/**
 * Created by God
 * on 2016/10/12.
 */

public interface IPagerFragment {
    BaseViewPagerAdapter getPagerAdapter();

    ViewPager getViewPager();

    Fragment getCurrent();
}
