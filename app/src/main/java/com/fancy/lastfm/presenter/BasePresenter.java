package com.fancy.lastfm.presenter;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;

/**
 * Created by Oleg on 21.05.2017.
 */

public class BasePresenter<T> {

    private T view;
    /*package*/ final CompositeDisposable compositeSubscription = new CompositeDisposable();
    @Inject
    protected Function<Observable, Observable> observableSchedulerStrategy;

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
