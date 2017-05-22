package com.fancy.lastfm.artist.list;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.fancy.lastfm.R;
import com.fancy.lastfm.activity.ProgressActivity;
import com.fancy.lastfm.entity.Artist;

import java.util.List;

import butterknife.BindView;

public class TopArtistActivity extends ProgressActivity<TopArtistListPresenter> implements TopArtistView {

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
        ArtistAdapter artistAdapter = new ArtistAdapter();
        artistAdapter.setDataList(artistList);
        recyclerView.setAdapter(artistAdapter);
    }

    @Override
    public void showArtistDetails(Artist artist) {

    }
}
