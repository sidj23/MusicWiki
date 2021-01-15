package com.sid.musicwiki.data.albumapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class AlbumInfoResponse {
    @SerializedName("album")
    @Expose
    private AlbumInfoData albumData;

    public AlbumInfoData getAlbumData() {
        return albumData;
    }

    public void setAlbumData(AlbumInfoData albumData) {
        this.albumData = albumData;
    }
}
