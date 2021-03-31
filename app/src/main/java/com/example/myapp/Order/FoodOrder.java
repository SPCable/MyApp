package com.example.myapp.Order;

public class FoodOrder {
    String nameFood;
    String foodPrice;
    String foodCount;
    String id;
    String foodPriceO;



    public FoodOrder(String nameFood, String foodPrice, String foodCount, String id, String foodPriceO) {
        this.nameFood = nameFood;
        this.foodPrice = foodPrice;
        this.foodCount = foodCount;
        this.id = id;
        this.foodPriceO = foodPriceO;
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

    public void setFoodPrice(String foodPrice) {
        this.foodPrice = foodPrice;
    }

    public String getFoodCount() {
        return foodCount;
    }

    public void setFoodCount(String foodCount) {
        this.foodCount = foodCount;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getFoodPriceO() {
        return foodPriceO;
    }

    public void setFoodPriceO(String foodPriceO) {
        this.foodPriceO = foodPriceO;
    }
}
