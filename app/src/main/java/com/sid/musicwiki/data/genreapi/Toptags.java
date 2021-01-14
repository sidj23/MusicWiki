package com.sid.musicwiki.data.genreapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class Toptags {

    @SerializedName("@attr")
    @Expose
    private Attr attr;
    @SerializedName("tag")
    @Expose
    private List<Genre> genreList = null;

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

    public List<Genre> getGenreList() {
        return genreList;
    }

    public void setGenreList(List<Genre> tag) {
        this.genreList = tag;
    }

}
