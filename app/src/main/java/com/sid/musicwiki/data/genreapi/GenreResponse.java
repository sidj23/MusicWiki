package com.sid.musicwiki.data.genreapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class GenreResponse {
    @SerializedName("toptags")
    @Expose
    private Toptags toptags;

    public Toptags getToptags() {
        return toptags;
    }

    public void setToptags(Toptags toptags) {
        this.toptags = toptags;
    }
}
