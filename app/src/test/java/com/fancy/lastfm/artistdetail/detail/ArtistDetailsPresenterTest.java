package com.fancy.lastfm.artistdetail.detail;

import com.fancy.lastfm.artistlist.ArtistDetailView;
import com.fancy.lastfm.artistlist.ArtistDetailsPresenter;
import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.rx.ErrorHandler;
import com.fancy.lastfm.rx.ObservableSchedulerStrategy;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Collections;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.functions.Function;

import static org.mockito.Matchers.any;
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
    @Mock
    private ErrorHandler errorHandler;
    @Spy
    private Function<Observable, Observable> observableSchedulerStrategy = new ObservableSchedulerStrategy();

    @Spy
    @InjectMocks
    ArtistDetailsPresenter presenter;

    @Before
    public void setUp() {
        when(presenter.getView()).thenReturn(view);
        when(errorHandler.getMessage(any())).thenReturn(TEST_ERROR);
    }

    @Test
    public void whenOnCreateThenRepositoryGetArtistSuccessShowArtist() {
        List<Album> albumList = Collections.emptyList();
        when(repository.getTopAlbums(ARTIST_NAME)).thenReturn(Observable.just(albumList));

        presenter.onCreate(ARTIST_NAME);

        verify(repository).getTopAlbums(ARTIST_NAME);
        verify(presenter.getView()).showAlbums(albumList);
    }

    @Test
    public void whenOnCreateThenRepositoryGetArtistErrorShowError() {
        when(repository.getTopAlbums(ARTIST_NAME)).thenReturn(Observable.error(new Exception()));

        presenter.onCreate(ARTIST_NAME);

        verify(repository).getTopAlbums(ARTIST_NAME);
        verify(presenter.getView()).showError(TEST_ERROR);
    }
}
