package com.socgen.kata.bankapp;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class Statement {

	private final List<StatementLine> statement = new ArrayList<>();
	


	public void addStatementLine(Transaction transaction, BigDecimal balanceAfter) {

		StatementLine statementLine = new StatementLine(transaction, balanceAfter);
		statement.add(statementLine);
	}

	public void print() {
		String header = "TRX_TYPE|DATE|AMOUNT|BALANCE";
		System.out.println(header);
		statement.forEach(System.out::println);
	}

}
