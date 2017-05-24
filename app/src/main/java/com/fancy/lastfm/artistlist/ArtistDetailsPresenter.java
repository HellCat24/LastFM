package com.fancy.lastfm.artistlist;

import com.fancy.lastfm.App;
import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.mvp.presenter.BasePresenter;
import com.fancy.lastfm.rx.BaseProgressSubscriber;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * @author Oleg Mazhukin
 */

public class ArtistDetailsPresenter extends BasePresenter<ArtistDetailView> {

    @Inject
    protected ArtistRepository repository;

    public void onCreate(String artist) {
        subscribeIO(repository.getTopAlbums(artist), new BaseProgressSubscriber<List<Album>>(getView(), errorHandler) {
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
