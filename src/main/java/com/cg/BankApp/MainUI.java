package com.cg.BankApp;

import java.util.Scanner;

import com.cg.ba.model.CustomerDetails;
import com.cg.ba.service.TransactionService;
import com.cg.ba.service.TransactionServiceImpl;
import com.cg.ba.service.UserEntryService;
import com.cg.ba.service.UserEntryServiceImpl;
import com.cg.ba.utility.InvalidAadhaarNoException;
import com.cg.ba.utility.InvalidMobileNoException;

//import com.cg.ba.model.CustomerDetails;

public class MainUI {

	static Scanner scan = new Scanner(System.in);
	static CustomerDetails cd = new CustomerDetails();
	static UserEntryService user = new UserEntryServiceImpl();
	static TransactionService transaction = new TransactionServiceImpl();
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
			System.out.println("Enter '0' to Register and '1' to Login");
			int n = scan.nextInt();
			switch(n)
			{
			case 0: int i = setInfo();
				if(i == 1)
				{
					int accountNo = user.register(cd);
					if(accountNo != 0)
					{
						System.out.println("Your Account No:"+accountNo);
						System.out.println("Please Login to continue");
						loginMain();
					}
				}
				break;
			case 1: loginMain();
				break;
			}
		}
	private static void loginMain() {
		// TODO Auto-generated method stub
		System.out.println("Your Account No");
		int acno = scan.nextInt();
		System.out.println("Your Password");
		String pass = scan.next();
		CustomerDetails cd = user.login(acno, pass);
		if(cd != null)
		options(cd);
		else
		{
			System.out.println("Wrong Credentials");
			loginMain();
		}
			
	}
	private static int setInfo() {
		// TODO Auto-generated method stub
		int i = 0;
		System.out.println("Enter your details"+"\n");
		System.out.println("Your First Name");
		cd.setFirstName(scan.next());
		System.out.println("Your Last Name");
		cd.setLastName(scan.next());
		System.out.println("Your Email Id");
		cd.setEmailId(scan.next());
		System.out.println("Your Pancard No");
		cd.setPancardNo(scan.nextLong());
		System.out.println("Your Aadhaar No");
		String aadharNo = scan.next();
		if(user.isAadharNoCorrect(aadharNo))
		{
			cd.setAadharNo(aadharNo);
			System.out.println("Your Address");
			cd.setAddress(scan.next());
			System.out.println("Your Mobile No");
			String mobileNo = scan.next();
			if(user.isMobileNoCorrect(mobileNo))
			{
				cd.setMobileNo(mobileNo);
				cd.setBalance(0);
				System.out.println("Your Password");
				cd.setPassword(scan.next());
				i++;
			}
			else
			{
				try {
					throw new InvalidMobileNoException();
				} catch (InvalidMobileNoException e) {
					// TODO Auto-generated catch block
				}
			}
		}
		else
		{
			try {
				throw new InvalidAadhaarNoException();
			} catch (InvalidAadhaarNoException e) {
				// TODO Auto-generated catch block
			}
		}
		return i;
	}

	
	private static void options(CustomerDetails u) {
		// TODO Auto-generated method stub
		while(true)
		{
			System.out.println("Options");
			System.out.println("'1' to Withdraw"+"\n"+"'2' to Deposit"+"\n"+ "'3' to check balance"+"\n"+"'4' to fund transfer"+"\n"+"'5' to exit");
			int n = scan.nextInt();
			switch(n)
			{
			case 1: System.out.println("Enter the amount to withdraw");
					int wAmt = scan.nextInt();
					int balance = transaction.withdraw(u.getAccountNo(), wAmt, u.getBalance());
					if(balance != 0)
					{
						System.out.println("Withdraw done");
						u.setBalance(balance);
					}
					else
						System.out.println("Insufficient balance");
					break;
			case 2: System.out.println("Enter the amount to deposit");
					int dAmt = scan.nextInt();
					int balance1 = transaction.deposit(u.getAccountNo(), dAmt, u.getBalance());
					if(balance1 != 0)
					{
						System.out.println("Deposit done");
						u.setBalance(balance1);
					}
					break;
			case 3: int bal = transaction.showBalance(u.getAccountNo());
					System.out.println("Your Balance:"+bal);
					break;
			case 4: System.out.println("Enter the amount to transfer");
					int tAmt = scan.nextInt();
					System.out.println("Enter the account no to which you want to transfer the money");
					int toAccNo = scan.nextInt();
					int details = transaction.fundTransfer(u.getAccountNo(), toAccNo, tAmt);
					if(details != 0)
					System.out.println(tAmt+" Rs/- of money is transferred from "+u.getAccountNo()+" to "+toAccNo);
					break;
			case 5:System.out.println("Thank You");
					System.exit(0);
			default: System.out.println("Give Proper Input");
			}
		}
		
	}

}
