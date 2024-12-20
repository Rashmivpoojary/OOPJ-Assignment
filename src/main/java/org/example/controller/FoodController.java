package org.example.controller;

import org.example.database.FoodItemDAO;
import org.example.model.FoodItem;

import javax.swing.*;
import java.util.List;

public class FoodController {
    private static FoodItemDAO foodItemDAO = new FoodItemDAO();

    public static void addFoodItem() {
        String name = JOptionPane.showInputDialog(null, "Enter Food Name:");
        String priceStr = JOptionPane.showInputDialog(null, "Enter Food Price:");
        double price;

        try {
            price = Double.parseDouble(priceStr);
            FoodItem foodItem = new FoodItem(0, name, price);
            foodItemDAO.addFoodItem(foodItem);
            JOptionPane.showMessageDialog(null, "Food item added successfully!");
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(null, "Invalid price entered.");
        }
    }

    public static void viewFoodItems() {
        List<FoodItem> foodItems = foodItemDAO.getAllFoodItems();
        StringBuilder sb = new StringBuilder("Available Food Items:\n\n");

        for (FoodItem foodItem : foodItems) {
            sb.append("ID: ").append(foodItem.getId())
                    .append(", Name: ").append(foodItem.getName())
                    .append(", Price: $").append(foodItem.getPrice()).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
}
}