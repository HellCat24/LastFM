package com.fancy.lastfm.mvp.presenter;

import android.support.annotation.VisibleForTesting;

import com.fancy.lastfm.rx.ErrorHandler;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.observers.DisposableObserver;

/**
 * @author Oleg Mazhukin
 */

public abstract class BasePresenter<T> {

    @VisibleForTesting
    @Inject
    public Function<Observable, Observable> observableSchedulerStrategy;
    @Inject
    protected ErrorHandler errorHandler;

    private T view;
    private CompositeDisposable compositeSubscription = new CompositeDisposable();

    public void attachView(T view) {
        this.view = view;
        initComponents();
    }

    public void detachView() {
        this.view = null;
        compositeSubscription.clear();
    }

    public T getView() {
        return view;
    }

    protected <V> void subscribeIO(Observable<V> observable, DisposableObserver<V> observer) {
        try {
            compositeSubscription.add((Disposable) observableSchedulerStrategy.apply(observable).subscribeWith(observer));
        } catch (Exception e) {
            throw new IllegalStateException("Failed to subscribe" + observable + " with " + observable);
        }
    }

    public abstract void initComponents();
}
