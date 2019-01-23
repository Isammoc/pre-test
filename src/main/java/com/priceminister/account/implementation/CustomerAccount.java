package com.priceminister.account.implementation;

import com.priceminister.account.Account;
import com.priceminister.account.AccountRule;
import com.priceminister.account.IllegalBalanceException;

public class CustomerAccount implements Account {

	private static final double INITIAL_BALANCE = 0.0;

	private Double balance = INITIAL_BALANCE;

	public void add(Double addedAmount) {
		if (addedAmount < 0) {
			throw new IllegalArgumentException("Cannot add negative amount (got '" + addedAmount + "')");
		}
		this.balance += addedAmount;
	}

	public Double getBalance() {
		return this.balance;
	}

	public Double withdrawAndReportBalance(Double withdrawnAmount, AccountRule rule) throws IllegalBalanceException {
		if (!rule.withdrawPermitted(INITIAL_BALANCE)) {
			throw new IllegalBalanceException(this.balance - withdrawnAmount);
		}
		this.balance -= withdrawnAmount;
		return this.balance;
	}

}
