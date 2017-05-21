package com.fancy.lastfm.artist.detail;

import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.mvp.presenter.BasePresenter;
import com.fancy.lastfm.rx.BaseProgressSubscriber;
import com.fancy.lastfm.rx.ErrorMessageProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * Created by Oleg on 21.05.2017.
 */

public class ArtistDetailsPresenter extends BasePresenter<ArtistDetailView> {

    @Inject
    protected ArtistRepository repository;
    @Inject
    protected ErrorMessageProvider errorHanlder;

    @Inject
    public ArtistDetailsPresenter(ArtistRepository repository, ErrorMessageProvider errorHanlder) {
        this.repository = repository;
        this.errorHanlder = errorHanlder;
    }

    public void onCreate(String artist) {
        subscribeIO(repository.getTopAlbum(artist)).subscribe(new BaseProgressSubscriber<List<Album>>(getView(), errorHanlder) {
            @Override
            public void onNext(@NonNull List<Album> list) {
                getView().showAlbums(list);
            }
        });
    }
}
