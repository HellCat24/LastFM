package com.fancy.lastfm.activity;

import android.view.View;
import android.widget.ProgressBar;

import com.fancy.lastfm.R;
import com.fancy.lastfm.mvp.presenter.BasePresenter;

import butterknife.BindView;

/**
 * @author Oleg Mazhukin
 */
public class ProgressActivity<P extends BasePresenter> extends BaseActivity<P> {

    @BindView(R.id.progressBar)
    ProgressBar progressBar;

    public void showProgress() {
        progressBar.setVisibility(View.VISIBLE);
    }

    public void hideProgress() {
        progressBar.setVisibility(View.GONE);
    }
}
