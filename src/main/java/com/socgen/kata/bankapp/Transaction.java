package com.socgen.kata.bankapp;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class Transaction {

	enum TRANSACTION_TYPE {
		DEPOSIT(1), WITHDRAWAL(-1);
       
		private final Integer coefficient;
		
		TRANSACTION_TYPE(Integer coefficient) {
			this.coefficient = coefficient;
		}
		
		public Integer getCoefficient () {
			return coefficient;
		}
	}

	private final TRANSACTION_TYPE type;
	private final BigDecimal amount;
	private final LocalDateTime transactionTime;

	public Transaction(TRANSACTION_TYPE type, BigDecimal amount, LocalDateTime transactionTime) {
		this.type = type;
		this.amount = amount;
		this.transactionTime = transactionTime;

	}

	public BigDecimal getBalanceAfter(BigDecimal balanceBefore) {


        BigDecimal amountWithCoefficient  = amount.multiply(BigDecimal.valueOf(type.getCoefficient()));

		return balanceBefore.add(amountWithCoefficient);
	}

	public TRANSACTION_TYPE getType() {
		return type;
	}

	public BigDecimal getAmount() {
		return amount;
	}

	public LocalDateTime getTransactionTime() {
		return transactionTime;
	}



}
