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

import java.util.ArrayList;
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
        repository = spy(new ArtistRepository(CacheState.LOCAL_THEN_REMOTE, localArtistStore, remoteArtistStore));

        when(remoteArtistStore.getTopArtist(TEST_COUNTRY)).thenReturn(Observable.<List<Artist>>empty());
        when(localArtistStore.getTopArtist(TEST_COUNTRY)).thenReturn(Observable.<List<Artist>>empty());

        when(localArtistStore.getSelectedCountry()).thenReturn(TEST_COUNTRY);
    }

    @Test
    public void thatInitialStateRemoteThenLocal() {
        Assert.assertTrue(repository.getCacheState() == CacheState.LOCAL_THEN_REMOTE);
    }

    @Test
    public void localStateCallOnlyLocal() {
        repository.setCacheState(CacheState.LOCAL);
        repository.getTopArtist().test();

        verify(localArtistStore).getTopArtist(localArtistStore.getSelectedCountry());
        verify(remoteArtistStore, never()).getTopArtist(localArtistStore.getSelectedCountry());
    }

    @Test
    public void remoteStateCallOnlyRemote() {
        ArrayList<Artist> arrayList = new ArrayList<>();
        when(remoteArtistStore.getTopArtist(TEST_COUNTRY)).thenReturn(Observable.just(arrayList));

        repository.setCacheState(CacheState.REMOTE);
        repository.getTopArtist().test();

        verify(remoteArtistStore).getTopArtist(localArtistStore.getSelectedCountry());
        verify(localArtistStore).saveArtistList(arrayList);
    }
}
