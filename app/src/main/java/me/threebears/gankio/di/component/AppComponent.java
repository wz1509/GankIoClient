package me.threebears.gankio.di.component;

import android.app.Application;

import javax.inject.Singleton;

import dagger.Component;
import me.threebears.gankio.di.module.AppModule;
import me.threebears.gankio.di.module.NetworkModule;
import me.threebears.gankio.model.api.GankService;

/**
 * Created time 2017/11/16.
 *
 * @author threeBears
 */
@Singleton
@Component(modules = {AppModule.class, NetworkModule.class})
public interface AppComponent {

    Application getApplication();

    GankService getGankService();

}
