package com.hnzz.androiddemo.ui;

import android.Manifest;
import android.content.Context;
import android.content.res.ColorStateList;
import android.support.annotation.NonNull;
import android.support.design.internal.BottomNavigationItemView;
import android.support.design.internal.BottomNavigationMenuView;
import android.support.design.widget.BottomNavigationView;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.hnzz.androiddemo.R;
import com.hnzz.androiddemo.base.BaseActivity;
import com.hnzz.androiddemo.ui.fragment.DemoFragment;
import com.hnzz.androiddemo.ui.fragment.HomeFragment;
import com.hnzz.androiddemo.ui.fragment.MineFragment;
import com.hnzz.androiddemo.ui.fragment.SystemFragment;
import com.hnzz.androiddemo.ui.fragment.WxFragment;
import com.hnzz.androiddemo.util.BottomNavigationViewHelper;
import com.hnzz.androiddemo.util.ConstantUtil;
import com.hnzz.androiddemo.util.SharedPreferenceUtil;
import com.hnzz.androiddemo.util.toast.ToastUtil;
import com.yanzhenjie.permission.Action;
import com.yanzhenjie.permission.AndPermission;
import com.yanzhenjie.permission.Rationale;
import com.yanzhenjie.permission.RequestExecutor;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends BaseActivity {

    @BindView(R.id.toolbar_common)
    Toolbar toolbarCommon;
    @BindView(R.id.frame_layout)
    FrameLayout frameLayout;
    @BindView(R.id.float_button)
    FloatingActionButton floatButton;
    @BindView(R.id.navigation)
    BottomNavigationView navigation;

    private List<Fragment> fragmentList;
    private int lastIndex;
    private long mExitTime;

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {
        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    floatButton.setVisibility(View.VISIBLE);
                    selectFragment(0);
                    return true;
                case R.id.navigation_system:
                    floatButton.setVisibility(View.VISIBLE);
                    selectFragment(1);
                    return true;
                case R.id.navigation_demo:
                    floatButton.setVisibility(View.VISIBLE);
                    selectFragment(2);
                    return true;
                case R.id.navigation_mine:
                    floatButton.setVisibility(View.GONE);
                    selectFragment(3);
                    return true;
                case R.id.navigation_wx:
                    floatButton.setVisibility(View.VISIBLE);
                    selectFragment(4);
                    return true;
                default:
                    break;
            }
            return false;
        }
    };

    @Override
    protected int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    protected void initData() {
        initFragment();
        selectFragment(0);
        requestPermission();
    }

    @Override
    protected void initToolbar() {
        setSupportActionBar(toolbarCommon);
        getSupportActionBar().setTitle(R.string.app_name);
    }

    /**
     * 设置默认选中fragment
     *
     * @param index 碎片fragment
     */
    private void selectFragment(int index) {
        FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
        Fragment currentFragment = fragmentList.get(index);
        Fragment lastFragment = fragmentList.get(lastIndex);
        lastIndex = index;
        ft.hide(lastFragment);
        if (!currentFragment.isAdded()) {
            getSupportFragmentManager().beginTransaction().remove(currentFragment).commit();
            ft.add(R.id.frame_layout, currentFragment);
        }
        ft.show(currentFragment);
        ft.commitAllowingStateLoss();
    }

    @Override
    protected void initView() {
        // 将item 设置为不移动
        BottomNavigationViewHelper.disableShiftMode(navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
        // 设置为蓝色背景
        floatButton.setBackgroundTintList(ColorStateList.valueOf(getResources().getColor(R.color.theme)));

//获取整个的NavigationView
        BottomNavigationMenuView menuView = (BottomNavigationMenuView) navigation.getChildAt(0);

//这里就是获取所添加的每一个Tab(或者叫menu)，
        View tab = menuView.getChildAt(0);
        BottomNavigationItemView itemView = (BottomNavigationItemView) tab;

//加载我们的角标View，新创建的一个布局
        View badge = LayoutInflater.from(this).inflate(R.layout.menu_badge, menuView, false);

//添加到Tab上
        itemView.addView(badge);
        TextView count = (TextView) badge.findViewById(R.id.tv_msg_count);
        count.setText(String.valueOf(1));
//
////如果没有消息，不需要显示的时候那只需要将它隐藏即可
        count.setVisibility(View.GONE);
    }

    @OnClick(R.id.float_button)
    void click(View view) {
        switch (view.getId()) {
            case R.id.float_button:
                scrollToTop();
                break;
            default:
                break;
        }
    }

    /**
     * 滚动置顶
     */
    private void scrollToTop() {
        switch (lastIndex) {
            case 0:
                HomeFragment homeFragment = (HomeFragment) fragmentList.get(0);
                homeFragment.scrollToTop();
                break;
            case 1:
                SystemFragment systemFragment = (SystemFragment) fragmentList.get(1);
                systemFragment.scrollToTop();
                break;
            case 2:
                DemoFragment demoFragment = (DemoFragment) fragmentList.get(2);
                demoFragment.scrollChildToTop();
                break;
            case 4:
                WxFragment wxFragment = (WxFragment) fragmentList.get(4);
                wxFragment.scrollChildToTop();
                break;
            default:
                break;
        }
    }

    /**
     * 初始化碎片
     */
    private void initFragment() {
        fragmentList = new ArrayList<>();
        fragmentList.add(HomeFragment.getInstance());
        fragmentList.add(SystemFragment.getInstance());
        fragmentList.add(DemoFragment.getInstance());
        fragmentList.add(MineFragment.getInstance());
        fragmentList.add(WxFragment.getInstance());
    }


    /**
     * 创建 menu
     *
     * @param menu
     * @return
     */
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main_menu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    /**
     * menu 选择器
     *
     * @param item
     * @return
     */
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.main_menu_hot:
//                JumpUtil.overlay(context, HotActivity.class);
                break;
            case R.id.main_menu_search:
//                JumpUtil.overlay(context, SearechActivity.class);
                break;
            default:
                break;
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((System.currentTimeMillis() - mExitTime) > 2000) {
            ToastUtil.show(activity, getString(R.string.exit_system));
            mExitTime = System.currentTimeMillis();
            return false;
        } else {
            SharedPreferenceUtil.put(activity, ConstantUtil.ISLOGIN, ConstantUtil.FALSE);
            finish();
            return true;
        }
    }


    private void requestPermission() {
        AndPermission.with(this)
                .permission(Manifest.permission.READ_PHONE_STATE,
                        Manifest.permission.ACCESS_FINE_LOCATION,
                        Manifest.permission.ACCESS_COARSE_LOCATION,
                        Manifest.permission.READ_EXTERNAL_STORAGE,
                        Manifest.permission.WRITE_EXTERNAL_STORAGE)
                // 准备方法，和 okhttp 的拦截器一样，在请求权限之前先运行改方法，已经拥有权限不会触发该方法
                .rationale(new Rationale() {
                    @Override
                    public void showRationale(Context context, List<String> permissions, RequestExecutor executor) {
                        // 此处可以选择显示提示弹窗
                        executor.execute();
                    }
                })
                .onGranted(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        ToastUtil.show(activity, "用户给权限啦");
                    }
                })
                .onDenied(new Action() {
                    @Override
                    public void onAction(List<String> permissions) {
                        if (AndPermission.hasAlwaysDeniedPermission(activity, permissions)) {
                            // 打开权限设置页
                            AndPermission.permissionSetting(activity).execute();
                            return;
                        }
                        ToastUtil.show(activity, "用户拒绝权限");
                    }
                }).start();
    }

}
