package com.crossal.ad;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AdController {

    private static final Logger logger = Logger.getLogger(AdController.class);

    @Autowired
    private AdService adService;

    /**
     * View an ad.
     *
     * @param filePath Path to file on web server file system to read json bids from. Default file path in application.properties will be used when null.
     * @param model
     * @return
     */
    @RequestMapping(value = "/ad", method = RequestMethod.GET)
    public String showAd(@RequestParam(value = "filePath", required = false) String filePath, Model model) {
        model.addAttribute("ad", adService.getAd(filePath));
        return "index";
    }
}
