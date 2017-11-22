package me.threebears.gankio.presenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import me.threebears.gankio.contract.GankContract;
import me.threebears.gankio.model.GankEntity;
import me.threebears.gankio.model.api.GankService;
import me.threebears.gankio.model.HttpResult;
import me.threebears.gankio.model.api.RxSchedulers;

/**
 * Created time 2017/11/16.
 *
 * @author threeBears
 */
public class GankPresenter implements GankContract.Presenter {

    private GankService mService;
    private GankContract.View mView;

    private final static int COUNT = 15;
    private int mPage = 1;

    @Inject
    public GankPresenter(GankService service, GankContract.View view) {
        mService = service;
        mView = view;
    }

    @Override
    public void getGankList(boolean isRefresh, String category) {
        Observable<HttpResult<GankEntity>> observable;
        if (isRefresh) {
            observable = mService.getGankList(category, COUNT, 1);
        } else {
            observable = mService.getGankList(category, COUNT, mPage);
        }
        observable
                .compose(RxSchedulers.ioMain())
                .compose(mView.bindToLife())
                .subscribe(gankEntityHttpResult -> {
                    if (gankEntityHttpResult.isSuccess()) {
                        if (isRefresh) {
                            mPage = 1;
                        }
                        mPage++;
                        mView.onResultGankList(isRefresh, gankEntityHttpResult.getGankList());
                    }
                }, throwable -> mView.onFailed(isRefresh, throwable.getMessage()));
    }

}
