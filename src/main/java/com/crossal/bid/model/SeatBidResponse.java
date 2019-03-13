package com.crossal.bid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class SeatBidResponse {
    private String id;
    @JsonProperty("seatbid")
    private List<BidResponse> seatBid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public List<BidResponse> getSeatBid() {
        return seatBid;
    }

    public void setSeatBid(List<BidResponse> seatBid) {
        this.seatBid = seatBid;
    }
}
