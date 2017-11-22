package me.threebears.gankio.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import me.threebears.gankio.InitApplication;
import me.threebears.gankio.R;
import me.threebears.gankio.contract.XianduTabContract;
import me.threebears.gankio.di.component.DaggerGankComponent;
import me.threebears.gankio.di.module.GankModule;
import me.threebears.gankio.model.XianduTabEntity;
import me.threebears.gankio.presenter.XianduTabPresenter;
import me.threebears.gankio.ui.adapter.ViewPagerAdapter;

/**
 * Created time 2017/11/17.
 *
 * @author threeBears
 */
public class XianduTabFragment extends BaseFragment implements XianduTabContract.View {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

    @Inject
    public XianduTabPresenter mPresenter;
    private List<Fragment> mFragmentList;

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_tab;
    }

    @Override
    protected void initView(View view) {
        mTabLayout = view.findViewById(R.id.tab_layout);
        mViewPager = view.findViewById(R.id.view_pager);
    }

    @Override
    protected void initData() {
        DaggerGankComponent.builder()
                .appComponent(InitApplication.getApplication().getAppComponent())
                .gankModule(new GankModule(this))
                .build()
                .inject(this);
        mPresenter.getXianduTabList();
    }

    @Override
    public void onResultTabList(List<XianduTabEntity> list) {
        List<String> titleList = new ArrayList<>();
        mFragmentList = new ArrayList<>(list.size());
        for (XianduTabEntity entity : list) {
            titleList.add(entity.getTitle());
            mFragmentList.add(XianduFragment.newXianduFragment(entity.getUrl()));
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), titleList, mFragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(mFragmentList.size() - 1);
        mViewPager.setCurrentItem(0);
        mTabLayout.setTabMode(TabLayout.MODE_SCROLLABLE);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    @Override
    public void onFailed(String msg) {
        Log.e("wz", msg);
        Toast.makeText(getContext(), "error:" + msg, Toast.LENGTH_SHORT).show();
    }

    public void onDoubleClick() {
        if (mFragmentList == null || mFragmentList.isEmpty()) {
            return;
        }
        int currentItem = mViewPager.getCurrentItem();
        if (mFragmentList != null && !mFragmentList.isEmpty()) {
            XianduFragment fragment = (XianduFragment) mFragmentList.get(currentItem);
            fragment.onDoubleClick();
        }
    }

}
