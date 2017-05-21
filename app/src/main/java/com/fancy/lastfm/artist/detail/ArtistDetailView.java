package com.fancy.lastfm.artist.detail;

import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.mvp.view.BaseView;

import java.util.List;

/**
 * Created by Oleg on 21.05.2017.
 */

public interface ArtistDetailView extends BaseView {

    void showAlbums(List<Album> albumList);
}
