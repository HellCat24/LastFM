package com.fancy.lastfm.store;

import com.fancy.lastfm.CacheState;
import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.entity.Artist;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.never;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Oleg on 21.05.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArtistRepositoryTest {

    private static final String TEST_COUNTRY = "Canada";

    @Mock
    private LocalArtistStore localArtistStore;
    @Mock
    private RemoteArtistStore remoteArtistStore;

    private ArtistRepository repository;

    @Before
    public void setUp() {
        repository = spy(new ArtistRepository(CacheState.REMOTE_THEN_LOCAL, localArtistStore, remoteArtistStore));

        when(remoteArtistStore.getTopArtist(TEST_COUNTRY)).thenReturn(Observable.<List<Artist>>empty());
        when(localArtistStore.getTopArtist(TEST_COUNTRY)).thenReturn(Observable.<List<Artist>>empty());

        when(localArtistStore.getSelectedCountry()).thenReturn(TEST_COUNTRY);
    }

    @Test
    public void thatInitialStateRemoteThenLocal() {
        Assert.assertTrue(repository.getCacheState() == CacheState.REMOTE_THEN_LOCAL);
    }

    @Test
    public void remoteThenLocalStateCallRemoteThenLocal() {
        Assert.assertTrue(repository.getCacheState() == CacheState.REMOTE_THEN_LOCAL);
    }

    @Test
    public void localStateCallOnlyLocal() {
        repository.getTopArtist();

        verify(localArtistStore).getTopArtist(repository.getSelectedCountry());
        verify(remoteArtistStore, never()).getTopArtist(localArtistStore.getSelectedCountry());
    }

    @Test
    public void remoteStateCallOnlyRemote() {
        repository.getTopArtist();

        verify(remoteArtistStore).getTopArtist(repository.getSelectedCountry());
        verify(localArtistStore, never()).getTopArtist(localArtistStore.getSelectedCountry());
    }
}
