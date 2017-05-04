package com.taiping.app.ui.setting.adapter;

import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.taiping.app.R;
import com.taiping.app.base.adapter.ListBaseAdapter;
import com.taiping.app.model.LikeRecord;
import com.taiping.app.util.ImageLoaderUtil;
import com.taiping.app.view.SquareImageView;

import butterknife.Bind;
import butterknife.ButterKnife;


public class LikeReadAdapter extends ListBaseAdapter<LikeRecord> {
    @Override
    protected View getRealView(int position, View convertView, ViewGroup parent) {

        ViewHolder vh;
        if (convertView == null || convertView.getTag() == null) {
            convertView = getLayoutInflater(parent.getContext()).inflate(
                    R.layout.item_like_article, null);
            vh = new ViewHolder(convertView);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder) convertView.getTag();
        }

        LikeRecord record = mDatas.get(position);
        vh.title.setText(record.getTitle());
        if (record.getImage() != null)
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
