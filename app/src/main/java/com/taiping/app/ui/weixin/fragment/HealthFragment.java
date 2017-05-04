package com.taiping.app.ui.weixin.fragment;

import android.view.View;
import android.widget.AdapterView;

import com.taiping.app.AppConstant;
import com.taiping.app.AppContext;
import com.taiping.app.api.remote.ApiFactory;
import com.taiping.app.base.adapter.ListBaseAdapter;
import com.taiping.app.base.ui.BaseListFragment;
import com.taiping.app.db.RealmHelper;
import com.taiping.app.model.HealthInfo;
import com.taiping.app.model.ReadRecordRealm;
import com.taiping.app.model.response.wx.WXItem;
import com.taiping.app.router.Router;
import com.taiping.app.ui.weixin.adapter.HealthAdapter;
import com.taiping.app.ui.weixin.adapter.WXAdapter;
import com.taiping.rxlife.FragmentEvent;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by zhoujy
 * on 2016/10/7.
 */

public class HealthFragment extends BaseListFragment<HealthInfo> {

    @Override
    protected void sendRequest() {

        ApiFactory.getsHealthApi().getHealthList(mCurrentPage + 1)
                .compose(this.bindUntilEvent(FragmentEvent.STOP))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);
    }

    @Override
    protected ListBaseAdapter<HealthInfo> getListAdapter() {
        HealthAdapter adapter = new HealthAdapter();
        return adapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        HealthInfo wxItem = mAdapter.getItem(position);
        if (wxItem != null) {
            Router.showDetail(getActivity(), wxItem.getTitle(), "http://www.tngou.net/info/show/"+wxItem.getId(), wxItem.getImg(), wxItem.getDescription(), wxItem.getTime()+"");
//            RealmHelper realmHelper = new RealmHelper(AppContext.context());
//            if (realmHelper.findReadRecord(wxItem.getTitle()) != null) {
//                return;
//            }
//            ReadRecordRealm recordRealm = new ReadRecordRealm();
//            recordRealm.setId(wxItem.getId());
//            recordRealm.setTitle(wxItem.getTitle());
//            recordRealm.setTime(System.currentTimeMillis());
//            recordRealm.setImage(wxItem.getPicUrl());
//            realmHelper.addReadRecord(recordRealm);


        }
    }
}
