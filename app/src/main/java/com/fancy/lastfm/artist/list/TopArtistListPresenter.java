package com.fancy.lastfm.artist.list;

import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.presenter.BasePresenter;
import com.fancy.lastfm.rx.BaseSubscriber;

import java.util.List;

import io.reactivex.annotations.NonNull;

/**
 * Created by Oleg on 21.05.2017.
 */

public class TopArtistListPresenter extends BasePresenter<TopArtistView>{

    private ArtistRepository repository;

    public void onCreate(){
        subscribeIO(repository.getTopArtist()).subscribe(new BaseSubscriber<List<Artist>>(view){
            @Override
            public void onNext(@NonNull List<Artist> list) {
                view.showArtists(list);
            }
        });
    }
}
