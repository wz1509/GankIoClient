package me.threebears.gankio.ui.fragment;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import me.threebears.gankio.R;
import me.threebears.gankio.ui.adapter.ViewPagerAdapter;

/**
 * Created time 2017/11/16.
 *
 * @author threeBears
 */
public class GankTabFragment extends BaseFragment {

    private TabLayout mTabLayout;
    private ViewPager mViewPager;

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
        List<String> titleList = Arrays.asList("Android", "iOS", "前端", "休息视频", "拓展资源");
        mFragmentList = new ArrayList<>(titleList.size());
        for (String str : titleList) {
            mFragmentList.add(GankFragment.newGankFragment(str));
        }
        ViewPagerAdapter adapter = new ViewPagerAdapter(getChildFragmentManager(), titleList, mFragmentList);
        mViewPager.setAdapter(adapter);
        mViewPager.setOffscreenPageLimit(mFragmentList.size() - 1);
        mViewPager.setCurrentItem(0);
        mTabLayout.setupWithViewPager(mViewPager);
    }

    public void onDoubleClick() {
        int currentItem = mViewPager.getCurrentItem();
        if (mFragmentList != null && !mFragmentList.isEmpty()) {
            GankFragment fragment = (GankFragment) mFragmentList.get(currentItem);
            fragment.onDoubleClick();
        }
    }

}
