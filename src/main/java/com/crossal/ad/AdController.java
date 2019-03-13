package com.crossal.ad;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@Controller
public class AdController {

    private static final Logger logger = Logger.getLogger(AdController.class);

    @Autowired
    private AdService adService;

    /**
     * View an ad.
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "ad", method = RequestMethod.GET)
    public String showAd(Model model) {
        model.addAttribute("ad", adService.getAd());
        return "adshow";
    }

//    @RequestMapping(value = "/getFile/{path:.+}", method = RequestMethod.GET)
//    public String showAd(@PathVariable String path, Model model) {
//        model.addAttribute("ad", adService.getAd(path));
//        return "adshow";
//    }
}
