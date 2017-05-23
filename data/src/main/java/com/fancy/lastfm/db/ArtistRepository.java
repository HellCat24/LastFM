package com.fancy.lastfm.db;

import com.fancy.lastfm.CacheState;
import com.fancy.lastfm.db.base.CacheRepository;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.store.LocalArtistStore;
import com.fancy.lastfm.store.RemoteArtistStore;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

/**
 * @author Oleg Mazhukin
 */
public class ArtistRepository extends CacheRepository {

    private LocalArtistStore localArtistStore;
    private RemoteArtistStore remoteArtistStore;

    public ArtistRepository(CacheState cacheState, LocalArtistStore localArtistStore, RemoteArtistStore remoteArtistStore) {
        super(cacheState);
        this.localArtistStore = localArtistStore;
        this.remoteArtistStore = remoteArtistStore;
    }

    public Observable<List<Artist>> getTopArtist() {
        switch (cacheState) {
            case LOCAL:
                return getLocalTopArtist();
            case REMOTE:
                return getRemoteTopArtist();
            default:
                return Observable.concatDelayError(Arrays.asList(getLocalTopArtist(), getRemoteTopArtist()));
        }
    }

    public Observable<List<Album>> getTopAlbums(String artist) {
        switch (cacheState) {
            case LOCAL:
                return getLocalTopAlbum(artist);
            case REMOTE:
                return getRemoteTopAlbum(artist);
            default:
                return Observable.concatDelayError(Arrays.asList(getLocalTopAlbum(artist), getRemoteTopAlbum(artist)));
        }
    }

    private Observable<List<Artist>> getLocalTopArtist() {
        return Observable.defer(() -> localArtistStore.getTopArtist(getSelectedCountry()));
    }

    public String getSelectedCountry() {
        //return "Spain";
        return localArtistStore.getSelectedCountry();
    }

    public Observable saveCountry(String country) {
        return Observable.just(country).map(s -> localArtistStore.saveSelectedCountry(s));
    }

    private Observable<List<Album>> getLocalTopAlbum(String artist) {
        return Observable.defer(() -> localArtistStore.getTopAlbum(artist));
    }

    private Observable<List<Artist>> getRemoteTopArtist() {
        return Observable.defer(() -> remoteArtistStore.getTopArtist(getSelectedCountry()).doAfterNext(artists -> localArtistStore.saveArtistList(artists)));
    }

    private Observable<List<Album>> getRemoteTopAlbum(String artist) {
        return Observable.defer(() -> getRemoteTopAlbum(artist).doAfterNext(albums -> localArtistStore.saveAlbumList(albums)));
    }
}
