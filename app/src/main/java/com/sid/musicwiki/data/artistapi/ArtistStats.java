package com.sid.musicwiki.data.artistapi;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class ArtistStats implements Parcelable {
    public static final Creator<ArtistStats> CREATOR = new Creator<ArtistStats>() {
        @Override
        public ArtistStats createFromParcel(Parcel in) {
            return new ArtistStats(in);
        }

        @Override
        public ArtistStats[] newArray(int size) {
            return new ArtistStats[size];
        }
    };
    @SerializedName("listeners")
    @Expose
    private String listeners;
    @SerializedName("playcount")
    @Expose
    private String playCount;

    protected ArtistStats(Parcel in) {
        listeners = in.readString();
        playCount = in.readString();
    }

    public String getListeners() {
        return listeners;
    }

    public void setListeners(String listeners) {
        this.listeners = listeners;
    }

    public String getPlayCount() {
        return playCount;
    }

    public void setPlayCount(String playCount) {
        this.playCount = playCount;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(listeners);
        parcel.writeString(playCount);
    }
}
