package com.fancy.lastfm.artistdetail.list;

import com.fancy.lastfm.App;
import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.mvp.presenter.BasePresenter;
import com.fancy.lastfm.rx.BaseProgressSubscriber;
import com.fancy.lastfm.rx.BaseSubscriber;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.annotations.NonNull;

/**
 * @author Oleg Mazhukin
 */

public class TopArtistListPresenter extends BasePresenter<TopArtistView> {

    @Inject
    protected ArtistRepository repository;

    public void onCreate() {
        loadArtist();
    }

    public void onCountrySelected(String country) {
        subscribeIO(repository.saveCountry(country), new BaseSubscriber(getView(), errorHandler) {
            @Override
            public void onNext(@NonNull Object o) {
                loadArtist();
            }
        });
    }

    @Override
    public void initComponents() {
        App.getAppComponent().inject(this);
    }

    private void loadArtist() {
        subscribeIO(repository.getTopArtist(), (new BaseProgressSubscriber<List<Artist>>(getView(), errorHandler) {

            @Override
            public void onNext(@NonNull List<Artist> list) {
                getView().showArtists(list);
            }
        }));
    }
}
