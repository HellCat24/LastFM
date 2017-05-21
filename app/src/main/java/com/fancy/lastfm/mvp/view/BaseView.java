package com.fancy.lastfm.mvp.view;

/**
 * @author Oleg Mazhukin
 */
public interface BaseView {

    void showProgress();

    void hideProgress();

    void showError(String error);
}
