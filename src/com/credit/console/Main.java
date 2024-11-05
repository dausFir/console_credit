package com.credit.console;

import com.credit.console.controller.LoanController;
import com.credit.console.view.LoanView;

public class Main {
	public static void main(String[] args) {
		LoanView loanView = new LoanView();
        LoanController loanController = new LoanController(loanView);
        loanController.start(); 
    }
}
