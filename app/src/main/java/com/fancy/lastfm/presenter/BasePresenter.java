package com.fancy.lastfm.presenter;

import android.support.annotation.VisibleForTesting;

import com.fancy.domain.IoSchedulerStrategy;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Oleg on 21.05.2017.
 */

public class BasePresenter<T> {

    private T view;
    /*package*/ final CompositeDisposable compositeSubscription = new CompositeDisposable();
    @Inject
    @IoSchedulerStrategy
    private Function<Observable, Observable> observableSchedulerStrategy;

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
        return observable.flatMap(new Function<Observable, Observable>() {

            @Override
            public Observable apply(@NonNull Observable observable) throws Exception {
                return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread(), true);
            }
        });
    }
}
