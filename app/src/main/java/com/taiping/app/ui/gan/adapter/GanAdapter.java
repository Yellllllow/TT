package com.taiping.app.ui.gan.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.taiping.app.R;
import com.taiping.app.base.adapter.ListBaseAdapter;
import com.taiping.app.model.response.gan.GanItem;
import com.taiping.app.util.ImageLoaderUtil;

import java.util.Random;

import butterknife.Bind;
import butterknife.ButterKnife;


public class GanAdapter extends ListBaseAdapter<GanItem> {


    String[] arr = {"http://baoxian.cntaiping.com/opencms/export/sites/baoxian/images/activity_banner/2017/1/activity_1.jpg",
            "http://baoxian.cntaiping.com/opencms/export/sites/baoxian/images/activity_banner/2016/7/activity_1.jpg",
            "http://baoxian.cntaiping.com/opencms/export/sites/baoxian/images/activity_banner/2017/1/activity_2.jpg",
            "http://baoxian.cntaiping.com/opencms/export/sites/baoxian/images/activity_banner/2016/1/activity_1.jpg",
            "http://baoxian.cntaiping.com/opencms/export/sites/baoxian/images/activity_banner/5/gaes.jpg",
            "http://baoxian.cntaiping.com/opencms/export/sites/baoxian/images/activity_banner/5/ebb11.jpg"};

    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(
                    R.layout.item_gan, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        GanItem item = mDatas.get(position);
        vh.title.setText(item.getDesc());
        vh.author.setText(item.getWho());



        int i = position%6;
        String url = arr[i];
        ImageLoaderUtil.load(parent.getContext(),  url, vh.head);

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.tv_tech_title)
        TextView title;
        @Bind(R.id.tv_tech_head)
        ImageView head;
        @Bind(R.id.tv_tech_author)
        TextView author;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
