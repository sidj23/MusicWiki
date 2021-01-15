package com.sid.musicwiki.ui.albuminfo;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.view.Menu;
import android.view.MenuItem;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sid.musicwiki.R;
import com.sid.musicwiki.data.albumapi.AlbumData;
import com.sid.musicwiki.data.artistapi.ArtistData;
import com.sid.musicwiki.data.genreapi.Genre;
import com.sid.musicwiki.data.tracksapi.TrackData;
import com.sid.musicwiki.databinding.ActivityAlbumInfoBinding;
import com.sid.musicwiki.ui.genredetails.GenreDetailsActivity;
import com.sid.musicwiki.ui.genredetails.genrefrag.GenreDataItemAdapter;
import com.sid.musicwiki.ui.main.GenreListAdapter;

public class AlbumInfoActivity extends AppCompatActivity implements GenreListAdapter.GenreListAdapterListener, GenreDataItemAdapter.GenreDataItemAdapterListener {

    private static final String ARG_PARAM_1 = "ARG_PARAM_1";

    private ActivityAlbumInfoBinding albumInfoBinding;
    private AlbumInfoViewModel albumInfoViewModel;
    private GenreListAdapter genreListAdapter;
    private ArtistData artistData;
    private AlbumData albumData;

    public static Intent newIntent(Context context, Object object) {
        Intent intent = new Intent(context, AlbumInfoActivity.class);
        Bundle args = new Bundle();
        if (object instanceof AlbumData)
            args.putParcelable("album_data", (AlbumData) object);
        else if (object instanceof ArtistData)
            args.putParcelable("artist_data", (ArtistData) object);
        intent.putExtra(ARG_PARAM_1, args);
        return intent;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        albumInfoBinding = DataBindingUtil.setContentView(this, R.layout.activity_album_info);
        albumInfoViewModel = new AlbumInfoViewModel(this);
        albumInfoBinding.setViewModel(albumInfoViewModel);

        artistData = getIntent().getExtras().getBundle(ARG_PARAM_1).getParcelable("artist_data");
        albumData = getIntent().getExtras().getBundle(ARG_PARAM_1).getParcelable("album_data");

        ActionBar actionBar = getSupportActionBar();
        actionBar.setDisplayHomeAsUpEnabled(true);
        actionBar.setHomeButtonEnabled(true);

        if (albumData != null) {
            actionBar.setTitle("Album Info");
        } else if (artistData != null) {
            actionBar.setTitle("Artist Info");
        }

        setUpDataSource(albumData, artistData);
        setUpView();
    }

    private void setUpView() {
        LinearLayoutManager gridLayoutManager = new LinearLayoutManager(this);
        gridLayoutManager.setOrientation(RecyclerView.HORIZONTAL);

        DisplayMetrics displayMetrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(displayMetrics);
        int width = displayMetrics.widthPixels;


        GenreListAdapter genreAdapter = new GenreListAdapter(this, true);
        albumInfoBinding.aaiGenreRv.setLayoutManager(gridLayoutManager);
        albumInfoBinding.aaiGenreRv.setAdapter(genreAdapter);

        LinearLayoutManager gridLayoutManager1 = new LinearLayoutManager(this);
        gridLayoutManager1.setOrientation(RecyclerView.HORIZONTAL);
        GenreDataItemAdapter albumAdapter = new GenreDataItemAdapter(this, true, width);
        albumInfoBinding.aaiAlbumRv.setLayoutManager(gridLayoutManager1);
        albumInfoBinding.aaiAlbumRv.setAdapter(albumAdapter);

        LinearLayoutManager gridLayoutManager2 = new LinearLayoutManager(this);
        gridLayoutManager2.setOrientation(RecyclerView.HORIZONTAL);
        GenreDataItemAdapter trackAdapter = new GenreDataItemAdapter(this, true, width);
        albumInfoBinding.aaiTrackRv.setLayoutManager(gridLayoutManager2);
        albumInfoBinding.aaiTrackRv.setAdapter(trackAdapter);
    }

    private void setUpDataSource(AlbumData albumData, ArtistData artistData) {
        albumInfoViewModel.setUpDataSource(albumData, artistData);
    }

    @Override
    public void onGenreItemClick(Genre genreData) {
        startActivity(GenreDetailsActivity.newIntent(this, genreData));
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

    @Override
    public void onGridItemClick(Object gridObject) {
        if (!(gridObject instanceof TrackData))
            startActivity(AlbumInfoActivity.newIntent(this, gridObject));
    }
}