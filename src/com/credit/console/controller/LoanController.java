package com.credit.console.controller;

import com.credit.console.utils.ValidatorUtils;
import com.credit.console.view.LoanView;

public class LoanController {
	private LoanView loanView;
    private double loanAmount;
    private String vehicleType;
    private String condition;
    private int year;
    private int tenor;
    private double downPayment;

    public LoanController(LoanView loanView) {
        this.loanView = loanView;
    }

    public void start() {
        boolean running = true;
        while (running) {
            int choice = loanView.displayMenu();
            switch (choice) {
                case 1:
                    collectLoanDetails();
                    if (ValidatorUtils.validateInputs(vehicleType, condition, year, loanAmount, tenor, downPayment)) {
                        double monthlyInstallment = calculateMonthlyInstallment();
                        loanView.displayMonthlyInstallments(monthlyInstallment, tenor, vehicleType);
                    }
                    break;
                case 2:
                    loadExistingCalculation();
                    break;
                case 3:
                    switchSheets();
                    break;
                case 4:
                    saveSheet();
                    break;
                case 5:
                    running = false;
                    break;
                default:
                    loanView.displayMessage("Invalid option. Please try again.");
            }
        }
    }

    private void collectLoanDetails() {
        vehicleType = loanView.getVehicleType();
        condition = loanView.getCondition();
        year = loanView.getYear();
        loanAmount = loanView.getLoanAmount();
        tenor = loanView.getTenor();
        downPayment = loanView.getDownPayment();
    }

    private double calculateMonthlyInstallment() {
        double interestRate = vehicleType.equalsIgnoreCase("Car") ? ValidatorUtils.CAR_INTEREST_RATE : ValidatorUtils.MOTOR_INTEREST_RATE;
        double principal = loanAmount - downPayment;
        double monthlyInterestRate = interestRate / 12;

        // Calculate the monthly payment using the formula for an annuity
        return (principal * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -tenor * 12));
    }

    private void loadExistingCalculation() {
    	
    }

    private void switchSheets() {
        loanView.switchSheets();
    }

    private void saveSheet() {
        loanView.saveSheet();
    }
}
