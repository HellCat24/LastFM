package com.fancy.lastfm.artist.detail;

import com.fancy.lastfm.App;
import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.mvp.presenter.BasePresenter;
import com.fancy.lastfm.rx.BaseProgressSubscriber;
import com.fancy.lastfm.rx.ErrorMessageProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.annotations.NonNull;

/**
 * @author Oleg Mazhukin
 */

public class ArtistDetailsPresenter extends BasePresenter<ArtistDetailView> {

    @Inject
    protected ArtistRepository repository;

    public void onCreate(String artist) {
        subscribeIO(repository.getTopAlbums(artist)).subscribe(new BaseProgressSubscriber<List<Album>>(getView(), errorHandler) {
            @Override
            public void onNext(@NonNull List<Album> list) {
                getView().showAlbums(list);
            }
        });
    }

    @Override
    public void initComponents() {
        App.getAppComponent().inject(this);
    }
}
