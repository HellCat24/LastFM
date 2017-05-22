package com.fancy.lastfm.store;

import com.fancy.lastfm.api.LastFmApi;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Oleg Mazhukin
 */
@RunWith(MockitoJUnitRunner.class)
public class RemoteArtistStoreTest {

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
