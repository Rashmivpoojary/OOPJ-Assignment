package org.example.model;

import java.util.List;



public class Order {
    private int id;
    private int userId;
    private double totalPrice;
    private List<FoodItem> foodItems;

    public Order(int id, int userId, double totalPrice, List<FoodItem> foodItems) {
        this.id = id;
        this.userId = userId;
        this.totalPrice = totalPrice;
        this.foodItems = foodItems;
    }

    // Getters and setters
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public int getUserId() { return userId; }
    public void setUserId(int userId) { this.userId = userId; }

    public double getTotalPrice() { return totalPrice; }
    public void setTotalPrice(double totalPrice) { this.totalPrice = totalPrice; }

    public List<FoodItem> getFoodItems() { return foodItems; }
    public void setFoodItems(List<FoodItem> foodItems) { this.foodItems =foodItems;}
}
