package com.taiping.app.base.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.taiping.app.R;
import com.taiping.app.model.ProductInfo;

import java.util.List;

import butterknife.Bind;
import butterknife.ButterKnife;

/**
 * Created by zhoujy on 2017/1/18.
 */

public class productListAdapter extends RecyclerView.Adapter<productListAdapter.MyViewHolder> {

    private List<ProductInfo> mDatas;
    private Context mContext;
    private LayoutInflater inflater;

    public productListAdapter(List<ProductInfo> mDatas, Context mContext) {
        this.mDatas = mDatas;
        this.mContext = mContext;
        inflater = LayoutInflater.from(mContext);
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.product_item_insurance, parent, false);
        MyViewHolder holder = new MyViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(MyViewHolder vh, final int position) {

        ProductInfo item = mDatas.get(position);
      //  ImageLoaderUtil.load(mContext, "http://image.tk.cn/uploadfiles/images/productPic/417/pf835b351-598_300_300.png", vh.image);
        vh.image.setImageResource(item.getPicId());
        vh.name.setText(item.getName());
        vh.age.setText(item.getAge());
        vh.lable.setText(item.getLable());
        vh.price.setText(item.getPrice());
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

        @Bind(R.id.produce_image)
        ImageView image;
        @Bind(R.id.product_name)
        TextView name;
        @Bind(R.id.product_age)
        TextView age;
        @Bind(R.id.product_lable)
        TextView lable;
        @Bind(R.id.product_price)
        TextView price;

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
