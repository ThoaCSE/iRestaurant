# iRestaurant - Restaurant Management System

**Software Engineering 1 | 3rd Year 2025 | Vietnamese - German University**

---

## Table of Contents

1. [Project Overview](#1-project-overview)
2. [System Architecture](#2-system-architecture)
3. [Features](#3-features)
4. [Project Structure](#4-project-structure)
5. [Class Descriptions](#5-class-descriptions)
6. [Installation & Setup](#6-installation--setup)
7. [How to Compile and Run](#7-how-to-compile-and-run)
   - 7.1 [Running the Main Application](#71-running-the-main-application)
   - 7.2 [Running Tests](#72-running-tests)
8. [User Guide](#8-user-guide)
9. [Testing Documentation](#9-testing-documentation)
10. [Technologies Used](#10-technologies-used)
11. [Contributors](#11-contributors)

---

## 1. Project Overview

iRestaurant is a comprehensive restaurant management system built with Java Swing GUI. The system provides role-based access for three types of users: Customers, Waiters, and Administrators. It manages meal orders, menu items, and order status tracking throughout the restaurant workflow.

**Project Goals:**
- Implement a user-friendly GUI for restaurant operations
- Manage meal orders with different statuses (ORDERED, IN_KITCHEN, READY, DELIVERING, DELIVERED, ARCHIVED, INACTIVE)
- Provide role-based interfaces for different user types
- Ensure robust order management with comprehensive testing

**Date:** December 24, 2025

---

## 2. System Architecture

The system follows a **Model-View-Controller (MVC)** pattern:

- **Model**: `MealOrder`, `MealOrderMgmt`, `Menu`, `RestaurantMenuItem`, `OrderItem`, `OrderStatus`
- **View**: Swing GUI components (`MainPanel`, `AdminPanel`, `CustomerOrderingPanel`, `WaiterDeliveryPanel`)
- **Controller**: Event handlers and business logic within management classes

**Architecture Diagram:**
```
┌─────────────────────────────────────────┐
│           Main Application              │
│          (MainPanel.java)               │
└───────────┬─────────────────────────────┘
            │
    ┌───────┴────────┬─────────────┐
    │                │             │
┌───▼────┐    ┌─────▼──────┐  ┌──▼──────┐
│Customer│    │   Waiter   │  │  Admin  │
│ Panel  │    │   Panel    │  │  Panel  │
└───┬────┘    └─────┬──────┘  └──┬──────┘
    │               │             │
    └───────┬───────┴────────┬────┘
            │                │
    ┌───────▼────────┐  ┌───▼──────────┐
    │ MealOrderMgmt  │  │     Menu     │
    └───────┬────────┘  └───┬──────────┘
            │               │
    ┌───────▼────────┐  ┌───▼──────────┐
    │   MealOrder    │  │ RestaurantMI │
    └────────────────┘  └──────────────┘
```

---

## 3. Features

### 3.1 Role-Based Access
- **Customer**: Browse menu and place orders
- **Waiter**: View orders and update delivery status
- **Administrator**: Manage menu items, view all orders, system configuration

### 3.2 Order Management
- Create new meal orders
- Track order status through workflow stages
- Update order status (ORDERED → IN_KITCHEN → READY → DELIVERING → DELIVERED)
- Count orders by status
- View order history

### 3.3 Menu Management
- Add new menu items (Admin only)
- View menu with prices and descriptions
- Manage item availability

### 3.4 User Interface
- Intuitive Swing GUI with color-coded role buttons
- Real-time status updates
- Responsive design with proper layout management
- Hover effects and visual feedback

---

## 4. Project Structure

```
iRestaurant/
├── README.md
├── bin/                          # Compiled .class files
│   └── test/
├── lib/                          # External libraries
│   ├── hamcrest-2.2.jar
│   ├── hamcrest-core-1.3-javadoc.jar
│   ├── junit-4.13.2.jar
│   └── junit-4.13.2-javadoc.jar
├── src/                          # Source code
│   ├── Main.java                 # Application entry point
│   ├── MainPanel.java            # Main GUI with role selection
│   ├── AdminPanel.java           # Admin interface
│   ├── CustomerOrderingPanel.java # Customer interface (placeholder)
│   ├── WaiterDeliveryPanel.java  # Waiter interface (placeholder)
│   ├── MealOrder.java            # Meal order model
│   ├── MealOrderMgmt.java        # Order management logic
│   ├── OrderItem.java            # Individual order item
│   ├── OrderStatus.java          # Order status enum
│   ├── Menu.java                 # Menu management
│   └── RestaurantMenuItem.java   # Menu item model
└── test/                         # Test files
    ├── MealOrderMgmtTest.java    # JUnit tests (integrated)
    └── StandaloneMealOrderTest.java # Standalone tests
```

---

## 5. Class Descriptions

### 5.1 Core Classes

**Main.java**
- Entry point of the application
- Launches the MainPanel GUI using SwingUtilities

**MainPanel.java**
- Role selection frame with three options: Customer, Waiter, Admin
- Contains nested panel classes: HeaderPanel, RoleSelectionPanel, RoleButton, FooterPanel
- Implements custom button components with hover effects

**MealOrder.java**
- Represents a single meal order
- Attributes: orderID, tableID, customerID, status, items list
- Methods: getters, setters, addItem(), getTotalPrice()

**MealOrderMgmt.java**
- Manages collection of meal orders
- Methods:
  - `addMealOrder(MealOrder)`: Add new order
  - `updateMealOrder(int orderID, OrderStatus)`: Update order status
  - `countNumberOf(OrderStatus)`: Count orders by status
  - `getOrderCount()`: Get total orders

**OrderStatus.java** (Enum)
- Status values: ORDERED, IN_KITCHEN, READY, DELIVERING, DELIVERED, ARCHIVED, INACTIVE

**RestaurantMenuItem.java**
- Represents menu item with name, description, price, and image

**Menu.java**
- Manages restaurant menu items
- Methods: addItem(), removeItem(), getAllItems()

### 5.2 GUI Classes

**AdminPanel.java**
- Administrator interface for managing system
- Features: Add menu items, view orders, system configuration

**CustomerOrderingPanel.java** (Placeholder)
- Customer interface for browsing menu and placing orders
- To be implemented: Menu display, cart management, order submission

**WaiterDeliveryPanel.java** (Placeholder)
- Waiter interface for order delivery management
- To be implemented: Order list, status updates, notifications

---

## 6. Installation & Setup

### 6.1 Prerequisites
- **Java Development Kit (JDK)**: Version 8 or higher
- **IDE**: VS Code, IntelliJ IDEA, Eclipse, or any Java IDE
- **JUnit**: Version 4.13.2 (included in `lib/` folder)

### 6.2 Setup Steps

1. **Clone or Download the Project**
   ```bash
   git clone <repository-url>
   cd iRestaurant
   ```

2. **Verify JDK Installation**
   ```powershell
   java -version
   javac -version
   ```

3. **Configure IDE**
   - Import project as Java project
   - Set JDK as project SDK
   - Add `lib/*.jar` files to classpath

4. **Verify Project Structure**
   - Ensure `src/` contains all Java source files
   - Ensure `lib/` contains JUnit JAR files
   - Ensure `test/` contains test files

---

## 7. How to Compile and Run

### 7.1 Running the Main Application

#### Option A: Using VS Code

1. Open `src/Main.java`
2. Click the **Run** button (▶️) above the `main` method
3. Or press `F5` to run in debug mode
4. The MainPanel GUI will launch with role selection

#### Option B: Using Command Line (PowerShell)

```powershell
# Navigate to project directory
cd c:\Users\Oath\IdeaProjects\iRestaurant

# Compile all source files
javac -d bin src/*.java

# Run the main application
java -cp bin Main
```

#### Option C: Using Terminal with Classpath

```powershell
# Compile with dependencies
javac -cp "lib/*" -d bin src/*.java

# Run application
java -cp "lib/*;bin" Main
```

**Expected Output:**
- A window titled "iRestaurant - Role Selection" appears
- Three colored buttons for Customer (Green), Waiter (Blue), and Admin (Orange)
- Click any button to access that role's interface

---

### 7.2 Running Tests

The project includes comprehensive JUnit tests for `MealOrderMgmt` class.

#### Test Files:
- **MealOrderMgmtTest.java**: Integrated tests (requires src classes)
- **StandaloneMealOrderTest.java**: Independent tests with embedded classes

#### Option A: Using VS Code Test Runner

1. Open `test/StandaloneMealOrderTest.java`
2. Click **Test** icon (🧪) in the left sidebar
3. Click **Run All Tests** or individual test play buttons
4. View results with green checkmarks (✓) for passed tests

#### Option B: Using Command Line with Custom Output

```powershell
# Navigate to project directory
cd c:\Users\Oath\IdeaProjects\iRestaurant

# Compile the standalone test
javac -cp "lib/junit-4.13.2.jar;lib/hamcrest-2.2.jar" test/StandaloneMealOrderTest.java

# Run with custom output format
java -cp "lib/junit-4.13.2.jar;lib/hamcrest-2.2.jar;test" StandaloneMealOrderTest
```

**Expected Output:**
```
Test: updateMealOrderTestFunction1
Result: Success

Test: updateMealOrderTestFunction2
Result: Success

Test: addMealOrderTestFunction1
Result: Success

Test: addMealOrderTestFunction2
Result: Success
```

#### Option C: Using JUnit Runner (Traditional)

```powershell
# Compile test and source files
javac -cp "lib/*" -d bin src/*.java test/MealOrderMgmtTest.java

# Run JUnit tests
java -cp "lib/*;bin" org.junit.runner.JUnitCore MealOrderMgmtTest
```

**Expected Output:**
```
JUnit version 4.13.2
.....
Time: 0.005

OK (4 tests)
```

---

## 8. User Guide

### 8.1 Launching the Application

1. Run `Main.java` using one of the methods in Section 7.1
2. The main panel appears with three role options

### 8.2 Customer Role (Under Development)

**Future Features:**
- Browse restaurant menu with images and prices
- Add items to cart
- Specify table number
- Submit order to kitchen
- Track order status

### 8.3 Waiter Role (Under Development)

**Future Features:**
- View pending orders (IN_KITCHEN, READY)
- Update order status to DELIVERING
- Mark orders as DELIVERED
- Receive notifications for new orders

### 8.4 Admin Role (Active)

**Current Features:**
- Add new menu items
- View all orders in system
- Manage restaurant settings
- Access system configuration

**How to Use Admin Panel:**
1. Click "Admin: Management" button
2. Admin panel opens with menu management tools
3. Add menu items with name, description, and price
4. View order statistics

---

## 9. Testing Documentation

### 9.1 Test Coverage

The project includes **4 comprehensive test functions**:

#### Test 1: updateMealOrderTestFunction1
- **Purpose**: Test updating order status from ORDERED to DELIVERED
- **Setup**: 3 orders with different statuses
- **Verification**:
  - ORDERED count decreases by 1
  - DELIVERED count increases by 1
  - Total order count remains 3

#### Test 2: updateMealOrderTestFunction2
- **Purpose**: Test updating order status from IN_KITCHEN to READY
- **Setup**: 2 orders (IN_KITCHEN and ORDERED)
- **Verification**:
  - READY count increases by 1
  - IN_KITCHEN count decreases by 1
  - Total order count remains 2

#### Test 3: addMealOrderTestFunction1
- **Purpose**: Test adding a new meal order
- **Setup**: 2 existing orders
- **Verification**:
  - Total order count increases by 1
  - INACTIVE count increases by 1 (default status)

#### Test 4: addMealOrderTestFunction2
- **Purpose**: Test null order handling
- **Setup**: 1 existing order
- **Verification**:
  - Adding null order has no effect
  - Order count remains unchanged

### 9.2 Test Execution Results

All tests must pass with the following results:
- ✅ **updateMealOrderTestFunction1**: Success
- ✅ **updateMealOrderTestFunction2**: Success
- ✅ **addMealOrderTestFunction1**: Success
- ✅ **addMealOrderTestFunction2**: Success

### 9.3 Running Tests for Homework Submission

For homework submission with screenshots:

1. **Run standalone tests:**
   ```powershell
   javac -cp "lib/junit-4.13.2.jar;lib/hamcrest-2.2.jar" test/StandaloneMealOrderTest.java
   java -cp "lib/junit-4.13.2.jar;lib/hamcrest-2.2.jar;test" StandaloneMealOrderTest
   ```

2. **Take screenshot** of terminal output showing all tests passed

3. **Syntax highlighting** (optional): Use https://highlight.hohli.com/ to format code for documentation

---

## 10. Technologies Used

- **Programming Language**: Java 8+
- **GUI Framework**: Java Swing
- **Testing Framework**: JUnit 4.13.2
- **Build Tool**: Manual compilation (javac)
- **IDE**: Visual Studio Code / IntelliJ IDEA / Eclipse
- **Version Control**: Git (optional)

**Key Libraries:**
- `javax.swing.*` - GUI components
- `java.awt.*` - Layout managers and graphics
- `java.util.Vector` - Dynamic array for order storage
- `java.util.Iterator` - Collection iteration
- `org.junit.*` - Unit testing

---

## 11. Contributors

**Course**: Software Engineering 1  
**Institution**: Vietnamese - German University  
**Academic Year**: 2025  
**Date**: December 24, 2025

**Project Team**: [Add team member names here]

---

## Appendix: Quick Reference Commands

### Compile Everything
```powershell
javac -cp "lib/*" -d bin src/*.java test/*.java
```

### Run Application
```powershell
java -cp "lib/*;bin" Main
```

### Run Tests (JUnit)
```powershell
java -cp "lib/*;bin" org.junit.runner.JUnitCore MealOrderMgmtTest
```

### Run Tests (Custom Output)
```powershell
java -cp "lib/*;test" StandaloneMealOrderTest
```

### Clean Build
```powershell
Remove-Item -Recurse -Force bin\*
javac -cp "lib/*" -d bin src/*.java
```

--- 
