package org.example.models;

public class MetroCard {
    private double balance;
    private String number;
    private int tripCount;

    public MetroCard(String number, int balance) {
        this.balance = balance;
        this.number = number;
        this.tripCount = 0;
    }

    public void addBalance(double amount) {
        this.balance += amount;
    }

    public void swipeCard(double amount) {
        this.balance -= amount;
        this.tripCount++;
        System.out.println("Card swiped for: " + amount);
    }

    public int getTripCount() {
        return tripCount;
    }

    public double getBalance() {
        return balance;
    }
}
