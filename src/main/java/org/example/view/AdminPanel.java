package org.example.view;
import org.example.controller.FoodController;
import org.example.controller.OrderController;
import org.example.database.FoodItemDAO;
import org.example.model.FoodItem;


import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;

public class AdminPanel extends JFrame {
    private JTable foodTable;

    public AdminPanel() {
        setTitle("Admin Panel");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Table to display food items
        foodTable = new JTable();
        JScrollPane scrollPane = new JScrollPane(foodTable);
        updateFoodTable();

        // Buttons
        JButton addFoodButton = new JButton("Add Food Item");
        JButton viewOrdersButton = new JButton("View Orders");
        JButton logoutButton = new JButton("Logout");

        // Button actions
        addFoodButton.addActionListener(e -> {
            FoodController.addFoodItem();
            updateFoodTable();
        });

        viewOrdersButton.addActionListener(e -> OrderController.viewUserOrders(-1));

        logoutButton.addActionListener(e -> {
            dispose();
            new LoginScreen();
        });

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(addFoodButton);
        buttonPanel.add(viewOrdersButton);
        buttonPanel.add(logoutButton);

        // Add components to the frame
        add(scrollPane, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void updateFoodTable() {
        FoodItemDAO foodItemDAO = new FoodItemDAO();
        List<FoodItem> foodItems = foodItemDAO.getAllFoodItems();

        String[] columnNames = {"ID", "Name", "Price"};
        DefaultTableModel model = new DefaultTableModel(columnNames, 0);

        for (FoodItem item : foodItems) {
            model.addRow(new Object[]{item.getId(), item.getName(), item.getPrice()});
        }

        foodTable.setModel(model);
}
}