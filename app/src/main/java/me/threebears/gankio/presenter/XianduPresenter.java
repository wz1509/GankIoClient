package me.threebears.gankio.presenter;

import android.util.Log;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.threebears.gankio.contract.XianduContract;
import me.threebears.gankio.model.api.RxSchedulers;
import me.threebears.gankio.model.XianduEntity;

/**
 * Created time 2017/11/16.
 *
 * @author threeBears
 */
public class XianduPresenter implements XianduContract.Presenter {

    private static final String TAG = "wz";

    private XianduContract.View mView;

    private String nextPageUrl = null;

    @Inject
    public XianduPresenter(XianduContract.View view) {
        this.mView = view;
    }

    @Override
    public void getXianduList(boolean isRefresh, String url) {
        Observable<String> observable;
        if (isRefresh) {
            observable = Observable.just("http://gank.io/" + url);
        } else {
            observable = Observable.just("http://gank.io/" + nextPageUrl);
        }
        observable
                .map(result -> {
                    List<XianduEntity> list = new ArrayList<>();
                    final Connection connect = Jsoup.connect(result);
                    // 伪装成浏览器抓取
                    connect.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/20100101 Firefox/32.0");
                    final Document document = connect.get();
                    Elements containerContent = document.select("div.typo")
                            .get(0)
                            .select("div.container");
                    // 抓取下一页数据url
                    nextPageUrl = containerContent.select("div.row")
                            .select("div")
                            .get(0)
                            .select("a")
                            .last().attr("href");
                    Log.e(TAG, nextPageUrl);

                    Elements items = containerContent.select("div.xiandu_items").select("div.xiandu_item");
                    for (Element item : items) {
                        XianduEntity xianduEntity = new XianduEntity();
                        final Elements leftElement = item.select("div.xiandu_left");
                        String no = leftElement.select("span.xiandu_index").text();
                        String shareUrl = leftElement.select("a").attr("href");
                        String desc = leftElement.select("a").text();
                        String date = leftElement.select("span small").text();

                        final Elements rightElement = item.select("div.xiandu_right").select("a");
                        String categotyUrl = rightElement.attr("href");
                        String categoryName = rightElement.attr("title");
                        String categoryIcon = rightElement.select("img").attr("src");

                        xianduEntity.setNo(no);
                        xianduEntity.setUrl(shareUrl);
                        xianduEntity.setDesc(desc);
                        xianduEntity.setDate(date);

                        xianduEntity.setCategoryIcon(categoryIcon);
                        xianduEntity.setCategoryName(categoryName);
                        xianduEntity.setCategoryUrl(categotyUrl);

                        list.add(xianduEntity);
                    }
                    return list;
                })
                .compose(RxSchedulers.ioMain())
                .compose(mView.bindToLife())
                .subscribe(list -> mView.onResultXianduList(isRefresh, list),
                        throwable -> mView.onFailed(isRefresh, throwable.getMessage()));
    }

}
