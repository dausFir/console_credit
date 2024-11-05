package com.credit.console.model;

public class LoanCalculation {

	private String vehicleType;
    private String condition;
    private int year;
    private double loanAmount;
    private int tenor;
    private double downPayment;
    private double monthlyInstallment;

    public LoanCalculation(String vehicleType, String condition, int year, double loanAmount, int tenor, double downPayment, double monthlyInstallment) {
        this.vehicleType = vehicleType;
        this.condition = condition;
        this.year = year;
        this.loanAmount = loanAmount;
        this.tenor = tenor;
        this.downPayment = downPayment;
        this.monthlyInstallment = monthlyInstallment;
    }

    public String getVehicleType() {
        return vehicleType;
    }

    public String getCondition() {
        return condition;
    }

    public int getYear() {
        return year;
    }

    public double getLoanAmount() {
        return loanAmount;
    }

    public int getTenor() {
        return tenor;
    }

    public double getDownPayment() {
        return downPayment;
    }

    public double getMonthlyInstallment() {
        return monthlyInstallment;
    }
}
