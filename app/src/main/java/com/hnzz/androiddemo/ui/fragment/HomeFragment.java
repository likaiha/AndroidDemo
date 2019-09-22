package com.hnzz.androiddemo.ui.fragment;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.hnzz.androiddemo.R;
import com.hnzz.androiddemo.adapter.MyQianbaoRecordListRclAdapter;
import com.hnzz.androiddemo.base.BaseFragment;
import com.hnzz.androiddemo.basevaryview.VaryViewHelper;
import com.hnzz.androiddemo.bean.QianbaoBean;
import com.hnzz.androiddemo.config.Config;
import com.hnzz.androiddemo.util.GsonUtil;
import com.hnzz.androiddemo.util.toast.ToastUtil;
import com.hnzz.androiddemo.widget.SwipeItemLayout;
import com.scwang.smartrefresh.layout.SmartRefreshLayout;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadMoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.zhouyou.http.EasyHttp;
import com.zhouyou.http.callback.SimpleCallBack;
import com.zhouyou.http.exception.ApiException;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;
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

public class HomeFragment extends BaseFragment {


    @BindView(R.id.rv)
    RecyclerView rv;
    @BindView(R.id.normal_view)
    SmartRefreshLayout normalView;
    Unbinder unbinder;
    private int page = 1;
    private MyQianbaoRecordListRclAdapter adapter;
    private List<QianbaoBean.DataBean> mDatas = new ArrayList<>();
    private VaryViewHelper viewHelper;

    public static HomeFragment getInstance() {
        return new HomeFragment();
    }

    public void scrollToTop() {
//        rv.smoothScrollToPosition(0);
    }

    @Override
    public int getLayoutResID() {
        return R.layout.fragment_home;
    }

    @Override
    protected void initData() {
        setRefresh();
        onPostmap(true, 1);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        // TODO: inflate a fragment view
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }


    /**
     * SmartRefreshLayout刷新加载
     */
    private void setRefresh() {
        rv.setLayoutManager(new LinearLayoutManager(activity));
        adapter = new MyQianbaoRecordListRclAdapter(activity, mDatas);
        rv.setAdapter(adapter);


        normalView.setOnRefreshListener(new OnRefreshListener() {
            //下拉刷新
            @Override
            public void onRefresh(RefreshLayout refreshlayout) {
//                getSampSheetList();
                onPostmap(true, 1);
                refreshlayout.finishRefresh(1000);
            }
        });
        normalView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore(@NonNull RefreshLayout refreshLayout) {
                page++;
                onPostmap(false, page);
                refreshLayout.finishLoadMore(1000);
            }
        });
//        normalView.setOnLoadmoreListener(new OnLoadmoreListener() {
//            //上拉加载
//            @Override
//            public void onLoadmore(RefreshLayout refreshlayout) {
//                refreshlayout.finishLoadmore(1000);
//            }
//        });
    }

    /**
     * post提交Object
     *
     * @param b
     */
    public void onPostmap(final boolean b, int page) {


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
//                        Log.e("Main", "response :  " + e.getMessage());
//                        ToastUtil.show(activity, e.getMessage());
//                        viewHelper.showErrorView();
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

    /**
     * post提交Object
     */
    public void onPostObject() {
//        ApiInfo apiInfo = new ApiInfo();
//        ApiInfo.ApiInfoBean apiInfoBean = apiInfo.new ApiInfoBean();
//        apiInfoBean.setApiKey("agentstaskid");
//        apiInfoBean.setApiName("76bb2168-211a-4645-a5cc-ed90386482af");
//        apiInfo.setApiInfo(apiInfoBean);

        JSONObject object = new JSONObject();
        try {
            object.put("type", "J");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        EasyHttp.post("RegistrationApp/getRegistrationList.do")
//                .baseUrl("http://117.158.202.179:8082/hnsxmj/")
                //如果是body的方式提交object，必须要加GsonConverterFactory.create()
                //他的本质就是把object转成json给到服务器，所以必须要加Gson Converter
                //切记！切记！切记！  本例可能地址不对只做演示用
                .addConverterFactory(GsonConverterFactory.create())
                .upObject(object)//这种方式会自己把对象转成json提交给服务器
                .execute(new SimpleCallBack<String>() {
                    @Override
                    public void onError(ApiException e) {
                        Log.e("Main", "response :  " + e.getMessage());
//                        ToastUtil.show(activity, e.getMessage());
                    }

                    @Override
                    public void onSuccess(String response) {
//                        ToastUtil.show(activity, response);
                    }
                });
    }
}
