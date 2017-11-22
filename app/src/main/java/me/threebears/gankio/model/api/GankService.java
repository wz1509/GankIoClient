package me.threebears.gankio.model.api;

import io.reactivex.Observable;
import me.threebears.gankio.model.GankEntity;
import me.threebears.gankio.model.HttpResult;
import retrofit2.http.GET;
import retrofit2.http.Path;

/**
 * Created time 2017/11/16.
 *
 * @author threeBears
 */
public interface GankService {

    String BASE_URL = "http://gank.io/";

    /**
     * 请求gank.io
     *
     * @param category 类型
     * @param count    数量
     * @param page     页码
     * @return 数据集合
     */
    @GET("api/data/{category}/{count}/{page}")
    Observable<HttpResult<GankEntity>> getGankList(@Path("category") String category,
                                                   @Path("count") int count,
                                                   @Path("page") int page);

}
