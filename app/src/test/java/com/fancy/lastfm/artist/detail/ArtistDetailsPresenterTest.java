package com.fancy.lastfm.artist.detail;

import com.fancy.lastfm.artist.list.TopArtistListPresenter;
import com.fancy.lastfm.artist.list.TopArtistView;
import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Oleg on 21.05.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class ArtistDetailsPresenterTest {

    private static final String ARTIST_NAME = "Pink Floyd";
    private static final String TEST_ERROR = "Error";

    @Mock
    private ArtistRepository repository;
    @Mock
    private ArtistDetailView view;

    ArtistDetailsPresenter presenter;

    @Before
    public void setUp(){
        presenter = new ArtistDetailsPresenter(repository);
        doReturn(view).when(presenter.getView());
    }

    @Test
    public void whenOnCreateThenRepositoryGetTopArtist(){
        presenter.onCreate(ARTIST_NAME);

        verify(repository).getTopAlbum(ARTIST_NAME);
    }

    @Test
    public void whenOnCreateThenRepositoryGetArtistSuccessShowArtist(){
        presenter.onCreate(ARTIST_NAME);

        List<Album> albumList = Collections.emptyList();
        when(repository.getTopAlbum(ARTIST_NAME)).thenReturn(Observable.just(albumList));

        verify(presenter.getView()).showAlbums(albumList);
    }

    @Test
    public void whenOnCreateThenRepositoryGetArtistErrorShowError(){
        presenter.onCreate(ARTIST_NAME);
        when(repository.getTopAlbum(ARTIST_NAME)).thenThrow(new Exception());

        verify(presenter.getView()).showError(TEST_ERROR);
    }

}
