package com.crossal.ad;

import com.crossal.ad.model.Ad;
import com.crossal.bid.BidServiceImpl;
import com.crossal.bid.model.Bid;
import com.crossal.bid.BidService;
import com.crossal.helpers.FileWriter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;

@Service
public class AdServiceImpl implements AdService {

    private static final Logger logger = Logger.getLogger(AdServiceImpl.class);

    @Autowired
    private BidService bidService;
    @Autowired
    private FileWriter fileWriter;

    @Value("${adplaform.ad.file.defaultpath}")
    private String defaultAdFilePath;

    @Override
    public Ad getAd() {
        return getAd(null);
    }

    @Override
    public Ad getAd(String filePath) {
        Bid bid = filePath == null || filePath.isEmpty() ? bidService.getHighestBidder() : bidService.getHighestBidder(new File(filePath));

        if (bid == null) {
            return null;
        }

        Ad ad = new Ad();
        ad.setMarkup(bid.getAdMarkup());

        try {
            fileWriter.writeToFile(defaultAdFilePath, "<html><body>" + bid.getAdMarkup() + "</body></html>");
        } catch (IOException e) {
            logger.error("Exception writing ad output to file: " + e);
        }

        return ad;
    }
}
