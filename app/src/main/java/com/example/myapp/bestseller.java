package com.example.myapp;

public class bestseller {
    String Name;
    Integer Img;
    String Price;

    public bestseller(String name, Integer img, String price) {
        Name = name;
        Img = img;
        Price = price;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public Integer getImg() {
        return Img;
    }

    public void setImg(Integer img) {
        Img = img;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }
}
