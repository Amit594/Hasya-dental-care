package com.example.haasyadentalcare.ui.Mission;

public class Item {


    String name;
    String details;
    int image;

    public Item(String name, String details, int image) {
        this.name = name;
        this.details = details;
        this.image = image;
    }


    public String getName() {
        return name;
    }

    public String getDetails() {
        return details;
    }

    public int getImage() {
        return image;
    }
}
