package com.sid.musicwiki.ui.genredetails;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sid.musicwiki.R;
import com.sid.musicwiki.data.genreapi.Genre;
import com.sid.musicwiki.databinding.ActivityGenreDetailsBinding;

public class GenreDetailsActivity extends AppCompatActivity implements GenreViewPagerAdapter.GenreViewPagerAdapterListener {

    private static final String ARG_PARAM_1 = "ARG_PARAM_1";

    private ActivityGenreDetailsBinding genreDetailsBinding;
    private GenreDetailsViewModel genreDetailsViewModel;
    private Genre genreData;
    private GenreViewPagerAdapter viewPagerAdapter;

    public static Intent newIntent(Context context, Genre genre) {
        Intent intent = new Intent(context, GenreDetailsActivity.class);
        Bundle args = new Bundle();
        args.putParcelable("genre_data", genre);
        intent.putExtra(ARG_PARAM_1, args);
        return intent;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        genreDetailsBinding = DataBindingUtil.setContentView(this, R.layout.activity_genre_details);
        genreDetailsViewModel = new GenreDetailsViewModel(this);
        genreDetailsBinding.setViewModel(genreDetailsViewModel);

        genreData = getIntent().getBundleExtra(ARG_PARAM_1).getParcelable("genre_data");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        if (genreData != null) {
            actionBar.setTitle(genreData.getName());
            setUpDataSource();
            setUpView();
        } else {
            finish();
        }
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }

    public boolean onCreateOptionsMenu(Menu menu) {
        return true;
    }


    private void setUpDataSource() {
        genreDetailsViewModel.setUpDataSource(genreData);
    }


    private void setUpView() {
        viewPagerAdapter = new GenreViewPagerAdapter(getSupportFragmentManager(), 0, this);
        genreDetailsBinding.agdDetailsVp.setAdapter(viewPagerAdapter);
        genreDetailsBinding.agdDetailsVp.setOffscreenPageLimit(3);
        genreDetailsBinding.agdTabsTl.setupWithViewPager(genreDetailsBinding.agdDetailsVp);
    }

    @Override
    public void setTabTitle() {
        if (genreDetailsBinding != null) {
            genreDetailsBinding.agdTabsTl.getTabAt(0).setText("Albums");
            genreDetailsBinding.agdTabsTl.getTabAt(1).setText("Artists");
            genreDetailsBinding.agdTabsTl.getTabAt(2).setText("Tracks");
        }
    }
}