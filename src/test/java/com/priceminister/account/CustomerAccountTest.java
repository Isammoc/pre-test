package com.priceminister.account;

import static org.junit.Assert.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.priceminister.account.implementation.CustomerAccount;
import com.priceminister.account.implementation.CustomerAccountRule;

/**
 * Please create the business code, starting from the unit tests below.
 * Implement the first test, the develop the code that makes it pass. Then focus
 * on the second test, and so on.
 * 
 * We want to see how you "think code", and how you organize and structure a
 * simple application.
 * 
 * When you are done, please zip the whole project (incl. source-code) and send
 * it to recrutement-dev@priceminister.com
 * 
 */
@RunWith(MockitoJUnitRunner.class)
public class CustomerAccountTest {

	private static final double EPSILON = 0.0001;

	private Account customerAccount;

	@Mock
	private AccountRule rule;

	@Before
	public void setUp() throws Exception {
		customerAccount = new CustomerAccount();
	}

	/**
	 * Tests that an empty account always has a balance of 0.0, not a NULL.
	 */
	@Test
	public void testAccountWithoutMoneyHasZeroBalance() {
		// given see setUp

		// when
		Double actual = customerAccount.getBalance();

		// then
		assertEquals(0.0, actual, EPSILON);
	}

	/**
	 * Adds money to the account and checks that the new balance is as expected.
	 */
	@Test
	public void testAddPositiveAmount() {
		// given see setUp
		double givenAmount = 123.45;

		// when
		customerAccount.add(givenAmount);

		// then
		assertEquals(givenAmount, customerAccount.getBalance(), EPSILON);
	}

	@Test
	public void testAddMultiplePositiveAmounts() {
		// given see SetUp
		double firstAmount = 123.45;
		double secondAmount = 234.56;

		// when
		customerAccount.add(firstAmount);
		customerAccount.add(secondAmount);

		// then
		assertEquals(firstAmount + secondAmount, customerAccount.getBalance(), EPSILON);
	}

	@Test(expected = NullPointerException.class)
	public void testAddNullAmount() {
		// given see setUp

		// when
		customerAccount.add(null);

		// then see expected
	}

	@Test(expected = IllegalArgumentException.class)
	public void testAddNegativeAmount() {
		// given see setUp
		double negativeAmount = -123.45;

		// when
		customerAccount.add(negativeAmount);

		// then see expected
	}

	/**
	 * Tests that an illegal withdrawal throws the expected exception. Use the logic
	 * contained in CustomerAccountRule; feel free to refactor the existing code.
	 */
	@Test(expected = IllegalBalanceException.class)
	public void testWithdrawAndReportBalanceIllegalBalance() throws Exception {
		// given see setUp
		double givenAmount = 123.45;
		customerAccount.add(givenAmount);

		// when
		customerAccount.withdrawAndReportBalance(456.78, rule);

		// then see expected
	}

	@Test
	public void testWithdrawNominal() throws Exception {
		// given see setUp
		double givenAmount = 123.45;
		customerAccount.add(givenAmount);
		rule = new CustomerAccountRule();

		// when
		Double result = customerAccount.withdrawAndReportBalance(23.45, rule);

		// then
		assertEquals(100.00, result, EPSILON);
		assertEquals(100.00, customerAccount.getBalance(), EPSILON);
	}

	@Test
	public void testWithdrawFollowRuleDenying() throws Exception {
		// given see setUp
		when(rule.withdrawPermitted(any(Double.class))).thenReturn(false);

		// when
		try {
			customerAccount.withdrawAndReportBalance(12.45, rule);
		} catch (IllegalBalanceException expected) {
			// then
			assertEquals(-12.45, expected.getIllegalBalance(), EPSILON);
		}
	}

	@Test
	public void testWithdrawFollowRuleAccepting() throws Exception {
		// given see setUp
		when(rule.withdrawPermitted(any(Double.class))).thenReturn(true);

		// when
		customerAccount.withdrawAndReportBalance(12.45, rule);

		// then
		assertEquals(-12.45, customerAccount.getBalance(), EPSILON);
	}

	@Test
	public void testWithdrawFollowRuleDenyingWithPreviousBalance() throws Exception {
		// given see setUp
		customerAccount.add(123.45);
		when(rule.withdrawPermitted(eq(111.0))).thenReturn(false);

		// when
		try {
			customerAccount.withdrawAndReportBalance(12.45, rule);
		} catch (IllegalBalanceException expected) {
			// then
			assertEquals(111.00, expected.getIllegalBalance(), EPSILON);
		}
	}
}
