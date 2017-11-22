package me.threebears.gankio.ui.activity;

import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.Toolbar;
import android.view.View;

import me.threebears.gankio.R;
import me.threebears.gankio.ui.fragment.GankTabFragment;
import me.threebears.gankio.ui.fragment.MoreFragment;
import me.threebears.gankio.ui.fragment.XianduTabFragment;

/**
 * @author threebears
 */
public class MainActivity extends BaseActivity {

    private Toolbar mToolbar;
    private GankTabFragment mGankTabFragment;
    private XianduTabFragment mXianduTabFragment;
    private MoreFragment mMoreFragment;

    /**
     * 记录底部菜单栏最后一次点击的时间
     */
    private long firstClickTime = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initView();
        showFragment(0);
    }

    private void initView() {
        mToolbar = findViewById(R.id.toolbar);
        setSupportActionBar(mToolbar);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottom_navigation_view);

        bottomNavigationView.setOnNavigationItemSelectedListener(item -> {
            switch (item.getItemId()) {
                case R.id.action_gank:
                    showFragment(0);
                    return true;
                case R.id.action_xd:
                    showFragment(1);
                    return true;
                case R.id.action_more:
                    showFragment(2);
                    return true;
                default:
            }
            return false;
        });
    }

    @Override
    public void onAttachFragment(Fragment fragment) {
        super.onAttachFragment(fragment);
        if (mGankTabFragment == null && fragment instanceof GankTabFragment) {
            mGankTabFragment = (GankTabFragment) fragment;
        }

        if (mXianduTabFragment == null && fragment instanceof XianduTabFragment) {
            mXianduTabFragment = (XianduTabFragment) fragment;
        }

        if (mMoreFragment == null && fragment instanceof MoreFragment) {
            mMoreFragment = (MoreFragment) fragment;
        }
    }

    private void showFragment(final int index) {
        // 开启事务
        FragmentTransaction fragmentTransaction = getSupportFragmentManager().beginTransaction();
        hideFragment(fragmentTransaction);
        switch (index) {
            case 0:
                if (getSupportActionBar() != null) {
                    getSupportActionBar().setTitle("干货");
                    mToolbar.setVisibility(View.VISIBLE);
                }
                if (mGankTabFragment == null) {
                    mGankTabFragment = new GankTabFragment();
                    fragmentTransaction.add(R.id.container, mGankTabFragment);
                } else {
                    // 如果不为空，则直接将它显示出来
                    fragmentTransaction.show(mGankTabFragment);
                }
                break;
            case 1:
                if (getSupportActionBar() != null) {
                    mToolbar.setTitle("闲读");
                    mToolbar.setVisibility(View.VISIBLE);
                }
                if (mXianduTabFragment == null) {
                    mXianduTabFragment = new XianduTabFragment();
                    fragmentTransaction.add(R.id.container, mXianduTabFragment);
                } else {
                    fragmentTransaction.show(mXianduTabFragment);
                }
                break;
            case 2:
                if (getSupportActionBar() != null) {
                    mToolbar.setVisibility(View.GONE);
                }
                if (mMoreFragment == null) {
                    mMoreFragment = new MoreFragment();
                    fragmentTransaction.add(R.id.container, mMoreFragment);
                } else {
                    fragmentTransaction.show(mMoreFragment);
                }
                break;
            default:
                break;
        }
        // 事务提交
        fragmentTransaction.commit();
        doubleClick(index);
    }

    private void hideFragment(FragmentTransaction fragmentTransaction) {
        if (mGankTabFragment != null) {
            fragmentTransaction.hide(mGankTabFragment);
        }
        if (mXianduTabFragment != null) {
            fragmentTransaction.hide(mXianduTabFragment);
        }
        if (mMoreFragment != null) {
            fragmentTransaction.hide(mMoreFragment);
        }
    }

    private void doubleClick(int index) {
        long secondClickTime = System.currentTimeMillis();
        boolean isDoubleClick = (secondClickTime - firstClickTime) < 500;
        if (isDoubleClick) {
            switch (index) {
                case 0:
                    mGankTabFragment.onDoubleClick();
                    break;
                case 1:
                    mXianduTabFragment.onDoubleClick();
                    break;
                default:
            }
        } else {
            firstClickTime = secondClickTime;
        }
    }

}
