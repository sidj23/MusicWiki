package com.sid.musicwiki.data.genreapi;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Attr {
    @SerializedName("offset")
    @Expose
    private Integer offset;
    @SerializedName("num_res")
    @Expose
    private Integer numRes;
    @SerializedName("total")
    @Expose
    private Integer total;

    public Integer getOffset() {
        return offset;
    }

    public void setOffset(Integer offset) {
        this.offset = offset;
    }

    public Integer getNumRes() {
        return numRes;
    }

    public void setNumRes(Integer numRes) {
        this.numRes = numRes;
    }

    public Integer getTotal() {
        return total;
    }

    public void setTotal(Integer total) {
        this.total = total;
    }
}
