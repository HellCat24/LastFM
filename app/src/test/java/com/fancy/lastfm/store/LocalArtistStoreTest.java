package com.fancy.lastfm.store;

import com.fancy.lastfm.api.LastFmApi;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;

import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Oleg on 21.05.2017.
 */

public class LocalArtistStoreTest {

    private static final String ARTIST_NAME = "Pink Floyd";
    private static final String TEST_COUNTRY = "Canada";

    @Mock
    private LastFmApi lastFmApi;

    private RemoteArtistStore remoteArtistStore;

    @Before
    public void setUp() {
        remoteArtistStore = new RemoteArtistStore(lastFmApi);
        when(lastFmApi.getTopAlbum(ARTIST_NAME)).thenReturn(Observable.<List<Album>>empty());
        when(lastFmApi.getTopArtist(TEST_COUNTRY)).thenReturn(Observable.<List<Artist>>empty());
    }

    @Test
    public void getTopAlbum() {
        remoteArtistStore.getTopAlbum(ARTIST_NAME);
        verify(lastFmApi).getTopAlbum(ARTIST_NAME);
    }

    @Before
    public void getTopArtist() {
        remoteArtistStore.getTopArtist(TEST_COUNTRY);
        verify(lastFmApi).getTopArtist(TEST_COUNTRY);
    }

}
