package com.fancy.lastfm.rx;

import com.fancy.lastfm.mvp.view.BaseView;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author Oleg Mazhukin
 */

public abstract class BaseProgressSubscriber<T> extends BaseSubscriber<T> {

    public BaseProgressSubscriber(BaseView view, ErrorMessageProvider errorHanlder) {
        super(view, errorHanlder);
    }

    @Override
    protected void onStart() {
        super.onStart();
        view.showProgress();
    }

    @Override
    public void onComplete() {
        super.onComplete();
        view.hideProgress();
    }
}
