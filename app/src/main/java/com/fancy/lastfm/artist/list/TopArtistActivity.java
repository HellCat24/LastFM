package com.fancy.lastfm.artist.list;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.fancy.lastfm.R;
import com.fancy.lastfm.activity.BaseActivity;

import butterknife.BindView;

public class TopArtistActivity extends BaseActivity<TopArtistListPresenter> {

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
