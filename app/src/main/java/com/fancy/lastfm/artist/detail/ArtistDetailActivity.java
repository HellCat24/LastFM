package com.fancy.lastfm.artist.detail;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.fancy.lastfm.R;
import com.fancy.lastfm.activity.BaseActivity;
import com.fancy.lastfm.activity.ProgressActivity;
import com.fancy.lastfm.artist.list.TopArtistListPresenter;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;

import java.util.List;

import butterknife.BindView;

/**
 * @author Oleg Mazhukin
 */
public class ArtistDetailActivity extends ProgressActivity<ArtistDetailsPresenter> implements ArtistDetailView {

    public static final String BUNDLE_ARTIST = "artist";

    @BindView(R.id.artist_detail_image)
    ImageView artistImage;
    @BindView(R.id.top_album_list)
    RecyclerView recyclerView;

    public static void start(Context context, Artist artist) {
        Intent intent = new Intent(context, ArtistDetailActivity.class);
        intent.putExtra(BUNDLE_ARTIST, artist);
        context.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        presenter.onCreate(getIntent().getStringExtra(BUNDLE_ARTIST));
    }

    @Override
    public void showAlbums(List<Album> albumList) {
        AlbumAdapter albumAdapter = new AlbumAdapter();
        albumAdapter.setDataList(albumList);
        recyclerView.setAdapter(albumAdapter);
    }
}

