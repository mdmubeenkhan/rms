package com.javapoc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import com.javapoc.dao.LoginDao;
import com.javapoc.util.DBConnection;

public class LoginDaoImpl implements LoginDao{

	public boolean validateLogin(String username,String password){
		boolean status=false;
		Connection con = DBConnection.getCon();
		try{
			PreparedStatement ps=con.prepareStatement("select * from users where USERNAME=? and PASSWORD=?");
			ps.setString(1,username);
			ps.setString(2,password);
			ResultSet rs=ps.executeQuery();
			status=rs.next();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return status;
	}

	@SuppressWarnings("unchecked")
	public String getLoginData(String username) {
		Connection con = DBConnection.getCon();
		JSONArray displayrecord = new JSONArray();
		try{
			PreparedStatement ps=con.prepareStatement("select USERNAME,USERTYPE from users where USERNAME=?");
			ps.setString(1,username);
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			int columns = meta.getColumnCount();
			while (rs.next()) {
				Map<String,String> map = new LinkedHashMap<String, String>();
	            for (int i = 1; i <= columns; i++) {
	                String key = meta.getColumnName(i);
	                String value = rs.getString(key);
	                map.put(key, value);
	            }
				displayrecord.add(map);
			}
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		String jsonString = displayrecord.toString();
		return jsonString;
	}
	
		
}
