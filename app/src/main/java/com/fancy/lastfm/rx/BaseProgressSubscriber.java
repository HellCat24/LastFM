package com.fancy.lastfm.rx;

import com.fancy.lastfm.view.base.BaseView;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Oleg on 21.05.2017.
 */

public abstract class BaseProgressSubscriber<T> extends BaseSubscriber<T> {

    public BaseProgressSubscriber(BaseView view) {
        super(view);
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {
        super.onSubscribe(d);
        view.showProgress();
    }

    @Override
    public void onComplete() {
        super.onComplete();
        view.hideProgress();
    }
}
