package com.example.myapp.Search;

public class ItemSearch {

    String Name;
    String Img;

    public ItemSearch() {
    }

    public ItemSearch(String name, String img) {
        this.Name = name;
        this.Img = img;
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
}
