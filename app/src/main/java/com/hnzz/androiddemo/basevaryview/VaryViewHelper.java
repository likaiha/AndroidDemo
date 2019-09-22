package com.hnzz.androiddemo.basevaryview;

import android.graphics.drawable.AnimationDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.hnzz.androiddemo.MyApplication;
import com.hnzz.androiddemo.R;


/**
 * 作者： corer   时间： 2015/10/18.
 * 功能：帮助切换错误，数据为空，正在加载的页面
 * 修改：
 */
public class VaryViewHelper {
    /**
     * 切换不同视图的帮助类
     */
    private OverlapViewHelper mViewHelper;
    /**
     * 错误页面
     */
    private View mErrorView;
    /**
     * 正在加载页面
     */
    private View mLoadingView;
    /**
     * 数据为空的页面
     */
    private View mEmptyView;
    /**
     * 正在加载页面的进度环
     */
    private ProgressBar mLoadingProgress;
    private ImageView iv_loading_progress;
    private AnimationDrawable animDrawable;

    public VaryViewHelper(View view) {
        this(new OverlapViewHelper(view));
    }

    public VaryViewHelper(OverlapViewHelper helper) {
        this.mViewHelper = helper;
    }


    public void setUpEmptyView(View view) {
        mEmptyView = view;
        mEmptyView.setClickable(true);
    }

    public void setUpErrorView(View view, View.OnClickListener listener) {
        mErrorView = view;
        mErrorView.setClickable(true);

        View btn = view.findViewById(R.id.vv_error_refresh);
        if (btn != null) {
            btn.setOnClickListener(listener);
        }
    }

    public void setUpLoadingView(View view) {
        mLoadingView = view;
        mLoadingView.setClickable(true);
        mLoadingProgress = view.findViewById(R.id.vv_loading_progress);
        iv_loading_progress = view.findViewById(R.id.iv_loading_progress);
        animDrawable = (AnimationDrawable) MyApplication.getInstance().getResources().getDrawable(R.drawable.progressbar);
        if (iv_loading_progress != null){

            iv_loading_progress.setImageDrawable(animDrawable);
        }
    }

    public void showEmptyView() {
        mViewHelper.showCaseLayout(mEmptyView);
        //stopProgressLoading();
        animDrawable.stop();
    }

    public void showErrorView() {
        mViewHelper.showCaseLayout(mErrorView);
        //stopProgressLoading();
        animDrawable.stop();
    }

    public void showLoadingView() {
        mViewHelper.showCaseLayout(mLoadingView);
        // startProgressLoading();
        animDrawable.start();
    }

    public void showDataView() {
        mViewHelper.restoreLayout();
    }


    public void releaseVaryView() {
        mErrorView = null;
        mLoadingView = null;
        mEmptyView = null;
    }

    public static class Builder {
        private View mErrorView;
        private View mLoadingView;
        private View mEmptyView;
        private View mDataView;
        private View.OnClickListener mRefreshListener;

        public Builder setErrorView(View errorView) {
            mErrorView = errorView;
            return this;
        }

        public Builder setLoadingView(View loadingView) {
            mLoadingView = loadingView;
            return this;
        }

        public Builder setEmptyView(View emptyView) {
            mEmptyView = emptyView;
            return this;
        }

        public Builder setDataView(View dataView) {
            mDataView = dataView;
            return this;
        }

        public Builder setRefreshListener(View.OnClickListener refreshListener) {
            mRefreshListener = refreshListener;
            return this;
        }

        public VaryViewHelper build() {

            if (mDataView == null) {
                throw new NullPointerException("dataView 不可为空");
            }
            VaryViewHelper helper = new VaryViewHelper(mDataView);
            if (mEmptyView != null) {
                helper.setUpEmptyView(mEmptyView);
            } else {
                helper.setUpEmptyView(LayoutInflater.from(mDataView.getContext()).inflate(R.layout.layout_emptyview, new LinearLayout(mDataView.getContext()), false));
            }

            if (mErrorView != null) {
                helper.setUpErrorView(mErrorView, mRefreshListener);
            } else {
                helper.setUpErrorView(LayoutInflater.from(mDataView.getContext()).inflate(R.layout.base_layout_error_view, new LinearLayout(mDataView.getContext()), false), mRefreshListener);
            }

            if (mLoadingView != null) {
                helper.setUpLoadingView(mLoadingView);
            } else {
                helper.setUpLoadingView(LayoutInflater.from(mDataView.getContext()).inflate(R.layout.layout_loadingview, new LinearLayout(mDataView.getContext()), false));
            }
            return helper;
        }


    }

}
