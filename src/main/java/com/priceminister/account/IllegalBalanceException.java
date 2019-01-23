package com.priceminister.account;

public class IllegalBalanceException extends Exception {

	private static final long serialVersionUID = -9204191749972551939L;

	private final Double balance;

	public IllegalBalanceException(Double illegalBalance) {
		super("Illegal account balance: " + illegalBalance);
		balance = illegalBalance;
	}

	public Double getIllegalBalance() {
		return balance;
	}
}
