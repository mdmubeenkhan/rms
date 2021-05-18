package com.javapoc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;

import com.javapoc.dao.HistoryDao;
import com.javapoc.util.DBConnection;

public class HistoryDaoImpl implements HistoryDao {

	@SuppressWarnings("unchecked")
	@Override
	public String getHistoryData(String id) {
		Connection con = DBConnection.getCon();
		JSONArray historyrecord = new JSONArray();
		try{
			PreparedStatement ps=con.prepareStatement("select ID,VERSION,SUMMARY,DESCRIPTION,CREATED_BY,CREATED_AT,STATUS from requirements where ID=?");
			ps.setString(1, id);
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
				historyrecord.add(map);
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
		String jsonString = historyrecord.toString();
		return jsonString;
	}


	@SuppressWarnings("unchecked")
	@Override
	public String getShowHistoryData(String id, String version) {
		Connection con = DBConnection.getCon();
		JSONArray showrecord = new JSONArray();
		try{
			PreparedStatement ps=con.prepareStatement("select SUMMARY,NAME,PROJECT,TYPE,SEVERITY,PRIORITY,DESCRIPTION from requirements where ID=? and VERSION=?");
			ps.setString(1, id);
			ps.setString(2, version);
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
				showrecord.add(map);
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
		String jsonString = showrecord.toString();
		return jsonString;
	}

}
