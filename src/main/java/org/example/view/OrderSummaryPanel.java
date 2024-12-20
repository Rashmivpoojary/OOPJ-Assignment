package org.example.view;

import javax.swing.*;
import java.awt.*;
import java.util.List;
import org.example.model.FoodItem;

public class OrderSummaryPanel extends JFrame {
    public OrderSummaryPanel(List<FoodItem> foodItems) {
        setTitle("Order Summary");
        setSize(600, 400);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);

        // Ensure the received list of food items is not null
        if (foodItems == null || foodItems.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No items in the order.");
            dispose(); // Close the window if there are no items
            return;
        }

        StringBuilder orderDetails = new StringBuilder("Order Summary:\n\n");
        double total = 0;

        for (FoodItem item : foodItems) {
            orderDetails.append(item.getName())
                    .append(" - $")
                    .append(item.getPrice())
                    .append("\n");
            total += item.getPrice();
        }

        orderDetails.append("\nTotal: $").append(total);

        JTextArea orderSummary = new JTextArea(orderDetails.toString());
        orderSummary.setEditable(false);

        add(new JScrollPane(orderSummary), BorderLayout.CENTER);
        setVisible(true);
    }
}