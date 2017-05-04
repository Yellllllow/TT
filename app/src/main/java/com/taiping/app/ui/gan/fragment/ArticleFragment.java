package com.taiping.app.ui.gan.fragment;

import android.support.annotation.NonNull;
import android.view.View;
import android.widget.AdapterView;

import com.taiping.app.api.remote.ApiFactory;
import com.taiping.app.base.adapter.ListBaseAdapter;
import com.taiping.app.base.ui.BaseListFragment;
import com.taiping.app.model.response.gan.GanItem;
import com.taiping.app.router.Router;
import com.taiping.app.ui.gan.adapter.GanAdapter;
import com.taiping.rxlife.FragmentEvent;

import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

public class ArticleFragment extends BaseListFragment<GanItem> {

    @Override
    protected void sendRequest() {
        String keyword = getSearchKeyword();
        ApiFactory.getGanApi()
                .getArticles(keyword, getPageSize(), mCurrentPage)
                .compose(this.bindUntilEvent(FragmentEvent.STOP))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(mSubscriber);

    }

    @NonNull
    private String getSearchKeyword() {
        String keyword = "";
        switch (mCatalog) {
            case 0:
                keyword = "Android";
                break;
            case 1:
                keyword = "iOS";
                break;
            case 2:
                keyword = "前端";
                break;
        }
        return keyword;
    }

    @Override
    protected ListBaseAdapter getListAdapter() {
        GanAdapter adapter = new GanAdapter();
        return adapter;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        GanItem item = mAdapter.getItem(position);
        if (item != null)
            Router.showDetail(getActivity(), item.getDesc(), item.getUrl(), "", "", "");
    }
}
