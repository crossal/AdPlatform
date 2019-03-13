package com.crossal.bid;

import com.crossal.bid.model.Bid;

import java.io.File;

public interface BidService {
    Bid getHighestBidder();
    Bid getHighestBidder(File file);
}
