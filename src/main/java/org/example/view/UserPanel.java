package org.example.view;

import org.example.controller.OrderController;
import org.example.database.FoodItemDAO;
import org.example.model.FoodItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class UserPanel extends JFrame {
    private List<FoodItem> cart = new ArrayList<>();
    private JLabel totalLabel = new JLabel("Total: $0.0");

    public UserPanel(int userId) {
        setTitle("User Panel");
        setSize(800, 600);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setLayout(new BorderLayout());

        // Buttons
        JButton viewMenuButton = new JButton("View Menu");
        JButton checkoutButton = new JButton("Checkout");
        JButton viewOrdersButton = new JButton("View Order Details");
        JButton logoutButton = new JButton("Logout");

        // Button actions
        viewMenuButton.addActionListener(e -> displayMenu());
        checkoutButton.addActionListener(e -> checkout(userId));
        viewOrdersButton.addActionListener(e -> OrderController.viewUserOrders(userId));
        logoutButton.addActionListener(e -> {
            dispose();
            new LoginScreen();
        });

        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(viewMenuButton);
        buttonPanel.add(checkoutButton);
        buttonPanel.add(viewOrdersButton);
        buttonPanel.add(logoutButton);

        add(totalLabel, BorderLayout.NORTH);
        add(buttonPanel, BorderLayout.SOUTH);

        setVisible(true);
    }

    private void displayMenu() {
        List<FoodItem> menu = new FoodItemDAO().getAllFoodItems();

        String[] menuItems = new String[menu.size()];
        for (int i = 0; i < menu.size(); i++) {
            FoodItem item = menu.get(i);
            menuItems[i] = item.getName() + " - $" + item.getPrice();
        }

        String selectedItem = (String) JOptionPane.showInputDialog(
                this,
                "Select a food item:",
                "Menu",
                JOptionPane.PLAIN_MESSAGE,
                null,
                menuItems,
                menuItems[0]
        );

        if (selectedItem != null) {
            for (FoodItem item : menu) {
                if (selectedItem.startsWith(item.getName())) {
                    cart.add(item);
                    updateTotal();
                }
            }
        }
    }

    private void updateTotal() {
        double total = cart.stream().mapToDouble(FoodItem::getPrice).sum();
        totalLabel.setText("Total: $" + total);
    }

    private void checkout(int userId) {
        if (cart.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Cart is empty!");
            return;
        }

        new OrderSummaryPanel(cart);
        OrderController.placeOrder(userId, cart);
        cart.clear();
        updateTotal();
}
}