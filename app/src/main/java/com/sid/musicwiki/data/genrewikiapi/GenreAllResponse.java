package com.sid.musicwiki.data.genrewikiapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sid.musicwiki.data.albumapi.AlbumData;
import com.sid.musicwiki.data.artistapi.ArtistData;
import com.sid.musicwiki.data.tracksapi.TrackData;

import java.util.List;

public class GenreAllResponse {

    @SerializedName("albums")
    @Expose
    AlbumResponse albumResponse;
    @SerializedName("topartists")
    @Expose
    ArtistResponse artistResponse;
    @SerializedName("tracks")
    @Expose
    TrackResponse trackResponse;
    @SerializedName("toptracks")
    @Expose
    TrackResponse topTrackResponse;
    @SerializedName("topalbums")
    @Expose
    AlbumResponse topAlbumResponse;

    public ArtistResponse getArtistResponse() {
        return artistResponse;
    }

    public void setArtistResponse(ArtistResponse artistResponse) {
        this.artistResponse = artistResponse;
    }

    public TrackResponse getTrackResponse() {
        return trackResponse;
    }

    public void setTrackResponse(TrackResponse trackResponse) {
        this.trackResponse = trackResponse;
    }

    public AlbumResponse getAlbumResponse() {
        return albumResponse;
    }

    public void setAlbumDataList(AlbumResponse albumResponse) {
        this.albumResponse = albumResponse;
    }

    public TrackResponse getTopTrackResponse() {
        return topTrackResponse;
    }

    public void setTopTrackResponse(TrackResponse topTrackResponse) {
        this.topTrackResponse = topTrackResponse;
    }

    public AlbumResponse getTopAlbumResponse() {
        return topAlbumResponse;
    }

    public void setTopAlbumResponse(AlbumResponse topAlbumResponse) {
        this.topAlbumResponse = topAlbumResponse;
    }

    public class AlbumResponse {
        @SerializedName("album")
        @Expose
        List<AlbumData> albumDataList;

        public List<AlbumData> getAlbumDataList() {
            return albumDataList;
        }

        public void setAlbumDataList(List<AlbumData> albumDataList) {
            this.albumDataList = albumDataList;
        }
    }

    public class ArtistResponse {
        @SerializedName("artist")
        @Expose
        List<ArtistData> artistDataList;

        public List<ArtistData> getArtistDataList() {
            return artistDataList;
        }

        public void setArtistDataList(List<ArtistData> artistDataList) {
            this.artistDataList = artistDataList;
        }
    }

    public class TrackResponse {
        @SerializedName("track")
        @Expose
        List<TrackData> trackData;

        public List<TrackData> getTrackData() {
            return trackData;
        }

        public void setTrackData(List<TrackData> trackData) {
            this.trackData = trackData;
        }
    }
}
