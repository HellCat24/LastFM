package com.fancy.lastfm.artist.list;

import android.view.View;

import com.fancy.lastfm.entity.Artist;
import com.fancy.lastfm.mvp.view.BaseView;

import java.util.List;

/**
 * @author Oleg Mazhukin
 */
public interface TopArtistView extends BaseView {

    void showArtists(List<Artist> artistList);

}
