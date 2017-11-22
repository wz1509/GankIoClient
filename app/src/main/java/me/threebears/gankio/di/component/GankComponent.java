package me.threebears.gankio.di.component;

import dagger.Component;
import me.threebears.gankio.di.module.GankModule;
import me.threebears.gankio.di.scope.FragmentScope;
import me.threebears.gankio.ui.fragment.GankFragment;
import me.threebears.gankio.ui.fragment.XianduFragment;
import me.threebears.gankio.ui.fragment.XianduTabFragment;

/**
 * Created time 2017/11/16.
 *
 * @author threeBears
 */
@FragmentScope
@Component(modules = GankModule.class, dependencies = AppComponent.class)
public interface GankComponent {

    void inject(GankFragment gankFragment);

    void inject(XianduTabFragment xianduTabFragment);

    void inject(XianduFragment xianduFragment);
}
