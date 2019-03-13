package com.crossal.bid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class BidResponse {
    @JsonProperty("bid")
    private List<Bid> bids;
    private Long seat;

    public List<Bid> getBids() {
        return bids;
    }

    public void setBids(List<Bid> bids) {
        this.bids = bids;
    }

    public Long getSeat() {
        return seat;
    }

    public void setSeat(Long seat) {
        this.seat = seat;
    }
}
