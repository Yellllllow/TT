package com.taiping.app.ui.gan.adapter;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.text.TextUtils;

import com.taiping.app.base.adapter.BaseViewPagerAdapter;
import com.taiping.app.ui.gan.GanMainTab;
import com.taiping.app.ui.gan.fragment.ArticleFragment;

public class MainTabPagerAdapter extends BaseViewPagerAdapter {


    public MainTabPagerAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public CharSequence getPageTitle(int position) {
        GanMainTab tab = GanMainTab.getTab(position);
        return !TextUtils.isEmpty(tab.getTitle()) ? tab.getTitle() : "";
    }

    @Override
    protected Fragment createItem(int position) {
        final int pat = position % getCount();
        GanMainTab[] values = GanMainTab.values();
        Fragment fragment = null;
        try {
            fragment = (Fragment) values[pat].getClazz().newInstance();
            Bundle args = new Bundle();
            args.putInt(ArticleFragment.BUNDLE_KEY_CATALOG, values[pat].getCatalog());
            fragment.setArguments(args);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return fragment;
    }

    @Override
    public int getCount() {
        return GanMainTab.values().length;
    }

}
