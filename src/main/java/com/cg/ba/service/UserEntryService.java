package com.cg.ba.service;

import com.cg.ba.model.CustomerDetails;

public interface UserEntryService {
	int register(CustomerDetails cd);
	boolean isAadharNoCorrect(String aadharNo);
	boolean isMobileNoCorrect(String mobileNo);
	CustomerDetails login(int id,String password);
}
