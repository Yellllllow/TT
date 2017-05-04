package com.taiping.app.ui.feedback;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RelativeLayout;
import android.widget.TextView;
import com.taiping.app.R;
import com.taiping.app.model.FeedbackDetail;
import java.text.SimpleDateFormat;
import java.util.List;

public class FeedbackAdapter extends BaseAdapter {

    private Context mContext;

    private List<FeedbackDetail> mInfos;

    private ViewHolder mHolder;

    public FeedbackAdapter(Context mContext, List<FeedbackDetail> infos) {
        this.mContext = mContext;
        this.mInfos = infos;
    }

    @Override
    public int getCount() {
        return mInfos == null ? 0 : mInfos.size();
    }

    @Override
    public FeedbackDetail getItem(int position) {
        return mInfos.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            mHolder = new ViewHolder();
            convertView = View.inflate(mContext, R.layout.feedback_list_item, null);
            mHolder.tvUserFeedbackTime = (TextView) convertView.findViewById(R.id.tv_user_feedback_time);
            mHolder.tvUserName = (TextView) convertView.findViewById(R.id.tv_user_name);
            mHolder.tvUserFeedbackContent = (TextView) convertView.findViewById(R.id.tv_user_feedback_content);
            mHolder.rlReply = (RelativeLayout) convertView.findViewById(R.id.rl_reply);
            mHolder.tvReplyTime = (TextView) convertView.findViewById(R.id.tv_reply_time);
            mHolder.tvReplyContent = (TextView) convertView.findViewById(R.id.tv_reply_content);
            convertView.setTag(mHolder);
        } else {
            mHolder = (ViewHolder) convertView.getTag();
        }

        FeedbackDetail info = mInfos.get(position);

        mHolder.tvUserFeedbackTime.setText(stampToDate(Long.valueOf(info.getComment_time())));
        mHolder.tvUserName.setText(info.getUser());
        mHolder.tvUserFeedbackContent.setText(info.getComment());

        mHolder.rlReply.setVisibility(View.GONE);
        if (info.getReply() != null) {
            mHolder.rlReply.setVisibility(View.VISIBLE);
            mHolder.tvReplyTime.setText(stampToDate(Long.valueOf(info.getReply_time())));
            mHolder.tvReplyContent.setText(info.getReply());
        }

        return convertView;
    }

    public static String stampToDate(long timeStamp) {

        long curTime = System.currentTimeMillis();
        long time = curTime - timeStamp;
        String format = null;

        if (time >= 0 && time < 3600 * 24) {
            format = "HH:mm";
        } else {
            format = "yyyy-MM-dd";
        }
        String res;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(format);
        System.out.println(format+"DDDDD"+time+"DDD"+timeStamp);
        res = simpleDateFormat.format(timeStamp);
        return res;
    }

    private class ViewHolder {
        TextView tvUserFeedbackTime;

        TextView tvUserName;

        TextView tvUserFeedbackContent;

        RelativeLayout rlReply;

        TextView tvReplyTime;

        TextView tvReplyContent;
    }

}
