package com.javapoc.service;

public interface ReviewService {

	public String ReviewValuesFromDB(String input);
	public String showValuesFromDB(String input);
	public String approveValuesInDB(String input);
	public String rejectValuesInDB(String input);
	public String modifyValuesInDB(String input);

}
