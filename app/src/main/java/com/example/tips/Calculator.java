package com.example.tips;

public class Calculator {

    public static double calculateTip(double billAmount, int tipPercent) {
        return billAmount * tipPercent / 100.0;
    }

    public static double calculateTotal(double billAmount, double tipAmount) {
        return billAmount + tipAmount;
    }

    public static double calculatePerPerson(double totalAmount, int peopleCount) {
        return totalAmount / peopleCount;
    }
}