package com.fancy.lastfm.rx;

import com.fancy.lastfm.view.base.BaseView;

import io.reactivex.Observer;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Oleg on 21.05.2017.
 */

public abstract class BaseSubscriber<T> implements Observer<T> {

    protected BaseView view;
    private ErrorMessageProvider errorProvider;

    public BaseSubscriber(BaseView view) {
        this.view = view;
    }

    @Override
    public void onSubscribe(@NonNull Disposable d) {

    }

    @Override
    public void onError(@NonNull Throwable e) {
        view.showError(errorProvider.getMessage(e));
    }

    @Override
    public void onComplete() {

    }
}
