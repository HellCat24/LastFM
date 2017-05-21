package com.fancy.domain;

/**
 * Created by Oleg on 21.05.2017.
 */

public class BasePresenter<T> {

    private T view;

    public void attachView(T view){
        this.view = view;
    }

    public void detachView(T view){
        this.view = null;
    }
}
