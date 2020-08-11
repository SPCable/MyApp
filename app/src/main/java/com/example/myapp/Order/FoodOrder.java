package com.example.myapp.Order;

public class FoodOrder {
    String nameFood;
    String foodPrice;
    Integer foodCount;
    public FoodOrder(String nameFood, String price) {
        this.nameFood = nameFood;
        foodPrice = price;
    }

    public String getNameFood() {
        return nameFood;
    }

    public void setNameFood(String nameFood) {
        this.nameFood = nameFood;
    }

    public String getFoodPrice() {
        return foodPrice;
    }

    public void setFoodPrice(String price) {
        foodPrice = price;
    }
}
