package com.fancy.lastfm.artist.detail;

import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.presenter.BasePresenter;
import com.fancy.lastfm.rx.BaseSubscriber;

import java.util.List;

import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;

/**
 * Created by Oleg on 21.05.2017.
 */

public class ArtistDetailsPresenter extends BasePresenter<ArtistDetailView> {

    private ArtistRepository repository;

    public void onCreate() {
        subscribeIO(repository.getTopAlbum())
                .doOnSubscribe(new Consumer<Disposable>() {
                    @Override
                    public void accept(@NonNull Disposable disposable) throws Exception {
                        view.showProgress();
                    }
                })
                .subscribe(new BaseSubscriber<List<Album>>(view) {
                    @Override
                    public void onNext(@NonNull List<Album> list) {
                        view.showAlbums(list);
                    }
                });
    }
}
