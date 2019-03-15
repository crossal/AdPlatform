package com.crossal.bid;

import com.crossal.bid.model.SeatBidResponse;
import com.crossal.helpers.FileObjReader;
import com.crossal.helpers.FileObjReaderFactory;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import java.io.File;
import java.io.IOException;

@Component
public class BidReaderFactory {

    private static final Logger logger = Logger.getLogger(BidReaderFactory.class);

    @Value("${adplaform.bid.file.defaultpath}")
    private String defaultBidFilePath;

    @Autowired
    private ResourceLoader resourceLoader;

    public BidReader getBidReader() {
        File file = null;
        try {
            file = resourceLoader.getResource(defaultBidFilePath).getFile();
        } catch (IOException e) {
            logger.error("Default file was retrieved correctly using application properties: " + e);
        }
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
