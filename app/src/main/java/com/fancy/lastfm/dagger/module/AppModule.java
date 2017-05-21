package com.fancy.lastfm.dagger.module;

import android.app.Application;

import com.fancy.lastfm.CacheState;
import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.store.LocalArtistStore;
import com.fancy.lastfm.store.RemoteArtistStore;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by Oleg on 21.05.2017.
 */
@Module
public class AppModule {

    Application mApplication;

    public AppModule(Application application) {
        mApplication = application;
    }

    @Provides
    @Singleton
    Application providesApplication() {
        return mApplication;
    }

    @Provides
    @Singleton
    ArtistRepository providesArtistRepository(LocalArtistStore localArtistStore, RemoteArtistStore remoteArtistStore) {
        return new ArtistRepository(CacheState.LOCAL_THEN_REMOTE, localArtistStore, remoteArtistStore);
    }

    @Provides
    @Singleton
    Function<Observable, Observable> providesSchredulerStrategy() {
        return new Function<Observable, Observable>() {
            @Override
            public Observable apply(@NonNull Observable observable) throws Exception {
                return observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread(), true);
            }
        };
    }
}
