package com.hnzz.androiddemo.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hnzz.androiddemo.R;
import com.hnzz.androiddemo.bean.QianbaoBean;

import java.util.List;

public class MyQianbaoRecordListRclAdapter extends RecyclerView.Adapter<MyQianbaoRecordListRclAdapter.ViewHolder> {

    private List<QianbaoBean.DataBean> mDatas;
    private Context mContext;

    public MyQianbaoRecordListRclAdapter(Context context, List<QianbaoBean.DataBean> datas) {
        mDatas = datas;
        mContext = context;

    }

    @NonNull
    @Override
    public MyQianbaoRecordListRclAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View itemView = LayoutInflater.from(mContext).inflate(R.layout.item_my_qianbao_list, parent, false);
        ViewHolder viewHolder = new ViewHolder(itemView);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyQianbaoRecordListRclAdapter.ViewHolder holder, int position) {

        holder.tv_money.setText(mDatas.get(position).getMoney());
        holder.tv_title.setText(mDatas.get(position).getDescription());
        holder.tv_time.setText(mDatas.get(position).getAdd_time());
    }

    @Override
    public int getItemCount() {
        return mDatas.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView tv_title, tv_time, tv_money;

        public ViewHolder(View itemView) {
            super(itemView);
            tv_title = itemView.findViewById(R.id.tv_title);
            tv_time = itemView.findViewById(R.id.tv_time);
            tv_money = itemView.findViewById(R.id.tv_money);
        }
    }
}
