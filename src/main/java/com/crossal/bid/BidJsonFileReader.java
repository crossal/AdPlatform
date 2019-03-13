package com.crossal.bid;

import com.crossal.bid.model.SeatBidResponse;
import com.crossal.helpers.JsonFileReader;
import org.apache.log4j.Logger;

import java.io.IOException;

public class BidJsonFileReader implements BidReader {

    private static final Logger logger = Logger.getLogger(BidJsonFileReader.class);

    private JsonFileReader<SeatBidResponse> jsonFileReader;

    public BidJsonFileReader(JsonFileReader<SeatBidResponse> jsonFileReader) {
        this.jsonFileReader = jsonFileReader;
    }

    @Override
    public SeatBidResponse getSeatBid() {
        try {
            SeatBidResponse seatBidResponse = jsonFileReader.readObj(SeatBidResponse.class);
            seatBidResponse.getSeatBid().forEach(bidResponse -> {
                bidResponse.getBids().forEach(bid -> {
                    String adMarkup = bid.getAdMarkup();
                    adMarkup = adMarkup.replaceAll("\\\\n", "\\\\r");
                    adMarkup = adMarkup.replaceAll("\\\\\\\\", "\\\\");
                    bid.setAdMarkup(adMarkup);
                });
            });

            return seatBidResponse;
        } catch (IOException e) {
            logger.error("Exception reading SeatBidResponse from json file: " + e);
            return null;
        }
    }
}
