package me.threebears.gankio.presenter;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.threebears.gankio.contract.XianduTabContract;
import me.threebears.gankio.model.api.RxSchedulers;
import me.threebears.gankio.model.XianduTabEntity;

/**
 * Created time 2017/11/17.
 *
 * @author threeBears
 */
public class XianduTabPresenter implements XianduTabContract.Presenter {

    private static final String TAG = "wz";

    private XianduTabContract.View mView;

    @Inject
    public XianduTabPresenter(XianduTabContract.View view) {
        this.mView = view;
    }

    @Override
    public void getXianduTabList() {
        Observable.just("http://gank.io/xiandu/")
                .map(result -> {
                    List<XianduTabEntity> list = new ArrayList<>();
                    final Connection connect = Jsoup.connect(result);
                    // 伪装成浏览器抓取
                    connect.header("User-Agent", "Mozilla/5.0 (X11; Linux x86_64; rv:32.0) Gecko/20100101 Firefox/32.0");
                    final Document document = connect.get();
                    Elements containerContent = document.select("div.typo")
                            .get(0)
                            .select("div.container");
                    Elements catsLi = containerContent.select("#xiandu_cat ul li");
                    for (Element element : catsLi) {
                        String href = element.select("a").attr("href");
                        String text = element.text();
                        list.add(new XianduTabEntity(text, href));
                    }
                    return list;
                })
                .compose(RxSchedulers.ioMain())
                .compose(mView.bindToLife())
                .subscribe(list -> mView.onResultTabList(list),
                        throwable -> mView.onFailed(throwable.getMessage()));
    }

}
