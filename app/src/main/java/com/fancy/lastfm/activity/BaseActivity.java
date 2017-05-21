package com.fancy.lastfm.activity;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.fancy.lastfm.mvp.presenter.BasePresenter;

import butterknife.ButterKnife;

/**
 * Created by Oleg on 21.05.2017.
 */
public class BaseActivity<T extends BasePresenter> extends AppCompatActivity {

    protected T presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);

    }

    @Override
    protected void onResume() {
        super.onResume();
        presenter.attachView(this);
    }

    @Override
    protected void onStop() {
        super.onStop();
        presenter.detachView(this);
    }
}
