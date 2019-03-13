package com.crossal.ad;

import com.crossal.ad.model.Ad;
import com.crossal.bid.model.Bid;
import com.crossal.bid.BidService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdServiceImpl implements AdService {

    @Autowired
    BidService bidService;

    @Override
    public Ad getAd() {
        Bid bid = bidService.getHighestBidder();

        if (bid == null) {
            return null;
        }

        Ad ad = new Ad();
        ad.setMarkup(bid.getAdMarkup());

        return ad;
    }

//    @Override
//    public Ad getAd(String filePath) {
//        SeatBidResponse seatBidResponse = bidReader.
//    }
}
