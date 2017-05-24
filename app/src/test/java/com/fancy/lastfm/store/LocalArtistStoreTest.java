package com.fancy.lastfm.store;

import com.fancy.lastfm.BuildConfig;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.entity.Country;
import com.fancy.lastfm.entity.DaoMaster;
import com.fancy.lastfm.entity.DaoSession;

import org.greenrobot.greendao.database.Database;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.RuntimeEnvironment;
import org.robolectric.annotation.Config;

import java.util.ArrayList;
import java.util.List;

import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertEquals;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;

/**
 * @author Oleg Mazhukin
 */
@RunWith(RobolectricTestRunner.class)
@Config(constants = BuildConfig.class)
public class LocalArtistStoreTest {

    private static final String ARTIST_NAME = "Pink Floyd";
    private static final String TEST_COUNTRY = "Canada";

    @Mock
    private DaoSession daoSession;

    private LocalArtistStore localArtistStore;

    @Before
    public void setUp() {
        DaoMaster.DevOpenHelper openHelper = new DaoMaster.DevOpenHelper(RuntimeEnvironment.application, null);
        Database db = openHelper.getWritableDb();
        daoSession = spy(new DaoMaster(db).newSession());

        localArtistStore = new LocalArtistStore(daoSession, TEST_COUNTRY);
    }

    @Test
    public void getTopAlbum() {
        TestObserver testObserver = localArtistStore.getTopAlbum(ARTIST_NAME).test();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
    }

    @Test
    public void getTopArtist() {
        TestObserver testObserver = localArtistStore.getTopArtist(TEST_COUNTRY).test();
        testObserver.assertComplete();
        testObserver.assertNoErrors();
    }

    @Test
    public void saveTopAlbum() {
        Artist artist = new Artist("1", "test", "123", "http");

        localArtistStore.saveArtistList(new ArrayList<Artist>() {{
            add(artist);
        }});

        TestObserver<List<Artist>> test = localArtistStore.getTopArtist(TEST_COUNTRY).test();
        List<Object> objects = test.getEvents().get(0);
        assertEquals(1, objects.size());
        Artist savedArtist = ((List<Artist>) objects.get(0)).get(0);
        assertEquals(savedArtist.getId(), artist.getId());
        assertEquals(savedArtist.getImageUrl(), artist.getImageUrl());
        assertEquals(savedArtist.getListenersCount(), artist.getListenersCount());
        assertEquals(savedArtist.getName(), artist.getName());
    }

    @Test
    public void saveTopArtist() {
        String artistName = "Rage Against The Machine";
        Album album = new Album("sda", "test", 123, "http", artistName);

        localArtistStore.saveAlbumList(new ArrayList<Album>() {{
            add(album);
        }});

        TestObserver<List<Album>> test = localArtistStore.getTopAlbum(artistName).test();
        List<Object> objects = test.getEvents().get(0);
        assertEquals(1, objects.size());
        Album savedAlbum = ((List<Album>) objects.get(0)).get(0);
        assertEquals(savedAlbum.getId(), album.getId());
        assertEquals(savedAlbum.getName(), album.getName());
        assertEquals(savedAlbum.getPlayCount(), album.getPlayCount());
        assertEquals(savedAlbum.getUrl(), album.getUrl());
        assertEquals(savedAlbum.getArtist(), album.getArtist());
    }

    @Test
    public void saveCountry() {

        localArtistStore.saveSelectedCountry(TEST_COUNTRY);

        verify(daoSession).deleteAll(Country.class);
        verify(daoSession).deleteAll(Artist.class);
        verify(daoSession).deleteAll(Album.class);

        assertEquals(localArtistStore.getSelectedCountry(), TEST_COUNTRY);
    }
}
