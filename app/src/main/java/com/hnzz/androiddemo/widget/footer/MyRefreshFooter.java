package com.hnzz.androiddemo.widget.footer;

import android.content.Context;
import android.graphics.drawable.AnimationDrawable;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hnzz.androiddemo.R;
import com.scwang.smartrefresh.layout.api.RefreshFooter;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * Created by leo on 2019/1/25.
 */
public class MyRefreshFooter extends LinearLayout implements RefreshFooter {


    private Context mContext;
    private TextView tv_des;
    private ImageView mAnimationImg;
    private AnimationDrawable refresh_anim;
    private boolean noMoreData;

    public MyRefreshFooter(Context context) {
        super(context);
        initView(context);
    }


    public MyRefreshFooter(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        initView(context);
    }

    public MyRefreshFooter(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initView(context);
    }


    @Override
    public boolean setNoMoreData(boolean noMoreData) {

        this.noMoreData = noMoreData;
        if (noMoreData) {
            mAnimationImg.setVisibility(GONE);
            tv_des.setText("没有更多数据了...");
        } else {
            mAnimationImg.setVisibility(VISIBLE);
            tv_des.setText("上拉加载");
        }

        return noMoreData;
    }

    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @NonNull
    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;
    }

    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(@NonNull RefreshKernel kernel, int height, int maxDragHeight) {

    }

    @Override
    public void onMoving(boolean isDragging, float percent, int offset, int height, int maxDragHeight) {

    }

    @Override
    public void onReleased(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {

    }

    @Override
    public void onStartAnimator(@NonNull RefreshLayout refreshLayout, int height, int maxDragHeight) {
        start();
    }


    @Override
    public int onFinish(@NonNull RefreshLayout refreshLayout, boolean success) {
        stop();
        return 0;
    }


    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }

    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

    @Override
    public void onStateChanged(@NonNull RefreshLayout refreshLayout, @NonNull RefreshState oldState, @NonNull RefreshState newState) {


        switch (newState) {
            case None:
            case PullDownToRefresh:
                if (noMoreData) {
                    tv_des.setText("没有更多数据了...");
                    mAnimationImg.setVisibility(GONE);
                } else {
                    tv_des.setText("上拉加载");
                    mAnimationImg.setVisibility(VISIBLE);
                }

                break;
            case Refreshing:
                tv_des.setText("正在加载");
                break;
            case ReleaseToRefresh:
                tv_des.setText("释放加载");
                break;
        }

    }


    private void initView(Context context) {
        this.mContext = context;
        LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        if (layoutInflater != null) {
            View view = layoutInflater.inflate(R.layout.base_refresh_head, this);

            tv_des = (TextView) view.findViewById(R.id.tv_des);

            mAnimationImg = (ImageView) view.findViewById(R.id.iv_refresh_anim);
            refresh_anim = (AnimationDrawable) ContextCompat.getDrawable(mContext, R.drawable.progressbar);
            //refresh_anim.setOneShot(true);
            mAnimationImg.setImageDrawable(refresh_anim);
            if (noMoreData) {
                mAnimationImg.setVisibility(GONE);
                tv_des.setText("没有更多数据了...");
            } else {
                mAnimationImg.setVisibility(VISIBLE);
                tv_des.setText("上拉加载");
            }
        }
    }

    private void start() {
        tv_des.setText("正在加载");
        if (refresh_anim != null && !refresh_anim.isRunning()) {
            refresh_anim.start();
        }
    }

    private void stop() {
        tv_des.setText("加载完成");
        if (refresh_anim != null && refresh_anim.isRunning()) {
            refresh_anim.stop();
        }
    }


}
