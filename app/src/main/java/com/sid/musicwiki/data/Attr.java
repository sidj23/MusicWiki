package com.sid.musicwiki.data;

import android.os.Parcel;
import android.os.Parcelable;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attr implements Parcelable {
    public static final Creator<Attr> CREATOR = new Creator<Attr>() {
        @Override
        public Attr createFromParcel(Parcel in) {
            return new Attr(in);
        }

        @Override
        public Attr[] newArray(int size) {
            return new Attr[size];
        }
    };
    @SerializedName("rank")
    @Expose
    private String rank;

    protected Attr(Parcel in) {
        rank = in.readString();
    }

    public String getRank() {
        return rank;
    }

    public void setRank(String rank) {
        this.rank = rank;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeString(rank);
    }
}
