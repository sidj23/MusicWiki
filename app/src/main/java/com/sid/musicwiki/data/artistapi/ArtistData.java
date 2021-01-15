package com.sid.musicwiki.data.artistapi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.sid.musicwiki.data.Image;
import com.sid.musicwiki.data.Attr;

import java.util.List;

public class ArtistData implements Parcelable {
    @SerializedName("name")
    @Expose
    private String name;
    @SerializedName("mbid")
    @Expose
    private String mbid;
    @SerializedName("url")
    @Expose
    private String url;
    @SerializedName("image")
    @Expose
    private List<Image> image = null;
    @SerializedName("@attr")
    @Expose
    private Attr attr;

    protected ArtistData(Parcel in) {
        name = in.readString();
        mbid = in.readString();
        url = in.readString();
        image = in.createTypedArrayList(Image.CREATOR);
        attr = in.readParcelable(Attr.class.getClassLoader());
    }

    public static final Creator<ArtistData> CREATOR = new Creator<ArtistData>() {
        @Override
        public ArtistData createFromParcel(Parcel in) {
            return new ArtistData(in);
        }

        @Override
        public ArtistData[] newArray(int size) {
            return new ArtistData[size];
        }
    };

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
        parcel.writeString(mbid);
        parcel.writeString(url);
        parcel.writeTypedList(image);
        parcel.writeParcelable(attr, i);
    }
}
