package com.example.franklin.hudumu.models;

public class CateringModel {
    public int cateringImages;
    public String details;
    public String price;

    public CateringModel(int cateringImages, String details, String price) {
        this.cateringImages = cateringImages;
        this.details = details;
        this.price = price;
    }

    public int getCateringImages() {
        return cateringImages;
    }

    public void setCateringImages(int cateringImages) {
        this.cateringImages = cateringImages;
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
