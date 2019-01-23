package com.priceminister.account.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.IllegalBalanceException;

public class CustomerAccount implements Account {

	private static final double INITIAL_BALANCE = 0.0;

	private Double balance = INITIAL_BALANCE;

	public void add(Double addedAmount) {
		this.balance = addedAmount;
	}

	public Double getBalance() {
		return this.balance;
	}

	public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) throws IllegalBalanceException {
		throw new IllegalBalanceException(0.0);
	}

}
