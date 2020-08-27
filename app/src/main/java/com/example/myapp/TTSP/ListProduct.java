package com.example.myapp.TTSP;

public class ListProduct {


    String name;
    String brand;
    String price;
    String image;
    String nameProduct;
    String imageProduct;


    public ListProduct(String name, String brand, String price, String image) {
        this.name = name;
        this.brand = brand;
        this.price = price;
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public ListProduct()
    {

    }
}
