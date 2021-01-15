package com.sid.musicwiki.data.albumapi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sid.musicwiki.data.Attr;
import com.sid.musicwiki.data.Image;
import com.sid.musicwiki.data.artistapi.ArtistData;
import com.sid.musicwiki.data.genreapi.Wiki;

import java.util.List;

public class AlbumData implements Parcelable {
    public static final Creator<AlbumData> CREATOR = new Creator<AlbumData>() {
        @Override
        public AlbumData createFromParcel(Parcel in) {
            return new AlbumData(in);
        }

        @Override
        public AlbumData[] newArray(int size) {
            return new AlbumData[size];
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
    @SerializedName("wiki")
    @Expose
    private Wiki wiki;

    protected AlbumData(Parcel in) {
        name = in.readString();
        duration = in.readString();
        mbid = in.readString();
        url = in.readString();
        artistData = in.readParcelable(ArtistData.class.getClassLoader());
        image = in.createTypedArrayList(Image.CREATOR);
        attr = in.readParcelable(Attr.class.getClassLoader());
        wiki = in.readParcelable(Wiki.class.getClassLoader());
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(duration);
        dest.writeString(mbid);
        dest.writeString(url);
        dest.writeParcelable(artistData, flags);
        dest.writeTypedList(image);
        dest.writeParcelable(attr, flags);
        dest.writeParcelable(wiki, flags);
    }

    @Override
    public int describeContents() {
        return 0;
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

    public Wiki getWiki() {
        return wiki;
    }

    public void setWiki(Wiki wiki) {
        this.wiki = wiki;
    }

}
