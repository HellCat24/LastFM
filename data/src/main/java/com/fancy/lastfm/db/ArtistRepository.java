package com.fancy.lastfm.db;

import com.fancy.lastfm.CacheState;
import com.fancy.lastfm.db.base.CacheRepository;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.store.ArtistStore;
import com.fancy.lastfm.store.LocalArtistStore;
import com.fancy.lastfm.store.RemoteArtistStore;

import java.util.Arrays;
import java.util.List;

import io.reactivex.Observable;

/**
 * Created by Oleg on 21.05.2017.
 */
public class ArtistRepository extends CacheRepository implements ArtistStore {

    private LocalArtistStore localArtistStore;
    private RemoteArtistStore remoteArtistStore;

    public ArtistRepository(CacheState cacheState) {
        super(cacheState);
    }

    @Override
    public Observable<List<Artist>> getTopArtist() {
        return Observable.concatDelayError(Arrays.asList(getRemoteTopArtist(), localArtistStore.getTopArtist()));
    }

    @Override
    public Observable<List<Album>> getTopAlbum() {
        return Observable.concatDelayError(Arrays.asList(remoteArtistStore.getTopAlbum(), localArtistStore.getTopAlbum()));
    }

    private Observable<List<Artist>> getRemoteTopArtist() {
        return remoteArtistStore.getTopArtist().doAfterNext(artists -> localArtistStore.saveArtistList(artists));
    }

    private Observable<List<Album>> getRemoteTopAlbums() {
        return remoteArtistStore.getTopAlbum().doAfterNext(albums -> localArtistStore.saveAlbumList(albums));
    }

}
