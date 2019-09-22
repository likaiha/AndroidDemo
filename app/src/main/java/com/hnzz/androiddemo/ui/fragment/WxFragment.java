package com.hnzz.androiddemo.ui.fragment;


import com.hnzz.androiddemo.R;
import com.hnzz.androiddemo.base.BaseFragment;

/**
 * 首页 fragment 界面
 *
 * @packageName: cn.white.ymc.wanandroidmaster.ui.home
 * @fileName: HomeFragment
 * @date: 2018/7/26  9:10
 * @author: ymc
 * @QQ:745612618
 */

public class WxFragment extends BaseFragment {




    public void scrollToTop() {
//        rv.smoothScrollToPosition(0);
    }

    @Override
    public int getLayoutResID() {
        return  R.layout.fragment_home;
    }

    @Override
    protected void initData() {

    }


    public static WxFragment getInstance() {
        return new WxFragment();
    }
    /**
     * 查找 子 fragment 回到顶部
     */
    public void scrollChildToTop(){
//        WxDetailFragment currentFragment = adapter.getCurrentFragment();
//        currentFragment.scrollToTop();
    }

}
