package com.example.myapp;

public class Food_sp {
    String nameFood;
    String ImgUrl;

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(String imgUrl) {
        ImgUrl = imgUrl;
    }

    public Food_sp(String nameFood, String imgUrl) {
        this.nameFood = nameFood;
        ImgUrl = imgUrl;
    }
}
