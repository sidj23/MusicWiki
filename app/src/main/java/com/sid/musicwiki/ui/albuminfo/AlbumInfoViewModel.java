package com.sid.musicwiki.ui.albuminfo;

import android.content.Context;

import com.sid.musicwiki.util.api.ApiInterface;
import com.sid.musicwiki.util.api.ApiUtil;

public class AlbumInfoViewModel {

    private Context context;
    private ApiInterface apiInterface;

    public AlbumInfoViewModel(Context context) {
        this.context = context;
        apiInterface = ApiUtil.provideRetrofit().create(ApiInterface.class);
    }
}
