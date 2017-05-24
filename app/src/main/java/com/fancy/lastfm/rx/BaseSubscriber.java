package com.fancy.lastfm.rx;

import com.fancy.lastfm.mvp.view.BaseView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.observers.DisposableObserver;

/**
 * @author Oleg Mazhukin
 */

public abstract class BaseSubscriber<T> extends DisposableObserver<T> {

    protected BaseView view;
    protected ErrorMessageProvider errorHandler;

    public BaseSubscriber(BaseView view, ErrorMessageProvider errorHandler) {
        this.view = view;
        this.errorHandler = errorHandler;
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
