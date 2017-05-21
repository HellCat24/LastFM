package com.fancy.lastfm.dagger.module;

import com.fancy.lastfm.api.BaseRequestIntercepter;
import com.fancy.lastfm.api.LastFmApi;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.mapper.AlbumMapper;
import com.fancy.lastfm.mapper.ArtistMapper;
import com.fancy.lastfm.store.RemoteArtistStore;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Oleg on 21.05.2017.
 */
@Module
public class NetModule {

    private String baseUrl;
    private String apiKey;

    public NetModule(String baseUrl, String apiKey) {
        this.baseUrl = baseUrl;
        this.apiKey = apiKey;
    }

    @Provides
    @Singleton
    OkHttpClient provideOkHttpClient(BaseRequestIntercepter baseRequestIntercepter) {
        return new OkHttpClient.Builder()
                .addInterceptor(baseRequestIntercepter)
                .build();
    }

    @Provides
    @Singleton
    BaseRequestIntercepter provideBaseRequestIntercepter() {
        return new BaseRequestIntercepter(apiKey);
    }

    @Provides
    @Singleton
    Gson provideGson(){
        return new GsonBuilder()
                .registerTypeAdapter(new TypeToken<ArrayList<Artist>>(){}.getType(), new ArtistMapper())
                .registerTypeAdapter(new TypeToken<ArrayList<Album>>(){}.getType(), new AlbumMapper())
                .create();
    }

    @Provides
    @Singleton
    LastFmApi provideLastFmApi(Gson gson, OkHttpClient okHttpClient) {
        Retrofit retrofit = new Retrofit.Builder()
                .addConverterFactory(GsonConverterFactory.create(gson))
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .build();
        return retrofit.create(LastFmApi.class);
    }

    @Provides
    @Singleton
    RemoteArtistStore provideRemoteArtistStore(LastFmApi lastFmApi) {
        return new RemoteArtistStore(lastFmApi);
    }
}
