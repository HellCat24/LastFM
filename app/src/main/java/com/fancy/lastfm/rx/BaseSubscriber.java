package com.fancy.lastfm.rx;

import com.fancy.lastfm.mvp.view.BaseView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author Oleg Mazhukin
 */

public abstract class BaseSubscriber<T> implements Observer<T> {

    protected BaseView view;
    protected ErrorMessageProvider errorHandler;

    public BaseSubscriber(BaseView view, ErrorMessageProvider errorHandler) {
        this.view = view;
        this.errorHandler = errorHandler;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onError(@NonNull Throwable e) {
        if (errorHandler != null) {
            view.showError(errorHandler.getMessage(e));
        }
        view.hideProgress();
    }

    @Override
    public void onComplete() {

    }
}
