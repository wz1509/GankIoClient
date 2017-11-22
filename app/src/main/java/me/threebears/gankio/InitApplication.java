package me.threebears.gankio;

import android.app.Application;

import me.threebears.gankio.di.component.AppComponent;
import me.threebears.gankio.di.component.DaggerAppComponent;
import me.threebears.gankio.di.module.AppModule;
import me.threebears.gankio.di.module.NetworkModule;

/**
 * Created time 2017/11/15.
 *
 * @author threeBears
 */
public class InitApplication extends Application {

    private static InitApplication mApplication;
    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mApplication = this;
    }

    public static InitApplication getApplication() {
        return mApplication;
    }

    public AppComponent getAppComponent() {
        if (mAppComponent == null) {
            mAppComponent = DaggerAppComponent.builder()
                    .networkModule(new NetworkModule())
                    .appModule(new AppModule(this))
                    .build();
        }
        return mAppComponent;
    }

}
