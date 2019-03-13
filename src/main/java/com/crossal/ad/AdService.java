package com.crossal.ad;

import com.crossal.ad.model.Ad;

public interface AdService {
    Ad getAd();
    Ad getAd(String filePath);
}
