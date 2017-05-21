package com.fancy.lastfm.dagger;

import com.fancy.lastfm.artist.detail.ArtistDetailsPresenter;
import com.fancy.lastfm.artist.list.TopArtistListPresenter;
import com.fancy.lastfm.dagger.module.AppModule;
import com.fancy.lastfm.dagger.module.DataModule;
import com.fancy.lastfm.dagger.module.NetModule;

import javax.inject.Singleton;

import dagger.Component;

@Singleton
@Component(modules = {AppModule.class, NetModule.class, DataModule.class})
public interface AppComponent {

    void inject(TopArtistListPresenter presenter);

    void inject(ArtistDetailsPresenter presenter);

}