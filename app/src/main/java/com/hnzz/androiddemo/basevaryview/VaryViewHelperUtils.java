package com.hnzz.androiddemo.basevaryview;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hnzz.androiddemo.R;


/**
 * Created by leo on 2017/4/21.
 */

public class VaryViewHelperUtils {


    public static VaryViewHelper getViewHelper(Context context, View contentView, View.OnClickListener onClickListener) {
        return new VaryViewHelper.Builder()
                .setDataView(contentView)//放数据的父布局，逻辑处理在该Activity中处理
                .setLoadingView(LayoutInflater.from(context).inflate(R.layout.base_layout_loading_view, new LinearLayout(context), false))//加载页，无实际逻辑处理
                .setEmptyView(LayoutInflater.from(context).inflate(R.layout.base_layout_empty_view, new LinearLayout(context), false))//空页面，无实际逻辑处理
                .setErrorView(LayoutInflater.from(context).inflate(R.layout.base_layout_error_view, new LinearLayout(context), false))//错误页面
                .setRefreshListener(onClickListener)//错误页点击刷新实现
                .build();
    }


    public static VaryViewHelper getViewHelper(Context context, View contentView, int empty_view_layout, View.OnClickListener onClickListener) {
        return new VaryViewHelper.Builder()
                .setDataView(contentView)//放数据的父布局，逻辑处理在该Activity中处理
                .setLoadingView(LayoutInflater.from(context).inflate(R.layout.base_layout_loading_view, new LinearLayout(context), false))//加载页，无实际逻辑处理
                .setEmptyView(LayoutInflater.from(context).inflate(empty_view_layout, new LinearLayout(context), false))//空页面，无实际逻辑处理
                .setErrorView(LayoutInflater.from(context).inflate(R.layout.base_layout_error_view, new LinearLayout(context), false))//错误页面
                .setRefreshListener(onClickListener)//错误页点击刷新实现
                .build();
    }

    /**
     * @param context         上下文
     * @param contentView     内容view
     * @param imageRes        图片源
     * @param emptyDes        空白内容描述
     * @param onClickListener 错误点击事件触发
     * @return 当前的ViewHelper
     */
    public static VaryViewHelper getViewHelper(Context context, View contentView, int imageRes, String emptyDes, View.OnClickListener onClickListener) {

        View emptyView = LayoutInflater.from(context).inflate(R.layout.base_layout_empty_view_inflate, new LinearLayout(context), false);
        ImageView iv_empty = emptyView.findViewById(R.id.iv_empty);
        TextView tv_empty_des = emptyView.findViewById(R.id.tv_empty_des);
        iv_empty.setImageResource(imageRes);
        tv_empty_des.setText(emptyDes);
        return new VaryViewHelper.Builder()
                .setDataView(contentView)//放数据的父布局，逻辑处理在该Activity中处理
                .setLoadingView(LayoutInflater.from(context).inflate(R.layout.base_layout_loading_view, new LinearLayout(context), false))//加载页，无实际逻辑处理
                .setEmptyView(emptyView)//空页面，无实际逻辑处理
                .setErrorView(LayoutInflater.from(context).inflate(R.layout.base_layout_error_view, new LinearLayout(context), false))//错误页面
                .setRefreshListener(onClickListener)//错误页点击刷新实现
                .build();
    }

}
