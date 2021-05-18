package com.javapoc.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.javapoc.dao.HistoryDao;
import com.javapoc.daoImpl.HistoryDaoImpl;
import com.javapoc.service.HistoryService;

public class HistoryServiceImpl implements HistoryService {

	@Override
	public String historyValuesFromDB(String input) {
		String strSource = input.replace("}", "").replace("{", "");
		String data[] = strSource.split(":");
        String value = data[1].trim().replaceAll("\"", "");
		
		HistoryDao rdi = new HistoryDaoImpl();
        String records = null;
    	records = rdi.getHistoryData(value);
		return records;
	}

	@Override
	public String showValuesFromDB(String input) {
		String strSource = input.replace("}", "").replace("{", "");
        
		Map<String,String> map = new LinkedHashMap<String, String>();
	    String array[] = strSource.split(",");
	    for(String arr : array){
            String data[] = arr.split(":");
            String key = data[0].trim();
            String value = data[1].trim();
            map.put(key, value);
	    }
	    
        Collection<String> values = map.values();
        String id = null;
        String version = null;
        
        ArrayList<String> listOfValues = new ArrayList<>(values);
        for (int i = 0; i < 1; i++) {
        	id = listOfValues.get(0).replace("\"", "");
        	version = listOfValues.get(1).replace("\"", "");
        }
		
		HistoryDao hdi = new HistoryDaoImpl();
        String records = null;
    	records = hdi.getShowHistoryData(id,version);
		return records;
	}

}
