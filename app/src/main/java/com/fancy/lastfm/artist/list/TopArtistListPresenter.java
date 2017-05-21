package com.fancy.lastfm.artist.list;

import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.presenter.BasePresenter;
import com.fancy.lastfm.rx.BaseSubscriber;
import com.fancy.lastfm.rx.ErrorMessageProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * Created by Oleg on 21.05.2017.
 */

public class TopArtistListPresenter extends BasePresenter<TopArtistView>{

    @Inject
    protected ArtistRepository repository;
    @Inject
    protected ErrorMessageProvider errorHanlder;

    @Inject
    public TopArtistListPresenter(ArtistRepository repository, ErrorMessageProvider errorHanlder) {
        this.repository = repository;
        this.errorHanlder = errorHanlder;
    }

    public void onCreate(){
        subscribeIO(repository.getTopArtist()).subscribe(new BaseSubscriber<List<Artist>>(getView(), errorHanlder){

            @Override
            public void onNext(@NonNull List<Artist> list) {
                getView().showArtists(list);
            }
        });
    }
}
