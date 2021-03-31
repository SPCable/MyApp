package com.example.myapp;

public class bestseller {
    String Name;
    String Img;
    String Price;

    public bestseller(String name, String img, String price) {
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

    public String getImg() {
        return Img;
    }

    public void setImg(String img) {
        Img = img;
    }

    public String getPrice() {
        return Price;
    }

    public void setPrice(String price) {
        Price = price;
    }

    public bestseller()
    {
    }
}
