package com.crossal.ad;

import com.crossal.ad.model.Ad;
import com.crossal.bid.model.Bid;
import com.crossal.bid.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    private BidService bidService;

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

        return ad;
    }
}
