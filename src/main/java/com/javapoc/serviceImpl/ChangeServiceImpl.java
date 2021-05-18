package com.javapoc.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.javapoc.dao.ChangeDao;
import com.javapoc.daoImpl.ChangeDaoImpl;
import com.javapoc.service.ChangeService;

public class ChangeServiceImpl implements ChangeService{

	@Override
	public String ChangeValuesFromDB() {
		ChangeDao cdi = new ChangeDaoImpl();
        String records = null;
    	records = cdi.getChangeData();
		return records;
	}

	@Override
	public String showValuesFromDB(String input) {
		String strSource = input.replace("}", "").replace("{", "");
		String data[] = strSource.split(":");
        String value = data[1].trim().replaceAll("\"", "");
		
        ChangeDao cdi = new ChangeDaoImpl();
        String records = null;
    	records = cdi.getShowChangeData(value);
		return records;
	}

	@Override
	public String editValuesInDB(String input) {
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
        	id = listOfValues.get(0).replace("\"", "");
        	summary = listOfValues.get(1).replace("\"", "");
        	name = listOfValues.get(2).replace("\"", "");
        	project = listOfValues.get(3).replace("\"", "");	
        	type = listOfValues.get(4).replace("\"", "");
        	severity = listOfValues.get(5).replace("\"", "");
        	priority = listOfValues.get(6).replace("\"", "");	
        	description = listOfValues.get(7).replace("\"", "");
        	created_by = listOfValues.get(8).replace("\"", "");
        	created_at = listOfValues.get(9).replace("\"", "");	
        	status = listOfValues.get(10).replace("\"", "");	
        }
        
        ChangeDao cdi = new ChangeDaoImpl();
        int version = cdi.updateChangeData(id,summary,name,project,type,severity,priority,description,created_by,created_at,status);
		System.out.println(version);
        String records = null;
        int result = 0;
		if(version != 0){
			result = cdi.editChangeData(id,version);
		}
		if(result > 0) {
    		records = cdi.getEditData(id);
		}
		return records;
	}

	
}
