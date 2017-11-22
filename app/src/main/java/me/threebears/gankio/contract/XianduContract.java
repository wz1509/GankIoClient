package me.threebears.gankio.contract;

import java.util.List;

import me.threebears.gankio.model.XianduEntity;
import me.threebears.gankio.ui.view.BaseView;

/**
 * Created time 2017/11/16.
 *
 * @author threeBears
 */
public interface XianduContract {

    interface View extends BaseView {

        void onResultXianduList(boolean isRefresh, List<XianduEntity> list);

        void onFailed(boolean isRefresh, String msg);
    }

    interface Presenter {

        void getXianduList(boolean isRefresh, String url);
    }

}
