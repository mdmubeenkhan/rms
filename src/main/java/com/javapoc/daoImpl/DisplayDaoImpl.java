package com.javapoc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;
import com.javapoc.dao.DisplayDao;
import com.javapoc.util.DBConnection;

public class DisplayDaoImpl implements DisplayDao{

	@SuppressWarnings("unchecked")
	public String getDisplayData() {
		Connection con = DBConnection.getCon();
		JSONArray displayrecord = new JSONArray();
		try{
			PreparedStatement ps=con.prepareStatement("select ID,SUMMARY,DESCRIPTION,CREATED_BY,CREATED_AT,STATUS from requirements where STATUS!=?");
			ps.setString(1, "H");
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
