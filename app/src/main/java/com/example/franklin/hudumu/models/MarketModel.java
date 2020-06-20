package com.example.franklin.hudumu.models;

public class MarketModel {
    public int marketImages;
    public String details;
    public String price;

    public MarketModel(int marketImages, String details, String price) {
        this.marketImages = marketImages;
        this.details = details;
        this.price = price;
    }

    public int getMarketImages() {
        return marketImages;
    }

    public void setMarketImages(int marketImages) {
        this.marketImages = marketImages;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
