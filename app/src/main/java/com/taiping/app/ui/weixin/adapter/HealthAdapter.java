package com.taiping.app.ui.weixin.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taiping.app.R;
import com.taiping.app.base.adapter.ListBaseAdapter;
import com.taiping.app.model.HealthInfo;
import com.taiping.app.model.response.wx.WXItem;
import com.taiping.app.util.ImageLoaderUtil;
import com.taiping.app.view.SquareImageView;

import java.text.SimpleDateFormat;
import java.util.Date;

import butterknife.Bind;
import butterknife.ButterKnife;


public class HealthAdapter extends ListBaseAdapter<HealthInfo> {

    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh;
        if (convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(
                    R.layout.item_wechat, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        final HealthInfo item = mDatas.get(position);
        ImageLoaderUtil.load(parent.getContext(), "http://tnfs.tngou.net/image"+item.getImg(), vh.image);
        vh.title.setText(item.getTitle());
        vh.from.setText(item.getKeywords());
        vh.time.setText(stampToDate(item.getTime()));
        return convertView;
    }


    /*
    * 将时间戳转换为时间
    */
    public static String stampToDate(long s){
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(s);
        res = simpleDateFormat.format(date);
        return res;
    }

    static class ViewHolder {
        @Bind(R.id.iv_wechat_item_image)
        SquareImageView image;
        @Bind(R.id.tv_wechat_item_title)
        TextView title;
        @Bind(R.id.tv_wechat_item_from)
        TextView from;
        @Bind(R.id.tv_wechat_item_time)
        TextView time;

        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
