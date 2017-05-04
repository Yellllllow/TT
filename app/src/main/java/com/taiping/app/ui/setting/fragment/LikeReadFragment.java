package com.taiping.app.ui.setting.fragment;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;

import com.taiping.app.AppContext;
import com.taiping.app.R;
import com.taiping.app.base.adapter.ListBaseAdapter;
import com.taiping.app.base.ui.BaseListFragment;
import com.taiping.app.db.RealmHelper;
import com.taiping.app.model.LikeRecord;
import com.taiping.app.model.LikeRecordRealm;
import com.taiping.app.model.response.wx.WXResult;
import com.taiping.app.router.Router;
import com.taiping.app.ui.setting.adapter.LikeReadAdapter;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.functions.Func1;

/**
 * Created by God
 * on 2016/10/7.
 */

public class LikeReadFragment extends BaseListFragment<LikeRecord> {


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
    }

    @Override
    protected void sendRequest() {
        RealmHelper realmHelper = new RealmHelper(AppContext.context());


        Observable.just(realmHelper.findAllLikeRecord()).flatMap(new Func1<List<LikeRecordRealm>, Observable<WXResult<List<LikeRecord>>>>() {

            @Override
            public Observable<WXResult<List<LikeRecord>>> call(List<LikeRecordRealm> likeRecordRealms) {
                List<LikeRecord> records = new ArrayList<>();
                LikeRecord record = null;
                for (LikeRecordRealm recordRealm : likeRecordRealms) {
                    record = new LikeRecord();
                    record.setId(recordRealm.getId());
                    record.setImage(recordRealm.getImage());
                    record.setTime(recordRealm.getTime());
                    record.setTitle(recordRealm.getTitle());
                    record.setType(recordRealm.getType());
                    records.add(record);

                }

                final WXResult<List<LikeRecord>> result = new WXResult<>();
                result.setCode(200);
                result.setNewslist(records);
                return Observable.just(result);


            }
        })
                .subscribe(mSubscriber);

    }

    @Override
    protected ListBaseAdapter<LikeRecord> getListAdapter() {
        LikeReadAdapter adapter = new LikeReadAdapter();
        return adapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        LikeRecord item = mAdapter.getItem(position);
        if (item != null) {
            Router.showDetail(getActivity(), item.getTitle(), item.getId(), item.getImage(), "", "");
        }
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        getActivity().getMenuInflater().inflate(R.menu.recent_read, menu);
        menu.findItem(R.id.clear_recent_read);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        RealmHelper realmHelper = new RealmHelper(AppContext.context());
        realmHelper.removeAllLikeRecord();
        mAdapter.clear();
        refresh();
        return true;
    }
}
