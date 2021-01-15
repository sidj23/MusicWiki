package com.sid.musicwiki.data.genrewikiapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sid.musicwiki.data.genreapi.Genre;

public class GenreWikiResponse {
    @SerializedName("tag")
    @Expose
    Genre genre;
    public Genre getGenre() {
        return genre;
    }

    public void setGenre(Genre genre) {
        this.genre = genre;
    }
}
