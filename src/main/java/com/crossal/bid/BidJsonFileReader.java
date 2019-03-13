package com.crossal.bid;

import com.crossal.bid.model.SeatBidResponse;
import com.crossal.helpers.FileReaderFactory;
import com.crossal.helpers.JsonFileReader;
import org.apache.log4j.Logger;

import java.io.File;
import java.io.IOException;

public class BidJsonFileReader implements BidReader {

    private static final Logger logger = Logger.getLogger(BidJsonFileReader.class);

//    private FileStreamFactory fileStreamFactory;
//    private File file;
    private JsonFileReader<SeatBidResponse> jsonFileReader;
//    private BufferedReader reader;

    public BidJsonFileReader(JsonFileReader<SeatBidResponse> jsonFileReader) {
        this.jsonFileReader = jsonFileReader;
    }

//    private void openFile() {
//
//    }

    @Override
    public SeatBidResponse getSeatBid() {
        try {
            return jsonFileReader.readObj(SeatBidResponse.class);
        } catch (IOException e) {
            logger.error("Exception reading SeatBidResponse from json file: " + e);
            return null;
        }
    }
}
