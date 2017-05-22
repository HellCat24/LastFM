package com.fancy.lastfm.rx;

import com.fancy.lastfm.mvp.view.BaseView;

import javax.inject.Inject;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * @author Oleg Mazhukin
 */

public abstract class BaseSubscriber<T> implements Observer<T> {

    protected BaseView view;
    @Inject
    protected ErrorMessageProvider errorHanlder;

    public BaseSubscriber(BaseView view, ErrorMessageProvider errorHanlder) {
        this.view = view;
        this.errorHanlder = errorHanlder;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onError(@NonNull Throwable e) {
        view.showError(errorHanlder.getMessage(e));
    }

    @Override
    public void onComplete() {

    }
}
