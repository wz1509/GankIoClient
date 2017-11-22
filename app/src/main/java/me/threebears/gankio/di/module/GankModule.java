package me.threebears.gankio.di.module;

import dagger.Module;
import dagger.Provides;
import me.threebears.gankio.contract.GankContract;
import me.threebears.gankio.contract.XianduContract;
import me.threebears.gankio.contract.XianduTabContract;
import me.threebears.gankio.di.scope.FragmentScope;

/**
 * Created time 2017/11/16.
 *
 * @author threeBears
 */
@Module
public class GankModule {

    private GankContract.View mGankView;

    private XianduTabContract.View mXianduTabView;

    private XianduContract.View mXianduView;

    public GankModule(GankContract.View view) {
        this.mGankView = view;
    }

    public GankModule(XianduTabContract.View view) {
        this.mXianduTabView = view;
    }

    public GankModule(XianduContract.View view) {
        this.mXianduView = view;
    }

    @FragmentScope
    @Provides
    public GankContract.View provideGankView() {
        return mGankView;
    }

    @FragmentScope
    @Provides
    public XianduTabContract.View provideXianduTabView() {
        return mXianduTabView;
    }

    @FragmentScope
    @Provides
    public XianduContract.View provideXianduView() {
        return mXianduView;
    }
}
