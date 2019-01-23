package com.priceminister.account.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.IllegalBalanceException;

public class CustomerAccount implements Account {

	private static final double INITIAL_BALANCE = 0.0;

	public void add(Double addedAmount) {
		// TODO Auto-generated method stub
	}

	public Double getBalance() {
		return INITIAL_BALANCE;
	}

	public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) throws IllegalBalanceException {
		// TODO Auto-generated method stub
		return null;
	}

}
