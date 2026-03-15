package com.expensetracker;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        UserManager userManager = new UserManager();
        ExpenseManager expenseManager = new ExpenseManager();

        while (true) {

            System.out.println("\n===== Expense Tracker =====");
            System.out.println("1. Register");
            System.out.println("2. Login");
            System.out.println("3. Exit");
            System.out.print("Enter choice: ");

            if (!sc.hasNextInt()) {
                System.out.println("Invalid input. Please enter a number.");
                sc.nextLine();
                continue;
            }

            int choice = sc.nextInt();

            switch (choice) {

                case 1:

                    sc.nextLine();

                    System.out.print("Enter username: ");
                    String username = sc.nextLine();

                    System.out.print("Enter password: ");
                    String password = sc.nextLine();

                    User newUser = new User();
                    newUser.setUsername(username);
                    newUser.setPassword(password);

                    userManager.registerUser(newUser);

                    break;

                case 2:

                    sc.nextLine();

                    System.out.print("Enter username: ");
                    String u = sc.nextLine();

                    System.out.print("Enter password: ");
                    String p = sc.nextLine();

                    User loginUser = userManager.loginUser(u, p);

                    if (loginUser != null) {

                        int userId = loginUser.getId();

                        System.out.println("Login Successful!");

                        while (true) {

                            System.out.println("\n===== Expense Menu =====");
                            System.out.println("1. Add Expense");
                            System.out.println("2. View Expenses");
                            System.out.println("3. Delete Expense");
                            System.out.println("4. Category Report");
                            System.out.println("5. Change Password");
                            System.out.println("6. Logout");
                            System.out.print("Enter choice: ");

                            if (!sc.hasNextInt()) {
                                System.out.println("Invalid input. Enter a number.");
                                sc.nextLine();
                                continue;
                            }

                            int ch = sc.nextInt();

                            switch (ch) {

                                case 1:

                                    sc.nextLine();

                                    Expense expense = new Expense();

                                    // NAME
                                    System.out.print("Enter expense name (0 to cancel): ");
                                    String name = sc.nextLine();

                                    if (name.equals("0")) {
                                        System.out.println("Expense entry cancelled.");
                                        break;
                                    }

                                    expense.setName(name);

                                    // AMOUNT
                                    System.out.print("Enter amount (0 to cancel): ");

                                    if (!sc.hasNextInt()) {
                                        System.out.println("Invalid amount entered.");
                                        sc.nextLine();
                                        break;
                                    }

                                    int amount = sc.nextInt();

                                    if (amount == 0) {
                                        System.out.println("Expense entry cancelled.");
                                        break;
                                    }

                                    expense.setAmount(amount);

                                    sc.nextLine();

                                    // CATEGORY
                                    System.out.print("Enter category (0 to cancel): ");
                                    String category = sc.nextLine();

                                    if (category.equals("0")) {
                                        System.out.println("Expense entry cancelled.");
                                        break;
                                    }

                                    expense.setCategory(category);

                                    // DATE
                                    System.out.print("Enter date (YYYY-MM-DD) (0 to cancel): ");
                                    String date = sc.nextLine();

                                    if (date.equals("0")) {
                                        System.out.println("Expense entry cancelled.");
                                        break;
                                    }

                                    if (!date.matches("\\d{4}-\\d{2}-\\d{2}")) {
                                        System.out.println("Invalid date format. Use YYYY-MM-DD.");
                                        break;
                                    }

                                    expense.setDate(date);

                                    expense.setUserId(userId);

                                    expenseManager.addExpense(expense);

                                    break;

                                case 2:

                                    expenseManager.viewExpenses(userId);
                                    break;

                                case 3:

                                	expenseManager.viewExpenses(userId);

                                    System.out.print("Enter expense ID to delete (0 to cancel): ");

                                    if (!sc.hasNextInt()) {
                                        System.out.println("Invalid ID.");
                                        sc.nextLine();
                                        break;
                                    }

                                    int id = sc.nextInt();

                                    if (id == 0) {
                                        System.out.println("Delete operation cancelled.");
                                        break;
                                    }

                                    expenseManager.deleteExpense(id, userId);

                                    break;

                                case 4:

                                    expenseManager.categoryReport(userId);
                                    break;

                                case 5:

                                    sc.nextLine();

                                    System.out.print("Enter new password: ");
                                    String newPassword = sc.nextLine();

                                    userManager.changePassword(userId, newPassword);

                                    break;

                                case 6:

                                    System.out.println("Logged out successfully.");
                                    break;

                                default:

                                    System.out.println("Invalid choice");
                            }

                            if (ch == 6)
                                break;
                        }

                    } else {

                        System.out.println("Invalid username or password");

                    }

                    break;

                case 3:

                    System.out.println("Exiting program...");
                    System.exit(0);

                default:

                    System.out.println("Invalid choice");
            }
        }
    }
}