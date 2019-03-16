package com.cg.ba.utility;

public class InvalidIdException extends Exception {
	public InvalidIdException()
	{
		System.out.println("Account No u have choosen to transfer money doesn't exists");
	}
}
