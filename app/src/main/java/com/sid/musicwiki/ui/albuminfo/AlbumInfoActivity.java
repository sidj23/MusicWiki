package com.sid.musicwiki.ui.albuminfo;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.sid.musicwiki.R;
import com.sid.musicwiki.databinding.ActivityAlbumInfoBinding;
import com.sid.musicwiki.databinding.ActivityGenreDetailsBinding;

public class AlbumInfoActivity extends AppCompatActivity {

    private ActivityAlbumInfoBinding albumInfoBinding;
    private AlbumInfoViewModel albumInfoViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        albumInfoBinding = DataBindingUtil.setContentView(this,R.layout.activity_album_info);
        albumInfoViewModel = new AlbumInfoViewModel(this);
    }
}