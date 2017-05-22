package com.fancy.lastfm.artist.list;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fancy.lastfm.R;
import com.fancy.lastfm.activity.ProgressActivity;
import com.fancy.lastfm.artist.detail.ArtistDetailActivity;
import com.fancy.lastfm.entity.Artist;

import java.util.List;

import butterknife.BindView;

public class TopArtistActivity extends ProgressActivity<TopArtistListPresenter> implements TopArtistView, ArtistAdapter.ArtistClickListener {

    @BindView(R.id.top_artist_list)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.onCreate();
    }

    @Override
    public void showArtists(List<Artist> artistList) {
        ArtistAdapter artistAdapter = new ArtistAdapter(this);
        artistAdapter.setDataList(artistList);
        recyclerView.setAdapter(artistAdapter);
    }

    @Override
    public void onArtistClicked(Artist artist, View icon) {
        ArtistDetailActivity.startWithAnimation(this, artist, icon);
    }
}
