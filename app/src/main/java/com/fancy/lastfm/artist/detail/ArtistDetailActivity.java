package com.fancy.lastfm.artist.detail;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fancy.lastfm.R;
import com.fancy.lastfm.activity.BaseActivity;
import com.fancy.lastfm.artist.list.TopArtistListPresenter;

import butterknife.BindView;

/**
 * Created by Oleg on 21.05.2017.
 */

public class ArtistDetailActivity extends BaseActivity<TopArtistListPresenter> {

    @BindView(R.id.top_artist_list)
    RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.onCreate();

        recyclerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }
}

