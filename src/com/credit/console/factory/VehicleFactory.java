package com.credit.console.factory;

import com.credit.console.model.Car;
import com.credit.console.model.Motor;
import com.credit.console.model.Vehicle;

public class VehicleFactory {

	 public static Vehicle createVehicle(String vehicleType, double loanAmount, int year, double downPayment, int tenor) {
	    
		if ("Car".equalsIgnoreCase(vehicleType)) {
	        return new Car(loanAmount, year, downPayment, tenor);
	    } else if ("Motor".equalsIgnoreCase(vehicleType)) {
	        return new Motor(loanAmount, year, downPayment, tenor);
	    }
	    return null;
	    
	}
}
