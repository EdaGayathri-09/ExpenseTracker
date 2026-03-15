package com.expensetracker;

public class Expense {

    private int id;
    private String name;
    private int amount;
    private String category;
    private String date;
    private int userId;

    // Default constructor
    public Expense() {
    }

    // Parameterized constructor
    public Expense(int id, String name, int amount, String category, String date, int userId) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.category = category;
        this.date = date;
        this.userId = userId;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }


    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }


    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }


    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }
}
