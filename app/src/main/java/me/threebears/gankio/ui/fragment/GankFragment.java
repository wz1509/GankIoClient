package me.threebears.gankio.ui.fragment;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import java.util.List;

import javax.inject.Inject;

import me.threebears.gankio.InitApplication;
import me.threebears.gankio.R;
import me.threebears.gankio.contract.GankContract;
import me.threebears.gankio.di.component.DaggerGankComponent;
import me.threebears.gankio.di.module.GankModule;
import me.threebears.gankio.model.GankEntity;
import me.threebears.gankio.presenter.GankPresenter;
import me.threebears.gankio.ui.activity.DetailActivity;
import me.threebears.gankio.ui.adapter.GankAdapter;

/**
 * Created time 2017/11/15.
 *
 * @author threeBears
 */
public class GankFragment extends BaseLazyFragment implements GankContract.View {

    private static final String TAG = GankFragment.class.getName();

    private static final String KEY_CATEGORY = "key_category";

    public static Fragment newGankFragment(String category) {
        Fragment fragment = new GankFragment();
        Bundle args = new Bundle();
        args.putString(KEY_CATEGORY, category);
        fragment.setArguments(args);
        return fragment;
    }

    private SwipeRefreshLayout mSwipeRefreshLayout;
    private RecyclerView mRecyclerView;
    private GankAdapter mAdapter;

    @Inject
    public GankPresenter mPresenter;

    @Override
    protected int getLayoutId() {
        return R.layout.layout_refresh_recycler_view;
    }

    @Override
    protected void initView(View view) {
        mSwipeRefreshLayout = mRootView.findViewById(R.id.swipeRefreshLayout);
        mRecyclerView = mRootView.findViewById(R.id.recyclerView);
    }

    @Override
    protected void onInvisible() {
    }

    @Override
    protected void initData() {
        DaggerGankComponent.builder()
                .appComponent(InitApplication.getApplication().getAppComponent())
                .gankModule(new GankModule(this))
                .build()
                .inject(this);

        mAdapter = new GankAdapter(getContext());
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        mRecyclerView.setAdapter(mAdapter);

        mSwipeRefreshLayout.setOnRefreshListener(() ->
                mPresenter.getGankList(true, getCategory()));
        mAdapter.setOnLoadMoreListener(() ->
                mPresenter.getGankList(false, getCategory()), mRecyclerView);

        mAdapter.setOnItemChildClickListener((adapter, view, position) -> {
            Toast.makeText(getContext(), "123", Toast.LENGTH_SHORT).show();
        });

        mAdapter.setOnItemClickListener((adapter, view, position) ->
                DetailActivity.startActivity(getActivity(), mAdapter.getData().get(position).getUrl()));

        mSwipeRefreshLayout.setRefreshing(true);
        mPresenter.getGankList(true, getCategory());
    }

    private String getCategory() {
        return getArguments().getString(KEY_CATEGORY);
    }

    @Override
    public void onResultGankList(boolean isRefresh, List<GankEntity> list) {
        closeSwipeRefreshLayout();
        if (isRefresh) {
            if (list.isEmpty()) {

            } else {
                mAdapter.setNewData(list);
            }
        } else {
            if (list.isEmpty()) {

            } else {
                mAdapter.addData(list);
            }
        }
        mAdapter.loadMoreComplete();
    }

    @Override
    public void onFailed(boolean isRefresh, String msg) {
        Log.e(TAG, "onFailed: " + msg);
        closeSwipeRefreshLayout();
        if (isRefresh) {

        } else {
            mAdapter.loadMoreFail();
        }
    }

    public void onDoubleClick() {
        int firstVisibleItemPosition = ((LinearLayoutManager) mRecyclerView.getLayoutManager())
                .findFirstVisibleItemPosition();
        if (firstVisibleItemPosition == 0) {
            mSwipeRefreshLayout.setRefreshing(true);
            mPresenter.getGankList(true, getCategory());
            return;
        }
        mRecyclerView.smoothScrollToPosition(0);
    }

    private void closeSwipeRefreshLayout() {
        if (mSwipeRefreshLayout.isRefreshing()) {
            mSwipeRefreshLayout.setRefreshing(false);
        }
    }
}
