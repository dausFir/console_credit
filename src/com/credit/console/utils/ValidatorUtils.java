package com.credit.console.utils;

import java.util.Calendar;

public class ValidatorUtils {

	public static final double CAR_INTEREST_RATE = 0.08; // 8%
    public static final double MOTOR_INTEREST_RATE = 0.09; // 9%

    public static boolean isValidVehicleType(String vehicleType) {
        return "Motor".equalsIgnoreCase(vehicleType) || "Car".equalsIgnoreCase(vehicleType);
    }

    public static boolean isValidCondition(String condition) {
        return "New".equalsIgnoreCase(condition) || "Used".equalsIgnoreCase(condition);
    }

    public static boolean isValidYear(int year, boolean isNew) {
        int currentYear = Calendar.getInstance().get(Calendar.YEAR);
        return isNew ? year >= currentYear - 1 : year >= 1886; // 1886 is the year the first car was invented
    }

    public static boolean isValidLoanAmount(double loanAmount) {
        return loanAmount > 0 && loanAmount <= 1_000_000_000; // Up to 1 billion
    }

    public static boolean isValidTenor(int tenor) {
        return tenor >= 1 && tenor <= 6; // 1 to 6 years
    }

    public static boolean isValidDownPayment(double downPayment, double loanAmount, boolean isNew) {
        double requiredPercentage = isNew ? 0.35 : 0.25; // 35% for new, 25% for used
        return downPayment >= requiredPercentage * loanAmount; // Check if down payment meets the requirement
    }

    public static boolean validateInputs(String vehicleType, String condition, int year, double loanAmount, int tenor, double downPayment) {
        boolean isNew = "New".equalsIgnoreCase(condition);
        
        if (!isValidVehicleType(vehicleType)) {
            System.out.println("Invalid vehicle type. Must be 'Motor' or 'Car'.");
            return false;
        }
        if (!isValidCondition(condition)) {
            System.out.println("Invalid condition. Must be 'New' or 'Used'.");
            return false;
        }
        if (!isValidYear(year, isNew)) {
            System.out.println("Invalid year. New vehicles must be less than the current year.");
            return false;
        }
        if (!isValidLoanAmount(loanAmount)) {
            System.out.println("Invalid loan amount. Must be greater than 0 and less than or equal to 1 billion.");
            return false;
        }
        if (!isValidTenor(tenor)) {
            System.out.println("Invalid tenor. Must be between 1 and 6 years.");
            return false;
        }
        if (!isValidDownPayment(downPayment, loanAmount, isNew)) {
            System.out.println("Invalid down payment. Must be at least " + (isNew ? "35%" : "25%") + " of the loan amount.");
            return false;
        }
        
        return true; // All validations passed
    }
}
