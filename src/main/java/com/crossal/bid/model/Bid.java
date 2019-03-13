package com.crossal.bid.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public class Bid {
    private String id;
    @JsonProperty("impid")
    private Integer impId;
    private Double price;
    @JsonProperty("adm")
    private String adMarkup;
    @JsonProperty("adomain")
    private List<String> adDomains;
    private Integer w;
    private Integer h;
    private String crid;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Integer getImpId() {
        return impId;
    }

    public void setImpId(Integer impId) {
        this.impId = impId;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getAdMarkup() {
        return adMarkup;
    }

    public void setAdMarkup(String adMarkup) {
        this.adMarkup = adMarkup;
    }

    public List<String> getAdDomains() {
        return adDomains;
    }

    public void setAdDomains(List<String> adDomains) {
        this.adDomains = adDomains;
    }

    public Integer getW() {
        return w;
    }

    public void setW(Integer w) {
        this.w = w;
    }

    public Integer getH() {
        return h;
    }

    public void setH(Integer h) {
        this.h = h;
    }

    public String getCrid() {
        return crid;
    }

    public void setCrid(String crid) {
        this.crid = crid;
    }
}
