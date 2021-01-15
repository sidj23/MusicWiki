package com.sid.musicwiki.data.albumapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sid.musicwiki.data.Attr;
import com.sid.musicwiki.data.Image;
import com.sid.musicwiki.data.genreapi.Genre;
import com.sid.musicwiki.data.genreapi.Wiki;

import java.util.List;

public class AlbumInfoData {

    @SerializedName("tags")
    @Expose
    private TagData tagData;
    @SerializedName("image")
    @Expose
    private List<Image> image = null;
    @SerializedName("@attr")
    @Expose
    private Attr attr;
    @SerializedName("wiki")
    @Expose
    private Wiki wiki;

    public TagData getTagData() {
        return tagData;
    }

    public void setTagData(TagData tagData) {
        this.tagData = tagData;
    }

    public List<Image> getImage() {
        return image;
    }

    public void setImage(List<Image> image) {
        this.image = image;
    }

    public Attr getAttr() {
        return attr;
    }

    public void setAttr(Attr attr) {
        this.attr = attr;
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }

    public class TagData {
        @SerializedName("tag")
        @Expose
        private List<Genre> genreList;

        public List<Genre> getGenreList() {
            return genreList;
        }

        public void setGenreList(List<Genre> genreList) {
            this.genreList = genreList;
        }
    }
}
