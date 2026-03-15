package com.expensetracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

public class ExpenseManager {

    // Add Expense
    public void addExpense(Expense expense){

        try{

            Connection conn = DBConnection.getConnection();

            String query = "INSERT INTO expenses(name,amount,category,date,user_id) VALUES(?,?,?,?,?)";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setString(1, expense.getName());
            ps.setInt(2, expense.getAmount());
            ps.setString(3, expense.getCategory());
            ps.setString(4, expense.getDate());
            ps.setInt(5, expense.getUserId());

            ps.executeUpdate();

            System.out.println("Expense Added Successfully");

        }catch(Exception e){
            e.printStackTrace();
        }

    }

    // View Expenses
    public void viewExpenses(int userId) {

        try {

            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM expenses WHERE user_id=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            int total = 0;
            int maxAmount = 0;
            String maxExpenseName = "";

            System.out.println("\nID  Name  Amount  Category  Date");

            while (rs.next()) {

                int id = rs.getInt("id");
                String name = rs.getString("name");
                int amount = rs.getInt("amount");
                String category = rs.getString("category");
                String date = rs.getString("date");

                System.out.println(id + "  " + name + "  " + amount + "  " + category + "  " + date);

                total += amount;

                if (amount > maxAmount) {
                    maxAmount = amount;
                    maxExpenseName = name;
                }
            }

            System.out.println("\n----- Expense Summary -----");
            System.out.println("Total Expenses: ₹" + total);

            if (maxAmount > 0) {
                System.out.println("Highest Expense: " + maxExpenseName + " (₹" + maxAmount + ")");
            } else {
                System.out.println("No expenses found.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Delete Expense
    public void deleteExpense(int expenseId, int userId) {

        try {
            Connection conn = DBConnection.getConnection();

            String query = "DELETE FROM expenses WHERE id = ? AND user_id = ?";
            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, expenseId);
            ps.setInt(2, userId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Expense deleted successfully.");
            } else {
                System.out.println("Expense not found or you are not authorized to delete it.");
            }

        } catch (Exception e) {
            e.printStackTrace();
        }
    }



    // Category Report
    public void categoryReport(int userId){

        try{

            Connection conn = DBConnection.getConnection();

            String query =
                    "SELECT category, SUM(amount) as total FROM expenses WHERE user_id=? GROUP BY category";

            PreparedStatement ps = conn.prepareStatement(query);

            ps.setInt(1, userId);

            ResultSet rs = ps.executeQuery();

            System.out.println("\nCategory Wise Expense Report");

            while(rs.next()){

                System.out.println(
                        rs.getString("category") + " : " +
                        rs.getInt("total")
                );

            }

        }catch(Exception e){
            e.printStackTrace();
        }

    }

}
