package com.fancy.lastfm.artist.detail;

import com.fancy.lastfm.artist.list.TopArtistListPresenter;
import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.entity.Album;

import org.junit.Before;
import org.junit.Test;
import org.junit.rules.TestRule;
import org.junit.runner.Description;
import org.junit.runner.RunWith;
import org.junit.runners.model.Statement;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * @author Oleg Mazhukin
 */
@RunWith(MockitoJUnitRunner.class)
public class ArtistDetailsPresenterTest {

    private static final String ARTIST_NAME = "Pink Floyd";
    private static final String TEST_ERROR = "Error";

    @Mock
    private ArtistRepository repository;
    @Mock
    private ArtistDetailView view;

    @InjectMocks
    ArtistDetailsPresenter presenter;

    @Before
    public void setUp() {
        presenter = spy(new ArtistDetailsPresenter(repository, throwable -> TEST_ERROR));
        presenter.observableSchedulerStrategy = observable -> observable;
        when(presenter.getView()).thenReturn(view);
    }

    @Test
    public void whenOnCreateThenRepositoryGetArtistSuccessShowArtist() {
        List<Album> albumList = Collections.emptyList();
        when(repository.getTopAlbum(ARTIST_NAME)).thenReturn(Observable.just(albumList));

        presenter.onCreate(ARTIST_NAME);

        verify(presenter.getView()).showAlbums(albumList);
    }

    @Test
    public void whenOnCreateThenRepositoryGetArtistErrorShowError() {
        when(repository.getTopAlbum(ARTIST_NAME)).thenReturn(Observable.error(new Exception()));

        presenter.onCreate(ARTIST_NAME);

        verify(presenter.getView()).showError(TEST_ERROR);
    }
}
