package com.cg.ba.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class TransactionDaoImplTest {
	static TransactionDaoImpl td;
	@BeforeAll
	public static void init() {
		td = new TransactionDaoImpl();
	}
	@Test
	void testWithdraw() {
		assertEquals(1000, td.withdraw(1000, 300, 1300));
	}

	@Test
	void testDeposit() {
		assertEquals(1500, td.deposit(1002, 300, 1200));
	}

	@Test
	void testShowBalance() {
		assertEquals(1200, td.showBalance(1003));
	}

	@Test
	void testFundTransfer() {
		assertEquals(1, td.fundTransfer(1003, 1002, 100));
	}

}
