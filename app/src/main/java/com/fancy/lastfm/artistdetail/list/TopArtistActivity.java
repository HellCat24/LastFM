package com.fancy.lastfm.artistdetail.list;

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
import com.fancy.lastfm.artistlist.ArtistDetailActivity;
import com.fancy.lastfm.entity.Artist;

import java.util.Arrays;
import java.util.List;

import butterknife.BindView;

public class TopArtistActivity extends ProgressActivity<TopArtistListPresenter> implements TopArtistView, ArtistAdapter.ArtistClickListener, AdapterView.OnItemSelectedListener {

    @BindView(R.id.top_artist_list)
    RecyclerView recyclerView;

    private ArrayAdapter<CharSequence> countryAdapter;
    private ArtistAdapter artistAdapter;

    private Spinner countrySpinner;

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

        String[] countryArray = getResources().getStringArray(R.array.country_array);

        countrySpinner = (Spinner) MenuItemCompat.getActionView(item);
        countryAdapter = new ArrayAdapter<>(this, R.layout.item_spinner_country, countryArray);

        int position = Arrays.asList(countryArray).indexOf(presenter.repository.getSelectedCountry());
        countryAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        countrySpinner.setAdapter(countryAdapter);

        countrySpinner.setSelection(position);
        countrySpinner.setOnItemSelectedListener(this);
        return true;
    }

    @Override
    public void showArtists(List<Artist> artistList) {
        artistAdapter.setDataList(artistList);
    }

    @Override
    public void onArtistClicked(Artist artist, View icon) {
        ArtistDetailActivity.startWithAnimation(this, artist, icon);
    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
        CharSequence countryName = countryAdapter.getItem(position);
        presenter.onCountrySelected((String) countryName);
    }

    @Override
    public void onNothingSelected(AdapterView<?> parent) {
        // do Nothing
    }

    private void initUI() {
        setTitle(R.string.popular_artist);

        artistAdapter = new ArtistAdapter(this);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(artistAdapter);
    }
}
