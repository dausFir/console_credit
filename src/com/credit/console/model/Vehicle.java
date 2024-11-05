package com.credit.console.model;

public abstract class Vehicle {

	protected double loanAmount;
    protected int year;
    protected double downPayment;
    protected int tenor;
    protected double initialInterestRate;

    public Vehicle(double loanAmount, int year, double downPayment, int tenor) {
        this.loanAmount = loanAmount;
        this.year = year;
        this.downPayment = downPayment;
        this.tenor = tenor;
    }

    public abstract double calculateMonthlyInstallment();

    public abstract double getInitialInterestRate();

	public double getLoanAmount() {
		return loanAmount;
	}

	public void setLoanAmount(double loanAmount) {
		this.loanAmount = loanAmount;
	}

	public int getYear() {
		return year;
	}

	public void setYear(int year) {
		this.year = year;
	}

	public double getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(double downPayment) {
		this.downPayment = downPayment;
	}

	public int getTenor() {
		return tenor;
	}

	public void setTenor(int tenor) {
		this.tenor = tenor;
	}

	public void setInitialInterestRate(double initialInterestRate) {
		this.initialInterestRate = initialInterestRate;
	}
    
    
    
}
