package com.example.franklin.hudumu.models;

public class CarWashingModel {
     public String details;
     public boolean isChecked;

    public CarWashingModel(String details, boolean isChecked) {

        this.details = details;
        this.isChecked = isChecked;
    }


    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }
}
