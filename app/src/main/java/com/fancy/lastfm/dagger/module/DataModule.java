package com.fancy.lastfm.dagger.module;

import android.app.Application;

import com.fancy.lastfm.entity.DaoMaster;
import com.fancy.lastfm.entity.DaoSession;
import com.fancy.lastfm.store.LocalArtistStore;

import dagger.Module;
import dagger.Provides;

/**
 * @author Oleg Mazhukin
 */
@Module
public class DataModule {

    private DaoSession daoSession;

    public DataModule(Application application) {
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(application, "notes-db");
        daoSession = new DaoMaster(helper.getWritableDb()).newSession();
    }

    @Provides
    DaoSession providesDaoSession() {
        return daoSession;
    }

    @Provides
    LocalArtistStore providesLocalArtistStore(DaoSession daoSession) {
        return new LocalArtistStore(daoSession);
    }
}
