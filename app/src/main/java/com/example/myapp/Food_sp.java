package com.example.myapp;

public class Food_sp {
    String nameFood;
    String ImgUrl;
    String Id;

    public Food_sp(String nameFood, String imgUrl, String id) {
        this.nameFood = nameFood;
        ImgUrl = imgUrl;
        Id = id;
    }

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

    public String getId() {
        return Id;
    }

    public void setId(String id) {
        Id = id;
    }

    public Food_sp()
    {
    }
}
