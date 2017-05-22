package com.fancy.lastfm.artist.detail;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.fancy.lastfm.R;
import com.fancy.lastfm.adapter.BaseListAdapter;
import com.fancy.lastfm.adapter.BaseViewHolder;
import com.fancy.lastfm.artist.list.ArtistAdapter;
import com.fancy.lastfm.entity.Album;

import butterknife.BindView;

/**
 * @author Oleg Mazhukin
 */

public class AlbumAdapter extends BaseListAdapter<Album, AlbumAdapter.AlbumHolder> {

    @Override
    public ArtistAdapter.ArtistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new ArtistAdapter.ArtistHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_album, parent, false));
    }

    public static class AlbumHolder extends BaseViewHolder<Album> {

        @BindView(R.id.album_icon)
        ImageView icon;
        @BindView(R.id.album_name)
        TextView albumName;
        @BindView(R.id.album_listeners)
        TextView albumListeners;

        public AlbumHolder(View v) {
            super(v);
        }

        @Override
        public void bindItem(Album album) {
            albumName.setText(album.getName());
            albumListeners.setText(String.format("Play Count : %d", album.getPlayCount()));
            loadImage(album.getUrl(), icon);
        }
    }
}
