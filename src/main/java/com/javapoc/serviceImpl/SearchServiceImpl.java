package com.javapoc.serviceImpl;

import com.javapoc.dao.SearchDao;
import com.javapoc.daoImpl.SearchDaoImpl;
import com.javapoc.service.SearchService;

public class SearchServiceImpl implements SearchService{

	public String SearchValuesFromDB(String input) {
		
		String strSource = input.replace("}", "").replace("{", "");
		String data[] = strSource.split(":");
        String value = data[1].trim().replaceAll("\"", "");
		
		SearchDao sdi = new SearchDaoImpl();
        String records = null;
    	records = sdi.getSearchData(value);
		return records;
		
	}
	
}
