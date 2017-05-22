package com.fancy.lastfm.mvp.presenter;

import android.support.annotation.VisibleForTesting;

import com.fancy.lastfm.rx.ErrorHandler;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;

/**
 * @author Oleg Mazhukin
 */

public class BasePresenter<T> {

    private T view;
    /*package*/ final CompositeDisposable compositeSubscription = new CompositeDisposable();
    @VisibleForTesting
    @Inject
    public Function<Observable, Observable> observableSchedulerStrategy;
    @Inject
    protected ErrorHandler errorHanlder;

    public void attachView(T view) {
        this.view = view;
    }

    public void detachView(T view) {
        this.view = null;
        compositeSubscription.clear();
    }

    public T getView() {
        return view;
    }

    protected Observable subscribeIO(Observable observable) {
        try {
            return observableSchedulerStrategy.apply(observable);
        } catch (Exception e) {
            return Observable.empty();
        }
    }
}
