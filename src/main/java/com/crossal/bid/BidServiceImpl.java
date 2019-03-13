package com.crossal.bid;

import com.crossal.bid.model.Bid;
import com.crossal.bid.model.BidResponse;
import com.crossal.bid.model.SeatBidResponse;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;

@Service
public class BidServiceImpl implements BidService {

    private static final Logger logger = Logger.getLogger(BidServiceImpl.class);

//    @Autowired
//    private BidReaderFactory bidReaderFactory;
    private BidReader bidReader;

    @Autowired
    public BidServiceImpl(BidReaderFactory bidReaderFactory) {
        bidReader = bidReaderFactory.getBidReader();
    }

    @Override
    public Bid getHighestBidder() {
        SeatBidResponse seatBidResponse = bidReader.getSeatBid();

        if (seatBidResponse == null || seatBidResponse.getSeatBid() == null) {
            return null;
        }

        List<Bid> bids = seatBidResponse.getSeatBid().stream().flatMap(b -> b.getBids().stream()).collect(Collectors.toList());
        Bid highestBid = bids.stream().max(Comparator.comparing(b -> b.getPrice())).orElse(null);
//        List<Bid> bids = seatBidResponse.getSeatBid().stream().map(b -> b.getBids()).collect(Collectors.toList());
//        BidResponse highestBidResponse = seatBidResponse.getSeatBid().stream().max(Comparator.comparing(b -> b.getBid() == null ? null : b.getBid().getPrice())).orElse(null);
//        Bid highestBid = highestBidResponse == null ? null : highestBidResponse.getBid();

        return highestBid;
    }

//    @Override
//    public SeatBidResponse getSeatBid() {
//        SeatBidResponse seatBidResponse = bidReader.getSeatBid();
//
//        return seatBidResponse;
//    }
}
