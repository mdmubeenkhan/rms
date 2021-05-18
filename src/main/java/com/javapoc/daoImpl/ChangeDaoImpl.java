package com.javapoc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;

import com.javapoc.dao.ChangeDao;
import com.javapoc.util.DBConnection;

public class ChangeDaoImpl implements ChangeDao{

	@SuppressWarnings("unchecked")
	@Override
	public String getChangeData() {
		Connection con = DBConnection.getCon();
		JSONArray changerecord = new JSONArray();
		try{
			PreparedStatement ps=con.prepareStatement("select ID,SUMMARY,DESCRIPTION,CREATED_BY,CREATED_AT from requirements where STATUS=?");
			ps.setString(1, "M");
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
	            changerecord.add(map);
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
		String jsonString = changerecord.toString();
		return jsonString;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getShowChangeData(String id) {
		Connection con = DBConnection.getCon();
		JSONArray showrecord = new JSONArray();
		try{
			PreparedStatement ps=con.prepareStatement("select SUMMARY,NAME,PROJECT,TYPE,SEVERITY,PRIORITY,DESCRIPTION from requirements where ID=? and STATUS=? ORDER BY VERSION DESC LIMIT 1");
			ps.setString(1, id);
			ps.setString(2, "M");
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

	@Override
	public int updateChangeData(String id,String summary,String name,String project,String type,String severity,String priority,String description, String created_by, String created_at, String status) {
		Connection con = DBConnection.getCon();
		@SuppressWarnings("unused")
		int result;
		int version=0;
		try{
			PreparedStatement ps1=con.prepareStatement("select max(VERSION) from requirements where id=?");
			ps1.setString(1, id);
			ResultSet rs = ps1.executeQuery();
			if(rs.next()) {
				version = Integer.parseInt(rs.getString(1));
			}
			int new_version = version + 1 ;
			
			PreparedStatement ps=con.prepareStatement("insert into requirements(ID,VERSION,SUMMARY,NAME,PROJECT,TYPE,SEVERITY,PRIORITY,DESCRIPTION,CREATED_BY,CREATED_AT,STATUS) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setString(1,id);
			ps.setInt(2,new_version);
			ps.setString(3,summary);
			ps.setString(4,name);
			ps.setString(5,project);
			ps.setString(6,type);
			ps.setString(7,severity);
			ps.setString(8,priority);
			ps.setString(9,description);
			ps.setString(10,created_by);
			ps.setString(11,created_at);
			ps.setString(12,status);
			result=ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return version;
	}
	
	public int editChangeData(String id, int version) {
		Connection con = DBConnection.getCon();
		int result=0;
		try{
			PreparedStatement ps=con.prepareStatement("update requirements set STATUS=? where id=? and VERSION=?");
			ps.setString(1,"H");
			ps.setString(2,id);
			ps.setInt(3,version);
			result=ps.executeUpdate();
		}catch(Exception e){
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return result;
	}
	
	@SuppressWarnings("unchecked")
	@Override
	public String getEditData(String id) {
		Connection con = DBConnection.getCon();
		JSONArray editrecord = new JSONArray();
		try{
			PreparedStatement ps=con.prepareStatement("select * from requirements where id=?");
			ps.setString(1, id);
			ResultSet rs=ps.executeQuery();
			ResultSetMetaData meta = rs.getMetaData();
			int columns = meta.getColumnCount();
			while (rs.next()) {
				Map<String,String> map = new LinkedHashMap<String, String>();
	            for (int i = 2; i <= columns; i++) {
	                String key = meta.getColumnName(i);
	                String value = rs.getString(key);
	                map.put(key, value);
	            }
	            editrecord.add(map);
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
		String jsonString = editrecord.toString();
		return jsonString;
	}

	
}
