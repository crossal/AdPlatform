package com.crossal.bid;

import com.crossal.bid.model.Bid;
import com.crossal.bid.model.SeatBidResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class BidServiceImpl implements BidService {

    private static final Logger logger = Logger.getLogger(BidServiceImpl.class);

    @Autowired
    private BidReaderFactory bidReaderFactory;

    @Override
    public Bid getHighestBidder(File file) {
        BidReader bidReader = file == null ? bidReaderFactory.getBidReader() : bidReaderFactory.getBidReader(file);

        SeatBidResponse seatBidResponse = bidReader.getSeatBid();

        if (seatBidResponse == null || seatBidResponse.getSeatBid() == null) {
            return null;
        }

        List<Bid> bids = seatBidResponse.getSeatBid().stream().flatMap(b -> b.getBids().stream()).collect(Collectors.toList());
        Bid highestBid = bids.stream().max(Comparator.comparing(b -> b.getPrice())).orElse(null);

        return highestBid;
    }

    @Override
    public Bid getHighestBidder() {
        return getHighestBidder(null);
    }
}
