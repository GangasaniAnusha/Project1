package com.cg.ba.utility;

public class InvalidAadhaarNoException extends Exception {
	public InvalidAadhaarNoException()
	{
		System.out.println("Aadhaar No should have 12 digits");
	}
}
