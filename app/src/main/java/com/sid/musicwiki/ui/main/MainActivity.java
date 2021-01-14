package com.sid.musicwiki.ui.main;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sid.musicwiki.R;
import com.sid.musicwiki.data.genreapi.Genre;
import com.sid.musicwiki.databinding.ActivityMainBinding;

public class MainActivity extends AppCompatActivity implements GenreListAdapter.GenreListAdapterListener {

    ActivityMainBinding mainBinding;
    MainViewModel mainViewModel;
    GenreListAdapter genreListAdapter;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mainBinding = DataBindingUtil.setContentView(this, R.layout.activity_main);
        mainViewModel = new MainViewModel(this);
        mainBinding.setViewModel(mainViewModel);
        mainBinding.executePendingBindings();

        setUpDataSource();
        setUpView();

    }

    private void setUpView() {
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        gridLayoutManager.setOrientation(RecyclerView.VERTICAL);
        genreListAdapter = new GenreListAdapter(this);

        mainBinding.amGenreRv.setLayoutManager(gridLayoutManager);
        mainBinding.amGenreRv.setAdapter(genreListAdapter);
    }

    private void setUpDataSource() {
        mainViewModel.setUpDataSource();
    }

    @Override
    public void onGenreItemClick(Genre genreData) {

    }
}