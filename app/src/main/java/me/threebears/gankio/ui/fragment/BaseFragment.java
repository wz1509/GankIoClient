package me.threebears.gankio.ui.fragment;

import android.os.Bundle;
import android.support.annotation.LayoutRes;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.trello.rxlifecycle2.LifecycleTransformer;
import com.trello.rxlifecycle2.components.support.RxFragment;

import me.threebears.gankio.ui.view.BaseView;

/**
 * Created time 2017/11/16.
 *
 * @author threeBears
 */

public abstract class BaseFragment extends RxFragment implements BaseView {

    protected View mRootView;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mRootView == null) {
            mRootView = inflater.inflate(getLayoutId(), container, false);
        }
        return mRootView;
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        if (isLazy()) {
            return;
        }
        initView(view);
        initData();
    }

    /**
     * 是否懒加载
     *
     * @return 默认false
     */
    protected boolean isLazy() {
        return false;
    }

    /**
     * 布局id
     *
     * @return id
     */
    @LayoutRes
    protected abstract int getLayoutId();

    /**
     * 初始化view
     *
     * @param view
     */
    protected abstract void initView(View view);

    /**
     * 这里获取数据，刷新界面
     */
    protected abstract void initData();

    @Override
    public <T> LifecycleTransformer<T> bindToLife() {
        return super.bindToLifecycle();
    }
}
