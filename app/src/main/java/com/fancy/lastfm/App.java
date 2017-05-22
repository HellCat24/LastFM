package com.fancy.lastfm;

import android.app.Application;

import com.fancy.lastfm.dagger.AppComponent;
import com.fancy.lastfm.dagger.DaggerAppComponent;
import com.fancy.lastfm.dagger.module.AppModule;
import com.fancy.lastfm.dagger.module.DataModule;
import com.fancy.lastfm.dagger.module.NetModule;

/**
 * @author Oleg Mazhukin
 */

public class App extends Application {

    private static AppComponent appComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        appComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .netModule(new NetModule(getString(R.string.base_url), getString(R.string.api_key)))
                .dataModule(new DataModule(this))
                .build();
    }

    public static AppComponent getAppComponent() {
        return appComponent;
    }
}
