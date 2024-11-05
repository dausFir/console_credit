package com.credit.console.view;

import java.util.Scanner;

import com.credit.console.model.LoanCalculation;
import com.credit.console.utils.ValidatorUtils;

public class LoanView {

	private Scanner scanner;

    public LoanView() {
        this.scanner = new Scanner(System.in);
    }

    public int displayMenu() {
        System.out.println("Menu:");
        System.out.println("1. Calculate Loan");
        System.out.println("2. Load Existing Calculation");
        System.out.println("3. Switch Sheets");
        System.out.println("4. Save Sheet");
        System.out.println("5. Exit");
        System.out.print("Choose an option: ");
        return scanner.nextInt();
    }

    public String getVehicleType() {
        System.out.print("Enter vehicle type (Motor/Car): ");
        return scanner.next();
    }

    public String getCondition() {
        System.out.print("Enter condition (New/Used): ");
        return scanner.next();
    }

    public int getYear() {
        System.out.print("Enter vehicle year (4-digit): ");
        return scanner.nextInt();
    }

    public double getLoanAmount() {
        System.out.print("Enter loan amount (up to 1 billion): ");
        return scanner.nextDouble();
    }

    public int getTenor() {
        System.out.print("Enter tenor (1-6 years): ");
        return scanner.nextInt();
    }

    public double getDownPayment() {
        System.out.print("Enter down payment: ");
        return scanner.nextDouble();
    }

    public void displayMonthlyInstallments(double monthlyInstallment, int tenor, String vehicleType) {
        double interestRate = vehicleType.equalsIgnoreCase("Car") ? ValidatorUtils.CAR_INTEREST_RATE : ValidatorUtils.MOTOR_INTEREST_RATE;
        System.out.printf("Monthly Installment for Year 1: Rp. %.2f, Interest Rate: %.1f%%\n", monthlyInstallment, interestRate * 100);

        for (int year = 2; year <= tenor; year++) {
            interestRate += (year % 2 == 0) ? 0.005 : 0.001; 
            monthlyInstallment *= (1 + (interestRate / 12));
            System.out.printf("Monthly Installment for Year %d: Rp. %.2f, Interest Rate: %.1f%%\n", year, monthlyInstallment, interestRate * 100);
        }
    }

    public void displayMessage(String message) {
        System.out.println(message);
    }

    public void switchSheets() {
	    System.out.print("Enter sheet index to switch to (0-9): ");
	    int index = scanner.nextInt();
	    if (index >= 0 && index < 10) {
	        System.out.println("Switched to sheet " + index);
	    } else {
	        System.out.println("Invalid sheet index.");
	    }
    }

    public void saveSheet() {
        // Save current sheet logic (placeholder)
        System.out.println("Saving current sheet...");
        // Implement your saving logic here
    }

	public void displayPreviousCalculation(int i, LoanCalculation calculation) {
		// TODO Auto-generated method stub
		 System.out.printf("%d. Vehicle Type: %s, Condition: %s, Year: %d, Loan Amount: Rp. %.2f, Tenor: %d years, Down Payment: Rp. %.2f, Monthly Installment: Rp. %.2f\n",
			                i, calculation.getVehicleType(), calculation.getCondition(), calculation.getYear(),
			                calculation.getLoanAmount(), calculation.getTenor(), calculation.getDownPayment(),
			                calculation.getMonthlyInstallment());
	}

	public int getCalculationIndex(int size) {
		// TODO Auto-generated method stub
		  System.out.print("Select a calculation to load (1-" + size + "): ");
	      return scanner.nextInt();
	}

	public void displayLoadedCalculation(LoanCalculation calculation) {
		// TODO Auto-generated method stub
		System.out.printf("Loaded Calculation: Vehicle Type: %s, Condition: %s, Year: %d, Loan Amount: Rp. %.2f, Tenor: %d years, Down Payment: Rp. %.2f, Monthly Installment: Rp. %.2f\n",
                calculation.getVehicleType(), calculation.getCondition(), calculation.getYear(),
                calculation.getLoanAmount(), calculation.getTenor(), calculation.getDownPayment(),
                calculation.getMonthlyInstallment());
		
	}
}
