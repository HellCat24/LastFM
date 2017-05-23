package com.fancy.lastfm.artist.list;

import android.os.Bundle;
import android.support.v4.view.MenuItemCompat;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.fancy.lastfm.R;
import com.fancy.lastfm.activity.ProgressActivity;
import com.fancy.lastfm.artist.detail.ArtistDetailActivity;
import com.fancy.lastfm.entity.Artist;

import java.util.List;

import butterknife.BindView;

public class TopArtistActivity extends ProgressActivity<TopArtistListPresenter> implements TopArtistView, ArtistAdapter.ArtistClickListener, AdapterView.OnItemSelectedListener {

    @BindView(R.id.top_artist_list)
    RecyclerView recyclerView;

    private ArrayAdapter<CharSequence> countryAdapter;
    private ArtistAdapter artistAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        presenter.onCreate();
        initUI();
    }

    @Override
    public int getLayoutId() {
        return R.layout.activity_main;
    }

    @Override
    public TopArtistListPresenter createPresenter() {
        return new TopArtistListPresenter();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.country_menu, menu);
        MenuItem item = menu.findItem(R.id.spinner);
        Spinner spinner = (Spinner) MenuItemCompat.getActionView(item);
        spinner.setOnItemSelectedListener(this);
        countryAdapter = ArrayAdapter.createFromResource(this, R.array.country_array, android.R.layout.simple_spinner_item);
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(countryAdapter);
        return true;
    }

    @Override
    public void showArtists(List<Artist> artistList) {
        artistAdapter.setDataList(artistList);
    }

    @Override
    public void setTitle(String title) {
        super.setTitle(title);
    }

    @Override
    public void onArtistClicked(Artist artist, View icon) {
        ArtistDetailActivity.startWithAnimation(this, artist, icon);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        presenter.onCountrySelected((String) countryAdapter.getItem(position));
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // do Nothing
    }

    private void initUI() {
        artistAdapter = new ArtistAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(artistAdapter);
    }
}
