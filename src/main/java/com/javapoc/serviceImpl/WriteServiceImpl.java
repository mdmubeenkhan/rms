package com.javapoc.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;
import com.javapoc.dao.WriteDao;
import com.javapoc.daoImpl.WriteDaoImpl;
import com.javapoc.service.WriteService;

public class WriteServiceImpl implements WriteService{
	
	public String loadValuesFromDB(String input) {
		String strSource = input.replace("}", "").replace("{", "");
		String data[] = strSource.split(":");
        String value = data[1].trim().replaceAll("\"", "");
		
		WriteDao wdi = new WriteDaoImpl();
        String records = null;
    	records = wdi.getSaveData(value);
		return records;
	}
	
	
	public String saveValuesToDB(String input) {
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
        String username = null;
        String summary = null;
        String name = null;
        String project = null;
        String type = null;
        String severity = null;
        String priority = null;
        String description = null;
        
        ArrayList<String> listOfValues = new ArrayList<>(values);
        for (int i = 0; i < 1; i++) {
        	username = listOfValues.get(0).replace("\"", "");
        	summary = listOfValues.get(1).replace("\"", "");
        	name = listOfValues.get(2).replace("\"", "");
        	project = listOfValues.get(3).replace("\"", "");	
        	type = listOfValues.get(4).replace("\"", "");
        	severity = listOfValues.get(5).replace("\"", "");
        	priority = listOfValues.get(6).replace("\"", "");	
        	description = listOfValues.get(7).replace("\"", "");
        }
        
        WriteDao wdi = new WriteDaoImpl();
        int result = wdi.saveWrite(username, summary, name, project, type, severity, priority, description);
        String records = null;
        if(result > 0) {
        	records = wdi.getSaveData(username);
        }
		return records;
	}

	
	public String submitValuesToDB(String input) {
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
        String summary = null;
        String name = null;
        String project = null;
        String type = null;
        String severity = null;
        String priority = null;
        String description = null;
        String created_by = null;
        String created_at = null;
        String status = null;
        
        ArrayList<String> listOfValues = new ArrayList<>(values);
        for (int i = 0; i < 1; i++) {
        	summary = listOfValues.get(0).replace("\"", "");
        	name = listOfValues.get(1).replace("\"", "");
        	project = listOfValues.get(2).replace("\"", "");	
        	type = listOfValues.get(3).replace("\"", "");
        	severity = listOfValues.get(4).replace("\"", "");
        	priority = listOfValues.get(5).replace("\"", "");	
        	description = listOfValues.get(6).replace("\"", "");
        	created_by = listOfValues.get(7).replace("\"", "");
        	created_at = listOfValues.get(8).replace("\"", "");	
        	status = listOfValues.get(9).replace("\"", "");	
        }
        
        WriteDao wdi = new WriteDaoImpl();
        int result = wdi.submitWrite(summary, name, project, type, severity, priority, description,created_by,created_at,status);
        String records = null;
        if(result > 0) {
        	records = wdi.getSubmitData();
        }
		return records;
	}


	public String clearValuesInDB(String input) {
		String strSource = input.replace("}", "").replace("{", "");
		String data[] = strSource.split(":");
        String value = data[1].trim().replaceAll("\"", "");
		
		WriteDao wdi = new WriteDaoImpl();
		int result = wdi.clearData(value);
        String records = null;
        if(result > 0) {
        	records = wdi.getSaveData(value);
        }
		return records;
	}

}
