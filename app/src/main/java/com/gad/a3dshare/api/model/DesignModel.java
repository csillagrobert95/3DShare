package com.gad.a3dshare.api.model;

import android.net.Uri;

public class DesignModel {
    private String name;
    private String description;
    private String length;
    private String width;
    private String height;
    private String price;
    private Uri imageUri;
    private Uri stlUri;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getHeight() {
        return height;
    }

    public void setHeight(String height) {
        this.height = height;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public Uri getImageUri() {
        return imageUri;
    }

    public void setImageUri(Uri imageUri) {
        this.imageUri = imageUri;
    }

    public Uri getStlUri() {
        return stlUri;
    }

    public void setStlUri(Uri stlUri) {
        this.stlUri = stlUri;
    }

    @Override
    public String toString() {
        return "DesignModel{" +
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", length='" + length + '\'' +
                ", width='" + width + '\'' +
                ", height='" + height + '\'' +
                ", price='" + price + '\'' +
                ", imageUri=" + imageUri +
                ", stlUri=" + stlUri +
                '}';
    }
}
