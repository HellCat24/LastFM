package com.fancy.lastfm.artist.detail;

import android.app.Activity;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.support.v7.widget.RecyclerView;
import android.view.View;
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

    private static final String SHARED_ANIMATION_KEY_IMAGE = "artist_image";

    @BindView(R.id.artist_detail_image)
    ImageView artistImage;
    @BindView(R.id.top_album_list)
    RecyclerView recyclerView;

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
        setContentView(R.layout.activity_main);
        Artist artist = (Artist) getIntent().getSerializableExtra(BUNDLE_ARTIST);
        presenter.onCreate(artist.getName());
        loadImage(artist);
    }

    private void loadImage(Artist artist) {
        Picasso.with(artistImage.getContext())
                .load(artist.getImageUrl())
                .fit()
                .into(artistImage);
    }

    @Override
    public void showAlbums(List<Album> albumList) {
        AlbumAdapter albumAdapter = new AlbumAdapter();
        albumAdapter.setDataList(albumList);
        recyclerView.setAdapter(albumAdapter);
    }
}

