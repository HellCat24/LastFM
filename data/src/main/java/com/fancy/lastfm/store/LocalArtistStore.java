package com.fancy.lastfm.store;

import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.AlbumDao;
import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.entity.ArtistDao;
import com.fancy.lastfm.entity.Country;
import com.fancy.lastfm.entity.DaoSession;

import java.util.List;

import io.reactivex.Observable;

/**
 * @author Oleg Mazhukin
 */

public class LocalArtistStore implements ArtistStore {

    private DaoSession daoSession;
    private String defaultCountry;

    public LocalArtistStore(DaoSession daoSession, String defaultCountry) {
        this.daoSession = daoSession;
        this.defaultCountry = defaultCountry;
    }

    @Override
    public Observable<List<Artist>> getTopArtist(String country) {
        return Observable.fromCallable(() -> daoSession.getArtistDao().queryBuilder().orderAsc(ArtistDao.Properties.Name).list());
    }

    @Override
    public Observable<List<Album>> getTopAlbum(String artist) {
        return Observable.fromCallable(() -> daoSession.getAlbumDao().queryBuilder().where(AlbumDao.Properties.ArtistName.eq(artist)).list());
    }

    public String getSelectedCountry() {
        Country savedCountry = daoSession.getCountryDao().queryBuilder().unique();
        return savedCountry == null ? defaultCountry : savedCountry.getName();
    }

    public Observable<List<Artist>> saveArtistList(List<Artist> artistList) {
        daoSession.getArtistDao().insertOrReplaceInTx(artistList);
        return getTopArtist(getSelectedCountry());
    }

    public void saveAlbumList(List<Album> albumList) {
        daoSession.getAlbumDao().insertOrReplaceInTx(albumList);
    }

    public boolean saveSelectedCountry(String country) {
        daoSession.deleteAll(Country.class);
        daoSession.deleteAll(Artist.class);
        daoSession.deleteAll(Album.class);
        daoSession.getCountryDao().insert(new Country(country));
        return true;
    }
}
