Food Ordering Management System
ABSTRACT:
This is a Java Swing-based Food Ordering Management System, designed to manage and streamline food orders for customers and administrators. The application features user-friendly interfaces for both customers and admins, allowing for seamless interaction. It uses MySQL as the backend database for storing data.

Features

Admin Functionality
View all food items in a tabular format.
Add new food items to the menu.
View all orders placed by customers.
Logout functionality.
Customer Functionality
View menu and add items to the cart.
Checkout and place orders.
View order history (details of previous orders).
Logout functionality.

Technologies Used

>>Java Swing: For creating the graphical user interface (GUI).

>>MySQL: As the relational database for storing food items, orders, and user data.

>>Apache Maven: For managing dependencies and building the project.

>>IntelliJ IDEA Ultimate: As the integrated development environment (IDE).
Prerequisites
1. Java Development Kit (JDK): Version 8 or higher.
2. MySQL: Install and configure MySQL on your system.
3. IntelliJ IDEA Ultimate: Install and configure the IDE.
4. Apache Maven: Ensure Maven is installed and configured.
Database Setup
1. Create the Database

Run the following SQL commands to set up the database:



CREATE DATABASE food_ordering_system;

USE food_ordering_system;

-- Table for users




CREATE TABLE users (
    id INT AUTO_INCREMENT PRIMARY KEY,
    username VARCHAR(50) NOT NULL,
    password VARCHAR(50) NOT NULL,
    role ENUM('ADMIN', 'CUSTOMER') NOT NULL
);


-- Table for food items



CREATE TABLE food_items (
    id INT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL,
    price DOUBLE NOT NULL
);



-- Table for orders



CREATE TABLE orders (
    id INT AUTO_INCREMENT PRIMARY KEY,
    user_id INT NOT NULL,
    total_price DOUBLE NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(id)
);



-- Table for order details




CREATE TABLE order_details (
    id INT AUTO_INCREMENT PRIMARY KEY,
    order_id INT NOT NULL,
    food_item_id INT NOT NULL,
    quantity INT NOT NULL,
    FOREIGN KEY (order_id) REFERENCES orders(id),
    FOREIGN KEY (food_item_id) REFERENCES food_items(id)
);
2. Insert Sample Data

Insert sample users and food items for testing:

-- Sample admin and customer users



INSERT INTO users (username, password, role) VALUES
('admin', 'admin', 'ADMIN'),
('user', 'user', 'CUSTOMER');



-- Sample food items



INSERT INTO food_items (name, price) VALUES
('Pizza', 8.99),
('Burger', 5.99),
('Pasta', 7.99),
('Coke', 1.99);



How to Run the Project

1. Clone the Repository

git clone https://github.com/Rashmivpoojary/FoodOrderingSystem.git
cd FoodOrderingSystem

2. Open the Project in IntelliJ

Open IntelliJ IDEA Ultimate.

Select File > Open and choose the project directory.


3. Configure Database Connection

Update the DatabaseConnection class in src/main/java/org/example/database/DatabaseConnection.java with your MySQL credentials:

private static final String URL = "jdbc:mysql://localhost:3306/food_ordering_system";
private static final String USER = "your_mysql_username";
private static final String PASSWORD = "your_mysql_password";

4. Build and Run

Ensure all Maven dependencies are downloaded.

Run the LoginScreen class to start the application.

Application Workflow

Admin Workflow

1. Login as an admin using:

Username: admin

Password: admin
2. View all food items displayed in a table.
3. Add new food items using the "Add Food Item" button.
4. View all orders placed by customers using the "View Orders" button.
5. Logout using the "Logout" button.
Customer Workflow
1. Login as a customer using:

Username: user

Password: user
2. View the menu and add items to the cart.
3. Checkout to place an order.
4. View your order history using the "View Order Details" button.
5. Logout using the "Logout" button.


Project Structure



FoodOrderingSystem/


├── src/

│   ├── main/

│   │   ├── java/

│   │   │   ├── org/example/

│   │   │   │   ├── controller/ 
                                         # Contains controllers for managing logic
│   │   │   │   ├── database/ 
                                          # Contains database connection and DAOs
│   │   │   │   ├── model/  
                                          # Contains data models
│   │   │   │   ├── view/  
                                         # Contains Swing-based GUI components
│   ├── resources/
│       └── application.properties      # Configuration file for database
└── pom.xml                              # Maven build file


Improvements for Future

Add user registration functionality.

Include quantity selection during food ordering.

Generate detailed order reports for the admin.

Implement a payment system (e.g., online payments).

Contributors:
https://github.com/Rashmivpoojary

Other Contributors: 
            https://github.com/PriyaLakshmi21

            
           
https://github.com/SSharvariChatra15

Demo link>>

https://youtu.be/HNcq5V5D9eA?si=cf3SOiKEDbqxwaEp
