package com.fancy.lastfm.artist.detail;

import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.presenter.BasePresenter;
import com.fancy.lastfm.rx.BaseProgressSubscriber;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by Oleg on 21.05.2017.
 */

public class ArtistDetailsPresenter extends BasePresenter<ArtistDetailView> {

    private ArtistRepository repository;

    public ArtistDetailsPresenter(ArtistRepository repository) {
        this.repository = repository;
    }

    public void onCreate(String artist) {
        subscribeIO(repository.getTopAlbum(artist)).subscribe(new BaseProgressSubscriber<List<Album>>(getView()) {
            @Override
            public void onNext(@NonNull List<Album> list) {
                getView().showAlbums(list);
            }
        });
    }
}
