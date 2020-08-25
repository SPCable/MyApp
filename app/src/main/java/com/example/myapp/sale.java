package com.example.myapp;

public class sale {
    String Name;
    String Price;
    String Discount;
    String Img;

    public sale(String name, String price, String discount, String img) {
        Name = name;
        Price = price;
        Discount = discount;
        Img = img;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public String getDiscount() {
        return Discount;
    }

    public void setDiscount(String discount) {
        Discount = discount;
    }

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public sale()
    {
    }
}
