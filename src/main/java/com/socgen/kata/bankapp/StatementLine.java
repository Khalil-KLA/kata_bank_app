package com.socgen.kata.bankapp;

import java.math.BigDecimal;
import java.time.format.DateTimeFormatter;

public class StatementLine {

	private final Transaction transaction;
	private final BigDecimal balanceAfter;
    private final DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

	public StatementLine(Transaction transaction, BigDecimal balanceAfter) {

		this.transaction = transaction;
		this.balanceAfter = balanceAfter;

	}

	@Override
	public String toString() {

		StringBuilder sb = new StringBuilder();
		
		sb.append(transaction.getType());
		sb.append("|");
		sb.append(transaction.getTransactionTime().format(dateFormatter));
		sb.append("|");
		sb.append(transaction.getAmount());
		sb.append("|");
		sb.append(this.balanceAfter);

		return sb.toString();
	}

}
