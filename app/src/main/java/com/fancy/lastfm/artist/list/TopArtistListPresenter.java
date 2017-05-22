package com.fancy.lastfm.artist.list;

import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.mvp.presenter.BasePresenter;
import com.fancy.lastfm.rx.BaseSubscriber;
import com.fancy.lastfm.rx.ErrorMessageProvider;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * @author Oleg Mazhukin
 */

public class TopArtistListPresenter extends BasePresenter<TopArtistView>{

    @Inject
    protected ArtistRepository repository;
    @Inject
    protected ErrorMessageProvider errorHandler;

    @Inject
    public TopArtistListPresenter(ArtistRepository repository, ErrorMessageProvider errorHandler) {
        this.repository = repository;
        this.errorHandler = errorHandler;
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
