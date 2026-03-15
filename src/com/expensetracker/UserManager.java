package com.expensetracker;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLIntegrityConstraintViolationException;

public class UserManager {

    // Register User
    public boolean registerUser(User user) {

        try {
            Connection con = DBConnection.getConnection();

            String query = "INSERT INTO users(username,password) VALUES(?,?)";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, user.getUsername());
            String hashedPassword = PasswordUtil.hashPassword(user.getPassword());
            ps.setString(2, hashedPassword);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Registration Successful!");
                return true;
            }

        } 
        catch (SQLIntegrityConstraintViolationException e) {
            System.out.println("Username already exists. Try another username.");
        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return false;
    }

    // Login User
    public User loginUser(String username, String password) {

        try {
            Connection con = DBConnection.getConnection();

            String query = "SELECT * FROM users WHERE username=? AND password=?";

            PreparedStatement ps = con.prepareStatement(query);

            ps.setString(1, username);
            String hashedPassword = PasswordUtil.hashPassword(password);
            ps.setString(2, hashedPassword);

            ResultSet rs = ps.executeQuery();

            if (rs.next()) {

                int id = rs.getInt("id");
                String uname = rs.getString("username");
                String pass = rs.getString("password");

                User user = new User(id, uname, pass);


                return user;
            } 
            else {
                System.out.println("Invalid Username or Password.");
            }

        } 
        catch (Exception e) {
            e.printStackTrace();
        }

        return null;
    }

    // Change Password
    public void changePassword(int userId, String newPassword) {

        try {
            Connection con = DBConnection.getConnection();

            String query = "UPDATE users SET password=? WHERE id=?";

            PreparedStatement ps = con.prepareStatement(query);

            String hashedPassword = PasswordUtil.hashPassword(newPassword);
            ps.setString(1, hashedPassword);
            ps.setInt(2, userId);

            int rows = ps.executeUpdate();

            if (rows > 0) {
                System.out.println("Password Updated Successfully!");
            } 
            else {
                System.out.println("Password Update Failed.");
            }

        } 
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
