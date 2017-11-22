package me.threebears.gankio.ui.view;

import com.trello.rxlifecycle2.LifecycleTransformer;

/**
 * Created time 2017/11/16.
 *
 * @author threeBears
 */
public interface BaseView {

    /**
     * 绑定生命周期
     *
     * @param <T>
     * @return
     */
    public <T> LifecycleTransformer<T> bindToLife();

}
