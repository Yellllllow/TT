package com.taiping.app.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taiping.app.R;
import com.taiping.app.model.response.fir.CareInsurance;
import com.taiping.app.model.response.fir.LifeInsurance;
import com.taiping.app.util.ImageLoaderUtil;
import com.taiping.app.view.SquareImageView;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhoujy on 2017/1/18.
 */

public class MyCarRecyclerAdapter extends RecyclerView.Adapter<MyCarRecyclerAdapter.MyViewHolder> {


    private List<CareInsurance> mDatas;
    private Context mContext;
    private LayoutInflater inflater;


    public MyCarRecyclerAdapter(List<CareInsurance> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.item_insurance, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder vh, final int position) {

        CareInsurance
                item = mDatas.get(position);
        ImageLoaderUtil.load(mContext, "http://baoxian.cntaiping.com/opencms/export/system/modules/com.itaiping.baoxian/resources/common/images/products/1406110728639.jpg", vh.image);
        vh.title.setText(item.getRiskcname() + "\n" + item.getBrandname());
        vh.from.setText(item.getCarowner());
        vh.time.setText(item.getLicenseno());
        if (mOnItemClickLitener != null) {
            vh.item.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    mOnItemClickLitener.onItemClick(v, position);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder {

        @Bind(R.id.item_layout)
        LinearLayout item;

        @Bind(R.id.iv_wechat_item_image)
        SquareImageView image;
        @Bind(R.id.tv_wechat_item_title)
        TextView title;
        @Bind(R.id.tv_wechat_item_from)
        TextView from;
        @Bind(R.id.tv_wechat_item_time)
        TextView time;

        public MyViewHolder(View view) {
            super(view);
            ButterKnife.bind(this, view);
        }

    }


    public interface OnItemClickLitener {
        void onItemClick(View view, int position);

        void onItemLongClick(View view, int position);
    }

    private OnItemClickLitener mOnItemClickLitener;

    public void setOnItemClickLitener(OnItemClickLitener mOnItemClickLitener) {
        this.mOnItemClickLitener = mOnItemClickLitener;
    }

}
