package com.javapoc.serviceImpl;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedHashMap;
import java.util.Map;

import com.javapoc.dao.LoginDao;
import com.javapoc.daoImpl.LoginDaoImpl;
import com.javapoc.service.LoginService;

public class LoginServiceImpl implements LoginService {

	public String validateUser(String input) {
		
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
        String password = null;
        
        ArrayList<String> listOfValues = new ArrayList<>(values);
        for (int i = 0; i < 1; i++) {
        	username = listOfValues.get(0).replace("\"", "");
        	password = listOfValues.get(1).replace("\"", "");
        }
        
		LoginDao ldi = new LoginDaoImpl();
        boolean result = ldi.validateLogin(username, password);
        String records = null;
        if(result == true) {
        	records = ldi.getLoginData(username);
        }
		return records;
		
	}
	
}
