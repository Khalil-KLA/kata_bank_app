package com.socgen.kata.bankapp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.socgen.kata.bankapp.Transaction.TRANSACTION_TYPE;

public class Account {
	
	private final String MSG_NEGATIVE_AMOUNT = "Negative Amount !";
	private final String MSG_INSUFFICIENT_CREDIT = "Insufficient credit !";

	private BigDecimal balance = BigDecimal.ZERO;
	private final Statement statement = new Statement();
	
	
	public void deposit(BigDecimal amount, LocalDateTime transactionTime) throws InvalidTransactionException {
		
		if(isNegativeAmount(amount)) {
			throw new InvalidTransactionException(MSG_NEGATIVE_AMOUNT);
		}
		addTransaction(TRANSACTION_TYPE.DEPOSIT, amount, transactionTime);
		
	}

	
	public void withdrawal(BigDecimal amount, LocalDateTime transactionTime) throws InvalidTransactionException {
		
		if(isNegativeAmount(amount)) {
			throw new InvalidTransactionException(MSG_NEGATIVE_AMOUNT);
		}
		
		if(isInsufficientCredit(amount)) {
			throw new InvalidTransactionException(MSG_INSUFFICIENT_CREDIT);
		}
		
		addTransaction(TRANSACTION_TYPE.WITHDRAWAL, amount, transactionTime);

	}

	public void printStatement() {
		statement.print();
	}

	private void addTransaction(TRANSACTION_TYPE transactionType, BigDecimal amount, LocalDateTime transactionTime) {

		Transaction transaction = new Transaction(transactionType, amount, transactionTime);
		this.balance = transaction.getBalanceAfter(this.balance);
		statement.addStatementLine(transaction, this.balance);

	}

	public BigDecimal getBalance() {
		return balance;
	}
	
	private boolean isNegativeAmount (BigDecimal amount) {
		
		return BigDecimal.ZERO.compareTo(amount) > 0;
	}
	
	private boolean isInsufficientCredit (BigDecimal amount) {
		
		return balance.compareTo(amount) < 0;
	}
}
