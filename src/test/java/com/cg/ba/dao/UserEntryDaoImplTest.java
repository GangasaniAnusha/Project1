package com.cg.ba.dao;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import com.cg.ba.model.CustomerDetails;

class UserEntryDaoImplTest {
	static UserEntryDaoImpl user;
	static CustomerDetails cd;
	@BeforeAll
	public static void init()
	{
		user = new UserEntryDaoImpl();
		cd = new CustomerDetails();
	}
	@Test
	void testRegister() {
		cd.setFirstName("Divya");
		cd.setLastName("Shree");
		cd.setEmailId("divya");
		cd.setPassword("divya");
		cd.setPancardNo(1234345L);
		cd.setAadharNo("234567890123");
		cd.setAddress("hyd");
		cd.setMobileNo("9160749833");
		cd.setBalance(0);
		int id = user.register(cd);
		assertEquals(1004, id);
	}

	@Test
	void testLogin() {
		cd = user.login(1002, "shruthi");
		assertEquals(1002, cd.getAccountNo());
	}

}
