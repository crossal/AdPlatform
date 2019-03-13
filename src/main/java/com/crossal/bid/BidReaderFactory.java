package com.crossal.bid;

import com.crossal.bid.model.SeatBidResponse;
import com.crossal.helpers.FileObjReader;
import com.crossal.helpers.FileObjReaderFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.File;

@Component
public class BidReaderFactory {

    @Value("${adplaform.bid.file.default}")
    private String defaultBidFilePath;

    public BidReader getBidReader() {
        File file = new File(defaultBidFilePath);
        FileObjReaderFactory fileObjReaderFactory = new FileObjReaderFactory();
        FileObjReader<SeatBidResponse> fileObjReader = fileObjReaderFactory.getJsonFileReader(file);

        return new BidJsonFileReader(fileObjReader);
    }

    public BidReader getBidReader(File file) {
        FileObjReaderFactory fileObjReaderFactory = new FileObjReaderFactory();
        FileObjReader<SeatBidResponse> fileObjReader = fileObjReaderFactory.getJsonFileReader(file);

        return new BidJsonFileReader(fileObjReader);
    }
}
