package com.socgen.kata.bankapp;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class AccountTest {

	private final ByteArrayOutputStream outContent = new ByteArrayOutputStream();
	private final PrintStream originalOut = System.out;
	private final Account account = new Account();

	@Test
	void test_Deposit() throws InvalidTransactionException {
		
		account.deposit(new BigDecimal(1000), LocalDateTime.of(2022, 10, 11, 11, 0));
		Assertions.assertEquals(new BigDecimal(1000), account.getBalance());
	}

	@Test
	void test_Withdrawal() throws InvalidTransactionException {
		
		account.deposit(new BigDecimal(1000), LocalDateTime.of(2022, 10, 11, 10, 0));
		account.withdrawal(new BigDecimal(200), LocalDateTime.of(2022, 10, 12, 10, 15));
		Assertions.assertEquals(new BigDecimal(800), account.getBalance());
	
	}

	@Test
	void test_PrintStatement() throws InvalidTransactionException {

		account.deposit(new BigDecimal(1000), LocalDateTime.of(2022, 10, 11, 9, 0));

		String result = "TRX_TYPE|DATE|AMOUNT|BALANCE" + System.getProperty("line.separator") +
				"DEPOSIT|2022-10-11|1000|1000" + System.getProperty("line.separator");

		// Changer la sortie standard
		System.setOut(new PrintStream(outContent));

		account.printStatement();

		System.setOut(originalOut);

		Assertions.assertEquals(result, outContent.toString());

	}

	@Test
	void test_Deposit_Negative_Amount() {

		Assertions.assertThrows(InvalidTransactionException.class,
				() -> account.deposit(new BigDecimal(-1000), LocalDateTime.of(2022, 10, 11, 7, 0)));

	}

	@Test
	void test_Withdrawal_Negative_Amount() {

		Assertions.assertThrows(InvalidTransactionException.class,
				() -> account.withdrawal(new BigDecimal(-1000), LocalDateTime.of(2022, 10, 11, 12, 20)));

	}

	@Test
	void test_Withdrawal_Insufficient_Credit() {

		Assertions.assertThrows(InvalidTransactionException.class,
				() -> account.withdrawal(new BigDecimal(100), LocalDateTime.of(2022, 10, 11, 12, 18)));

	}

}
