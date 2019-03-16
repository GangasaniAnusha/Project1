package com.cg.ba.utility;

public class InvalidMobileNoException extends Exception {
	public InvalidMobileNoException()
	{
		System.out.println("Mobile No should be 10 digits");
	}
}
