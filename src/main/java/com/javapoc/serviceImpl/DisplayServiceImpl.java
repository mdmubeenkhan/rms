package com.javapoc.serviceImpl;

import com.javapoc.dao.DisplayDao;
import com.javapoc.daoImpl.DisplayDaoImpl;
import com.javapoc.service.DisplayService;

public class DisplayServiceImpl implements DisplayService{
	
	public String DisplayValuesFromDB() {
		
		DisplayDao ddi = new DisplayDaoImpl();
        String records = null;
    	records = ddi.getDisplayData();
		return records;
		
	}


}
