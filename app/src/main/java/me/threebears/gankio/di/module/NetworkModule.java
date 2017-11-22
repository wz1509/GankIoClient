package me.threebears.gankio.di.module;

import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import me.threebears.gankio.model.api.GankService;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created time 2017/11/16.
 *
 * @author threeBears
 */
@Module
public class NetworkModule {

    @Singleton
    @Provides
    public GankService provideGankService() {
        OkHttpClient.Builder httpClientBuilder = new OkHttpClient.Builder();
        //设置超时时间
        httpClientBuilder.connectTimeout(15 * 1000, TimeUnit.MILLISECONDS);
        httpClientBuilder.writeTimeout(15 * 1000, TimeUnit.MILLISECONDS);
        httpClientBuilder.readTimeout(15 * 1000, TimeUnit.MILLISECONDS);
        return new Retrofit
                .Builder()
                .client(httpClientBuilder.build())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .baseUrl(GankService.BASE_URL)
                .build()
                .create(GankService.class);
    }


}
