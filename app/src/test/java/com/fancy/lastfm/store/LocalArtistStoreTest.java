package com.fancy.lastfm.store;

import com.fancy.lastfm.BuildConfig;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;
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

import io.reactivex.observers.TestObserver;

import static junit.framework.Assert.assertEquals;
import static junit.framework.Assert.assertNotNull;

/**
 * Created by Oleg on 21.05.2017.
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
        daoSession = new DaoMaster(db).newSession();

        localArtistStore = new LocalArtistStore(daoSession);
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
        Artist artist = new Artist("test", "123", "http");

        localArtistStore.saveArtistList(new ArrayList<Artist>() {{
            add(artist);
        }});

        //assertNotNull(artist.getId());
        //assertNotNull(daoSession.getArtistDao().load(artist.getId()));
        assertEquals(1, daoSession.getArtistDao().count());
        assertEquals(1, daoSession.loadAll(Artist.class).size());
    }

    @Test
    public void saveTopArtist() {
        Album album = new Album("test", 123, "http");

        localArtistStore.saveAlbumList(new ArrayList<Album>() {{
            add(album);
        }});

        //assertNotNull(artist.getId());
        //assertNotNull(daoSession.getArtistDao().load(artist.getId()));
        assertEquals(1, daoSession.getAlbumDao().count());
        assertEquals(1, daoSession.loadAll(Album.class).size());
    }

}
