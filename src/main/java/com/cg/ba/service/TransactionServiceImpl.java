package com.cg.ba.service;

import com.cg.ba.dao.TransactionDao;
import com.cg.ba.dao.TransactionDaoImpl;

public class TransactionServiceImpl implements TransactionService {
	TransactionDao td = new TransactionDaoImpl();
	public int withdraw(int accountNo, int amount, int balance) {
		// TODO Auto-generated method stub
		
		return td.withdraw(accountNo, amount, balance);
	}

	public int deposit(int accountNo, int amount, int balance) {
		// TODO Auto-generated method stub
		return td.deposit(accountNo, amount, balance);
	}

	public int showBalance(int accountNo) {
		// TODO Auto-generated method stub
		return td.showBalance(accountNo);
	}

	public int fundTransfer(int fromAccountNo, int toaccountNo, int amount) {
		// TODO Auto-generated method stub
		return td.fundTransfer(fromAccountNo, toaccountNo, amount);
	}
	

}
