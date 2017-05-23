package com.fancy.lastfm.artist.list;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.fancy.lastfm.R;
import com.fancy.lastfm.adapter.BaseListAdapter;
import com.fancy.lastfm.entity.Artist;

/**
 * @author Oleg Mazhukin
 */

public class ArtistAdapter extends BaseListAdapter<Artist, ArtistHolder> {

    public interface ArtistClickListener {

        void onArtistClicked(Artist artist, View icon);
    }

    private ArtistClickListener onArtistClickListener;

    public ArtistAdapter(ArtistClickListener artistClickListener) {
        this.onArtistClickListener = artistClickListener;
    }

    @Override
    public ArtistHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_artist, parent, false);
        return new ArtistHolder(view, onArtistClickListener);
    }
}
