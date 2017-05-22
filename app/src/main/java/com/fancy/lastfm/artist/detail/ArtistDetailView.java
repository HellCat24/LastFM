package com.fancy.lastfm.artist.detail;

import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.mvp.view.BaseView;

import java.util.List;

/**
 * @author Oleg Mazhukin
 */

public interface ArtistDetailView extends BaseView {

    void showAlbums(List<Album> albumList);
}
