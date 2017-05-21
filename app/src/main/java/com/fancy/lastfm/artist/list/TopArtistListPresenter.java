package com.fancy.lastfm.artist.list;

import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.presenter.BasePresenter;
import com.fancy.lastfm.rx.BaseSubscriber;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;

/**
 * Created by Oleg on 21.05.2017.
 */

public class TopArtistListPresenter extends BasePresenter<TopArtistView>{

    @Inject
    protected ArtistRepository repository;

    public TopArtistListPresenter(ArtistRepository repository) {
        this.repository = repository;
    }

    public void onCreate(){
        subscribeIO(repository.getTopArtist()).subscribe(new BaseSubscriber<List<Artist>>(getView()){

            @Override
            public void onNext(@NonNull List<Artist> list) {
                getView().showArtists(list);
            }
        });
    }
}
