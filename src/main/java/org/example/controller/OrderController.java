package org.example.controller;

import org.example.database.OrderDAO;
import org.example.model.FoodItem;
import org.example.model.Order;


import javax.swing.*;
import java.util.List;

public class OrderController {
    private static OrderDAO orderDAO = new OrderDAO();

    public static void placeOrder(int userId, List<FoodItem> foodItems) {
        if (foodItems.isEmpty()) {
            JOptionPane.showMessageDialog(null, "Cart is empty. Please add items to place an order.");
            return;
        }

        double totalPrice = foodItems.stream().mapToDouble(FoodItem::getPrice).sum();
        Order order = new Order(0, userId, totalPrice, foodItems);

        boolean success = orderDAO.saveOrder(order);

        if (success) {
            JOptionPane.showMessageDialog(null, "Order placed successfully!");
        } else {
            JOptionPane.showMessageDialog(null, "Failed to place the order. Please try again.");
        }
    }

    public static void viewUserOrders(int userId) {
        if (userId == -1) { // Admin request for all orders
            List<Order> allOrders = orderDAO.getAllOrders();
            displayOrders(allOrders, "All Orders");
        } else { // User-specific orders
            List<Order> userOrders = orderDAO.getOrdersByUserId(userId);
            displayOrders(userOrders, "Your Orders");
        }
    }

    private static void displayOrders(List<Order> orders, String title) {
        if (orders.isEmpty()) {
            JOptionPane.showMessageDialog(null, "No orders found.");
            return;
        }

        StringBuilder sb = new StringBuilder(title + ":\n\n");
        for (Order order : orders) {
            sb.append("Order ID: ").append(order.getId())
                    .append(", Total Price: $").append(order.getTotalPrice())
                    .append(", Items: ").append(order.getFoodItems().size()).append("\n");
        }

        JOptionPane.showMessageDialog(null, sb.toString());
    }
}