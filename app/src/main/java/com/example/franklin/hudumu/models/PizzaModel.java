package com.example.franklin.hudumu.models;

public class PizzaModel {
    public int pizzaImages;
    public String type;
    public String size;
    public String price;

    public PizzaModel(int pizzaImages, String type, String size, String price) {
        this.pizzaImages = pizzaImages;
        this.type = type;
        this.size = size;
        this.price = price;
    }

    public int getPizzaImages() {
        return pizzaImages;
    }

    public void setPizzaImages(int pizzaImages) {
        this.pizzaImages = pizzaImages;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSize() {
        return size;
    }

    public void setSize(String size) {
        this.size = size;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }
}
