package com.example.myapp.History;

import java.text.DateFormat;

public class ItemHistory {

    DateFormat ngay;
    String name;
    String price;
    String soluong;


    public ItemHistory(DateFormat ngay, String name, String price, String soluong) {
        this.ngay = ngay;
        this.name = name;
        this.price = price;
        this.soluong = soluong;
    }


    public DateFormat getNgay() {
        return ngay;
    }

    public void setNgay(DateFormat ngay) {
        this.ngay = ngay;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getSoluong() {
        return soluong;
    }

    public void setSoluong(String soluong) {
        this.soluong = soluong;
    }
}
