package com.example.myapp;

public class Food_sp {
    String nameFood;
    String ImgUrl;
    String idFood;

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

    public String getIdFood() {
        return idFood;
    }

    public void setIdFood(String idFood) {
        this.idFood = idFood;
    }


    public Food_sp(String nameFood, String imgUrl, String idFood) {
        this.nameFood = nameFood;
        ImgUrl = imgUrl;
        this.idFood = idFood;
    }

    public Food_sp()
    {
    }
}
