package com.sid.musicwiki.data.artistapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sid.musicwiki.data.genreapi.Genre;
import com.sid.musicwiki.data.genreapi.Wiki;

import java.util.List;

public class ArtistInfoData {

    @SerializedName("tags")
    @Expose
    private TagData tagData;
    @SerializedName("bio")
    @Expose
    private Wiki wiki;
    @SerializedName("stats")
    @Expose
    private ArtistStats artistStats;

    public TagData getTagData() {
        return tagData;
    }

    public void setTagData(TagData tagData) {
        this.tagData = tagData;
    }

    public Wiki getWiki() {
        return wiki;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }

    public ArtistStats getArtistStats() {
        return artistStats;
    }

    public void setArtistStats(ArtistStats artistStats) {
        this.artistStats = artistStats;
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
