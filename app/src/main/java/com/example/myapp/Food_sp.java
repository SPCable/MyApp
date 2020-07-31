package com.example.myapp;

public class Food_sp {
    String nameFood;
    Integer ImgUrl;

    public Food_sp(String nameFood, Integer imgUrl) {
        this.nameFood = nameFood;
        ImgUrl = imgUrl;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public Integer getImgUrl() {
        return ImgUrl;
    }

    public void setImgUrl(Integer imgUrl) {
        ImgUrl = imgUrl;
    }
}
