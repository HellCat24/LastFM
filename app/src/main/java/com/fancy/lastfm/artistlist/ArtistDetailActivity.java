package com.fancy.lastfm.artistlist;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;

import com.fancy.lastfm.R;
import com.fancy.lastfm.activity.ProgressActivity;
import com.fancy.lastfm.entity.Album;
import com.fancy.lastfm.entity.Artist;
import com.squareup.picasso.Picasso;

import java.util.List;

import butterknife.BindView;

/**
 * @author Oleg Mazhukin
 */
public class ArtistDetailActivity extends ProgressActivity<ArtistDetailsPresenter> implements ArtistDetailView {

    public static final String BUNDLE_ARTIST = "artist";

    private static final String SHARED_ANIMATION_KEY_IMAGE = "artist_detail_image";

    @BindView(R.id.toolbar)
    Toolbar toolbar;
    @BindView(R.id.artist_detail_image)
    ImageView artistImage;
    @BindView(R.id.top_album_list)
    RecyclerView recyclerView;

    private AlbumAdapter albumAdapter;

    public static void startWithAnimation(Activity activity, Artist offer, View image) {
        Intent intent = getBasicIntent(activity, offer);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN) {
            Pair<View, String> p1 = Pair.create(image, SHARED_ANIMATION_KEY_IMAGE);
            ActivityOptionsCompat options = ActivityOptionsCompat.makeSceneTransitionAnimation(activity, p1);
            activity.startActivity(intent, options.toBundle());
            activity.overridePendingTransition(0, 0);
        } else {
            activity.startActivity(intent);
        }
    }

    @NonNull
    private static Intent getBasicIntent(Activity activity, Artist artist) {
        Intent intent = new Intent(activity, ArtistDetailActivity.class);
        intent.putExtra(BUNDLE_ARTIST, artist);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        initUI();
        Artist artist = (Artist) getIntent().getSerializableExtra(BUNDLE_ARTIST);
        presenter.onCreate(artist.getName());
        loadImage(artist);
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_artist_detail;
    }

    @Override
    public ArtistDetailsPresenter createPresenter() {
        return new ArtistDetailsPresenter();
    }

    @Override
    public void showAlbums(List<Album> albumList) {
        AlbumAdapter albumAdapter = new AlbumAdapter();
        albumAdapter.setDataList(albumList);
        recyclerView.setAdapter(albumAdapter);
    }

    private void loadImage(Artist artist) {
        setTitle(artist.getName());
        Picasso.with(artistImage.getContext())
                .load(artist.getImageUrl())
                .into(artistImage);
    }

    private void initUI() {
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(view -> finish());
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


        albumAdapter = new AlbumAdapter();
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(albumAdapter);
    }
}

