package com.sid.musicwiki.data.artistapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistInfoResponse {

    @SerializedName("artist")
    @Expose
    private ArtistInfoData artistInfoData;

    public ArtistInfoData getArtistInfoData() {
        return artistInfoData;
    }

    public void setArtistInfoData(ArtistInfoData artistInfoData) {
        this.artistInfoData = artistInfoData;
    }
}
