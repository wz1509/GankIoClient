package me.threebears.gankio.contract;

import java.util.List;

import me.threebears.gankio.model.XianduTabEntity;
import me.threebears.gankio.ui.view.BaseView;

/**
 * Created time 2017/11/17.
 *
 * @author threeBears
 */
public interface XianduTabContract {

    interface View extends BaseView {

        void onResultTabList(List<XianduTabEntity> list);

        void onFailed(String msg);
    }

    interface Presenter {

        void getXianduTabList();
    }

}
