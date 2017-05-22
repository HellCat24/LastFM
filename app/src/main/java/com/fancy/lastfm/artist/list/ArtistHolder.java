package com.fancy.lastfm.artist.list;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.fancy.lastfm.R;
import com.fancy.lastfm.adapter.BaseViewHolder;
import com.fancy.lastfm.entity.Artist;

import butterknife.BindView;

public class ArtistHolder extends BaseViewHolder<Artist> {

    @BindView(R.id.artist_icon)
    ImageView icon;
    @BindView(R.id.artist_name)
    TextView artistName;
    @BindView(R.id.artist_listeners)
    TextView artistListeners;

    public ArtistHolder(View v) {
        super(v);
    }

    @Override
    public void bindItem(Artist artist) {
        artistName.setText(artist.getName());
        artistListeners.setText(String.format("Listeners : %s", artist.getListenersCount()));
        loadImage(artist.getImageUrl(), icon);
    }
}