package com.fancy.lastfm.view.base;

/**
 * @author Oleg Mazhukin
 */
public interface BaseView {

    void showProgress();

    void hideProgress();

    void showError(String error);
}
