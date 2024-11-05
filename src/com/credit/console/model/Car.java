package com.credit.console.model;

public class Car extends Vehicle{
	
	public Car(double loanAmount, int year, double downPayment, int tenor) {
        super(loanAmount, year, downPayment, tenor);
        this.initialInterestRate = 0.08; // 8%
    }

    @Override
    public double calculateMonthlyInstallment() {
        double principal = loanAmount - downPayment;
        double annualInterestRate = initialInterestRate + 0.001 * (tenor - 1); // Interest adjustment
        double monthlyInterestRate = annualInterestRate / 12;
        int totalPayments = tenor * 12;

        return (principal * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -totalPayments));
    }

    @Override
    public double getInitialInterestRate() {
        return initialInterestRate;
    }

}