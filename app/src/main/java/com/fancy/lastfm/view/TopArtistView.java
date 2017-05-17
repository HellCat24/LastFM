package com.fancy.lastfm.view;

import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.view.base.BaseView;

import java.util.List;

/**
 * @author Oleg Mazhukin
 */
public interface TopArtistView extends BaseView {

    void showArtists(List<Artist> artistList);

    void showArtistDetails(Artist artist);
}
