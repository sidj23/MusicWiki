package com.sid.musicwiki.data.tracksapi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sid.musicwiki.data.Attr;
import com.sid.musicwiki.data.Image;
import com.sid.musicwiki.data.artistapi.ArtistData;

import java.util.List;

public class TrackData implements Parcelable {
    public static final Creator<TrackData> CREATOR = new Creator<TrackData>() {
        @Override
        public TrackData createFromParcel(Parcel in) {
            return new TrackData(in);
        }

        @Override
        public TrackData[] newArray(int size) {
            return new TrackData[size];
        }
    };
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("duration")
    @Expose
    private String duration;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("artist")
    @Expose
    private ArtistData artistData;
    @SerializedName("image")
    @Expose
    private List<Image> image = null;
    @SerializedName("@attr")
    @Expose
    private Attr attr;

    protected TrackData(Parcel in) {
        name = in.readString();
        duration = in.readString();
        mbid = in.readString();
        url = in.readString();
        artistData = in.readParcelable(ArtistData.class.getClassLoader());
        image = in.createTypedArrayList(Image.CREATOR);
        attr = in.readParcelable(Attr.class.getClassLoader());
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public String getMbid() {
        return mbid;
    }

    public void setMbid(String mbid) {
        this.mbid = mbid;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public ArtistData getArtistData() {
        return artistData;
    }

    public void setArtistData(ArtistData artistData) {
        this.artistData = artistData;
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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(name);
        parcel.writeString(duration);
        parcel.writeString(mbid);
        parcel.writeString(url);
        parcel.writeParcelable(artistData, i);
        parcel.writeTypedList(image);
        parcel.writeParcelable(attr, i);
    }
}
