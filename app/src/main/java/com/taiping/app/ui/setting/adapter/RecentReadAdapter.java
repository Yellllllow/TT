package com.taiping.app.ui.setting.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taiping.app.R;
import com.taiping.app.base.adapter.ListBaseAdapter;
import com.taiping.app.model.ReadRecord;
import com.taiping.app.util.ImageLoaderUtil;
import com.taiping.app.view.SquareImageView;

import butterknife.Bind;
import butterknife.ButterKnife;

public class RecentReadAdapter extends ListBaseAdapter<ReadRecord> {
    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(
                    R.layout.item_recent_article, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        ReadRecord record = mDatas.get(position);
        vh.title.setText(record.getTitle());
        ImageLoaderUtil.load(parent.getContext(), record.getImage(), vh.image);

        return convertView;
    }

    static class ViewHolder {
        @Bind(R.id.iv_article_image)
        SquareImageView image;
        @Bind(R.id.tv_article_title)
        TextView title;


        ViewHolder(View view) {
            ButterKnife.bind(this, view);
        }
    }
}
