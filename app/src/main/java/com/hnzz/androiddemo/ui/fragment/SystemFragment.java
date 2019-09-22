package com.hnzz.androiddemo.ui.fragment;


import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.hnzz.androiddemo.R;
import com.hnzz.androiddemo.adapter.MyQianbaoRecordListRclAdapter;
import com.hnzz.androiddemo.base.BaseFragment;
import com.hnzz.androiddemo.basevaryview.VaryViewHelper;
import com.hnzz.androiddemo.basevaryview.VaryViewHelperUtils;
import com.hnzz.androiddemo.bean.QianbaoBean;
import com.hnzz.androiddemo.util.GsonUtil;
import com.hnzz.androiddemo.util.toast.ToastUtil;
import com.hnzz.androiddemo.widget.footer.MyRefreshFooter;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * 首页 fragment 界面
 *
 * @packageName: cn.white.ymc.wanandroidmaster.ui.home
 * @fileName: HomeFragment
 * @date: 2018/7/26  9:10
 * @author: ymc
 * @QQ:745612618
 */

public class SystemFragment extends BaseFragment {


    @BindView(R.id.recycle_view)
    RecyclerView recycleView;
    @BindView(R.id.refresh_footer)
    MyRefreshFooter refreshFooter;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;
    private int page = 1;
    private MyQianbaoRecordListRclAdapter adapter;
    private List<QianbaoBean.DataBean> mDatas = new ArrayList<>();
    private VaryViewHelper viewHelper;

    public static SystemFragment getInstance() {
        return new SystemFragment();
    }

    public void scrollToTop() {
        recycleView.smoothScrollToPosition(0);
    }

    @Override
    public void onNetChange(int netMobile) {
        ToastUtil.show(activity,  netMobile+ " ");
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_system;
    }

    @Override
    protected void initData() {
        setRefresh();
        initViewHelper();
        onPostObject(true, 1);
    }


    /**
     * SmartRefreshLayout刷新加载
     */
    private void setRefresh() {
        recycleView.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new MyQianbaoRecordListRclAdapter(activity, mDatas);
        recycleView.setAdapter(adapter);
        normalView.setOnRefreshListener(new OnRefreshListener() {
            //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                getSampSheetList();
                onPostObject(true, 1);
                refreshlayout.finishRefresh(1000);
            }
        });
        normalView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                onPostObject(false, page);
                refreshLayout.finishLoadMore(1000);
            }
        });
    }


    private void initViewHelper() {
        viewHelper = VaryViewHelperUtils.getViewHelper(context, normalView, new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (viewHelper != null) {
                    viewHelper.showLoadingView();
                }
                page = 1;
                onPostObject(true, 1);
            }
        });
        viewHelper.showLoadingView();
    }

    /**
     * post提交Object
     *
     * @param b
     */
    public void onPostObject(final boolean b, int page) {


        EasyHttp.post("appapi/user/getFinances/")
//                .baseUrl("http://117.158.202.179:8082/hnsxmj/")
                //如果是body的方式提交object，必须要加GsonConverterFactory.create()
                //他的本质就是把object转成json给到服务器，所以必须要加Gson Converter
                //切记！切记！切记！  本例可能地址不对只做演示用
                .addConverterFactory(GsonConverterFactory.create())
                .params("user_id", String.valueOf(136))
                .params("page", String.valueOf(page))
                .params("page_count", String.valueOf(10))
//                .upObject(object)//这种方式会自己把对象转成json提交给服务器
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        Log.e("Main", "response :  " + e.getMessage());
//                        ToastUtil.show(activity, e.getMessage());
                        viewHelper.showErrorView();
                    }

                    @Override
                    public void onSuccess(String response) {
//                        ToastUtil.show(activity, response);
                        Log.e("fragment", "getCarList" + response);
                        QianbaoBean qianbaoBean = GsonUtil.GsonToBean(response, QianbaoBean.class);
                        if (qianbaoBean.getCode().equals("0")) {

                            List<QianbaoBean.DataBean> data = qianbaoBean.getData();

                            if (data != null) {
                                if (b) {
                                    mDatas.clear();
                                }
                                mDatas.addAll(data);
                                normalView.finishRefresh();
                                adapter.notifyDataSetChanged();
                                if (data.size() == 0) {
                                    normalView.finishLoadMoreWithNoMoreData();
                                } else {
                                    normalView.finishLoadMore();
                                }
                                if (mDatas.size() > 0) {
                                    viewHelper.showDataView();
                                } else {
                                    viewHelper.showEmptyView();
                                }

                            } else {
                                viewHelper.showErrorView();
                            }

                        }

                    }
                });
    }
}
