package com.taiping.app.ui;


import com.taiping.app.R;
import com.taiping.app.ui.gan.fragment.GanPagerFragment;
import com.taiping.app.ui.setting.fragment.SettingFragment;
import com.taiping.app.ui.weixin.fragment.HealthFragment;
import com.taiping.app.ui.weixin.fragment.WXFragment;


public enum MainTabs {
    TAB_GAN(0, R.string.main_tab_meizhi1, R.drawable.btn_tab_insurance, GanPagerFragment.class),
    TAB_WX(1, R.string.main_tab_meizhi2, R.drawable.btn_tab_health, HealthFragment.class),
    TAB_SETTING(2, R.string.main_tab_meizhi3, R.drawable.btn_tab_mine, SettingFragment.class);

    private int index;
    private int nameRes;
    private int iconRes;
    private Class<?> clazz;

    MainTabs(int index, int nameRes, int iconRes, Class<?> clazz) {
        this.index = index;
        this.nameRes = nameRes;
        this.iconRes = iconRes;
        this.clazz = clazz;
    }


    public int getIndex() {
        return index;
    }

    public int getNameRes() {
        return nameRes;
    }

    public int getIconRes() {
        return iconRes;
    }

    public Class<?> getClazz() {
        return clazz;
    }
}
