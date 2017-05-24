package com.fancy.lastfm.dagger.module;

import android.app.Application;

import com.fancy.lastfm.CacheState;
import com.fancy.lastfm.R;
import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.rx.ErrorHandler;
import com.fancy.lastfm.rx.ErrorMessageProvider;
import com.fancy.lastfm.store.LocalArtistStore;
import com.fancy.lastfm.store.RemoteArtistStore;



import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import io.reactivex.Observable;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;

/**
 * @author Oleg Mazhukin
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
        return observable -> observable.subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread(), true);
    }

    @Provides
    @Singleton
    ErrorHandler providesErrorMessageProvider() {
        return new ErrorHandler(new ErrorHandler.ErrorMessageProvider() {
            @Override
            public String getConnectionTimeOut() {
                return mApplication.getString(R.string.connectiom_time_out);
            }

            @Override
            public String getConnectionError() {
                return mApplication.getString(R.string.connection_error);
            }

            @Override
            public String getDefaultError() {
                return mApplication.getString(R.string.server_error);
            }
        });
    }
}
