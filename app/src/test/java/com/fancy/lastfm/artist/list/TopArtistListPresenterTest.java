package com.fancy.lastfm.artist.list;

import com.fancy.lastfm.db.ArtistRepository;
import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.rx.ErrorMessageProvider;
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

import static org.mockito.Mockito.spy;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by Oleg on 21.05.2017.
 */
@RunWith(MockitoJUnitRunner.class)
public class TopArtistListPresenterTest {

    private static final String TEST_COUNTRY = "Canada";
    private static final String TEST_ERROR = "Error";

    @Mock
    private ArtistRepository repository;
    @Mock
    private TopArtistView view;
    @Spy
    private Function<Observable, Observable> observableSchedulerStrategy = new ObservableSchedulerStrategy();

    @InjectMocks
    private TopArtistListPresenter presenter;

    @Before
    public void setUp() {
        presenter = spy(new TopArtistListPresenter(repository, throwable -> TEST_ERROR));
        presenter.observableSchedulerStrategy = observable -> observable;
        when(presenter.getView()).thenReturn(view);
        when(repository.getSelectedCountry()).thenReturn(TEST_COUNTRY);
        when(repository.getTopArtist()).thenReturn(Observable.<List<Artist>>empty());
    }

    @Test
    public void whenOnCreateThenRepositoryGetArtistSuccessShowArtist() {
        List<Artist> artistList = Collections.emptyList();
        when(repository.getTopArtist()).thenReturn(Observable.just(artistList));

        presenter.onCreate();

        verify(repository).getTopArtist();
        verify(view).showArtists(artistList);
    }

    @Test
    public void whenOnCreateThenRepositoryGetArtistErrorShowError() {
        when(repository.getTopArtist()).thenReturn(Observable.error(new Exception()));

        presenter.onCreate();

        verify(repository).getTopArtist();
        verify(presenter.getView()).showError(TEST_ERROR);
    }
}
