package com.fancy.lastfm.artistdetail.list;

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

    private ArtistAdapter.ArtistClickListener clickListener;

    public ArtistHolder(View v, ArtistAdapter.ArtistClickListener clickListener) {
        super(v);
        this.clickListener = clickListener;
    }

    @Override
    public void bindItem(Artist artist) {
        itemView.setOnClickListener(v -> clickListener.onArtistClicked(artist, icon));
        artistName.setText(artist.getName());
        artistListeners.setText(String.format(itemView.getResources().getString(R.string.listeners_count), artist.getListenersCount()));
        loadImage(artist.getImageUrl(), icon);
    }
}