package com.credit.console.controller;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.credit.console.model.LoanCalculation;
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
    
    // Map to hold previous calculations with a unique key (e.g., a timestamp)
    private Map<Integer, LoanCalculation> previousCalculations;
    private Map<Integer, LoanCalculation> savedSheets; // Map for saved sheets
    private int calculationCounter; // Counter to generate unique keys

    public LoanController(LoanView loanView) {
        this.loanView = loanView;
        this.previousCalculations = new HashMap<>();
        this.savedSheets = new HashMap<>(); // Initialize saved sheets map
        this.calculationCounter = 1; // Start counter at 1
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

        return (principal * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -tenor * 12));
    }

    private void loadExistingCalculation() {
    	// Prompt user to choose which calculations to load
        int loadChoice = loanView.chooseLoadOption();

        if (loadChoice == 1) { // Load previous calculations
            if (previousCalculations.isEmpty()) {
                loanView.displayMessage("No previous calculations found.");
                return;
            }

            // Display previous calculations
            loanView.displayMessage("Previous Loan Calculations:");
            for (Map.Entry<Integer, LoanCalculation> entry : previousCalculations.entrySet()) {
                Integer key = entry.getKey();
                LoanCalculation calculation = entry.getValue();
                loanView.displayPreviousCalculation(key, calculation);
            }

            // Ask user to select a calculation to load
            int index = loanView.getCalculationIndex(previousCalculations.size());
            if (previousCalculations.containsKey(index)) {
                LoanCalculation selectedCalculation = previousCalculations.get(index);
                loanView.displayLoadedCalculation(selectedCalculation);
            } else {
                loanView.displayMessage("Invalid selection. Please try again.");
            }
        } else if (loadChoice == 2) { // Load saved sheets
            if (savedSheets.isEmpty()) {
                loanView.displayMessage("No saved sheets found.");
                return;
            }

            // Display saved sheets
            loanView.displayMessage("Saved Sheets:");
            for (Map.Entry<Integer, LoanCalculation> entry : savedSheets.entrySet()) {
                Integer key = entry.getKey();
                LoanCalculation calculation = entry.getValue();
                loanView.displaySavedSheet(key, calculation);
            }

            // Ask user to select a saved sheet to load
            int index = loanView.getCalculationIndex(savedSheets.size());
            if (savedSheets.containsKey(index)) {
                LoanCalculation selectedCalculation = savedSheets.get(index);
                loanView.displayLoadedCalculation(selectedCalculation);
            } else {
                loanView.displayMessage("Invalid selection. Please try again.");
            }
        } else {
            loanView.displayMessage("Invalid option. Please try again.");
        }
    }

    private void switchSheets() {
        loanView.switchSheets();
    }
    
    private void saveCalculation() {
        LoanCalculation newCalculation = new LoanCalculation(vehicleType, condition, year, loanAmount, tenor, downPayment, calculateMonthlyInstallment());
        previousCalculations.put(calculationCounter++, newCalculation); 
    }

    private void saveSheet() {
        savedSheets.put(calculationCounter, previousCalculations.get(calculationCounter - 1)); 
        loanView.displayMessage("Current calculation saved to the sheet with ID: " + calculationCounter);
        calculationCounter++; 
    }
    

    private void displaySavedSheets() {
        if (savedSheets.isEmpty()) {
            loanView.displayMessage("No saved sheets found.");
            return;
        }

        loanView.displayMessage("Saved Sheets:");
        for (Map.Entry<Integer, LoanCalculation> entry : savedSheets.entrySet()) {
            Integer key = entry.getKey();
            LoanCalculation calculation = entry.getValue();
            loanView.displaySavedSheet(key, calculation);
        }
    }
    
    
}
