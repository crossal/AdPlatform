package com.crossal.bid;

import com.crossal.bid.model.SeatBidResponse;
import com.crossal.helpers.FileReaderFactory;
import com.crossal.helpers.JsonFileReader;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class BidReaderFactory {

    @Value("${adplaform.bid.file.default}")
    private String defaultBidFilePath;

    public BidReader getBidReader() {
        File file = new File(defaultBidFilePath);
        FileReaderFactory fileReaderFactory = new FileReaderFactory();
        JsonFileReader<SeatBidResponse> jsonFileReader = fileReaderFactory.getJsonFileReader(file);

        return new BidJsonFileReader(jsonFileReader);
    }

    public BidReader getBidReader(File file) {
        FileReaderFactory fileReaderFactory = new FileReaderFactory();
        JsonFileReader<SeatBidResponse> jsonFileReader = fileReaderFactory.getJsonFileReader(file);

        return new BidJsonFileReader(jsonFileReader);
    }
}
