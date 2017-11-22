package me.threebears.gankio.contract;

import java.util.List;

import me.threebears.gankio.model.GankEntity;
import me.threebears.gankio.ui.view.BaseView;

/**
 * Created time 2017/11/16.
 *
 * @author threeBears
 */
public interface GankContract {

    interface View extends BaseView {

        /**
         * 数据回调
         *
         * @param isRefresh 是否刷新
         * @param list      数据集合
         */
        void onResultGankList(boolean isRefresh, List<GankEntity> list);

        /**
         * 请求失败
         *
         * @param isRefresh 是否是刷新
         * @param msg       错误信息
         */
        void onFailed(boolean isRefresh, String msg);
    }

    interface Presenter {

        /**
         * 请求更多
         *
         * @param isRefresh 是否刷新
         * @param category  类型
         */
        void getGankList(boolean isRefresh, String category);
    }

}
