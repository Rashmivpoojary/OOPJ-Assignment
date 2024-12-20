package org.example.database;
import org.example.model.User;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class UserDAO {
    public int authenticateUser(String username, String password) {
        int userId = -1;

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT id FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                userId = rs.getInt("id"); // Get the user's ID if valid credentials
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return userId; // Return -1 if authentication fails
    }

    public boolean isAdmin(String username) {
        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT is_admin FROM users WHERE username = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                return rs.getBoolean("is_admin"); // Return true if the user is an admin
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }
    public String getUserRole(String username, String password) {
        String role = null;

        try (Connection conn = DatabaseConnection.getConnection()) {
            String query = "SELECT is_admin FROM users WHERE username = ? AND password = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setString(1, username);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                boolean isAdmin = rs.getBoolean("is_admin");
                role = isAdmin ? "admin" : "customer";
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return role; // Returns "admin", "customer", or null if the user is not found
    }

}
