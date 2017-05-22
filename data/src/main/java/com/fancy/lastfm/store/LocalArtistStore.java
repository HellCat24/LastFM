package com.fancy.lastfm.store;

import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.entity.DaoSession;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Oleg Mazhukin
 */

public class LocalArtistStore implements ArtistStore {

    private DaoSession daoSession;

    public LocalArtistStore(DaoSession daoSession) {
        this.daoSession = daoSession;
    }

    public void saveArtistList(List<Artist> artistList){
        daoSession.insert(artistList);
    }

    public void saveAlbumList(List<Album> albumList){
        daoSession.insert(albumList);
    }

    @Override
    public Observable<List<Artist>> getTopArtist(String country) {
        return Observable.just(daoSession.getArtistDao().queryBuilder().list());
    }

    @Override
    public Observable<List<Album>> getTopAlbum(String artist) {
        return Observable.just(daoSession.getAlbumDao().queryBuilder().list());
    }

    public String getSelectedCountry(){
        return null;
    }

    public void saveSelectedCountry(String country){

    }
}
