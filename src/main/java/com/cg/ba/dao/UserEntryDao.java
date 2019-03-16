package com.cg.ba.dao;

import com.cg.ba.model.CustomerDetails;

public interface UserEntryDao {
	int register(CustomerDetails cd);
	CustomerDetails login(int id,String password);
}
