package com.javapoc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import com.javapoc.dao.WriteDao;
import com.javapoc.util.DBConnection;

public class WriteDaoImpl implements WriteDao{

	public int submitWrite(String summary,String name,String project,String type,String severity,String priority,String description, String created_by, String created_at, String status){
		Connection con = DBConnection.getCon();
		int result=0;
		int count = 0;
		try{
			PreparedStatement ps1 = con.prepareStatement("select max(id) from requirements");
			ResultSet rs = ps1.executeQuery();
			if(rs.next() == true) {
				count = rs.getInt(1) + 1;
			}
			PreparedStatement ps=con.prepareStatement("insert into requirements(ID,VERSION,SUMMARY,NAME,PROJECT,TYPE,SEVERITY,PRIORITY,DESCRIPTION,CREATED_BY,CREATED_AT,STATUS) values(?,?,?,?,?,?,?,?,?,?,?,?)");
			ps.setInt(1,count);
			ps.setInt(2,1);			
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
		return result;
	}

	@SuppressWarnings("unchecked")
	public String getSubmitData() {
		Connection con = DBConnection.getCon();
		JSONArray writerecord = new JSONArray();
		try{
			PreparedStatement ps=con.prepareStatement("select * from requirements ORDER BY id DESC LIMIT 1");
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
	            writerecord.add(map);
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
		String jsonString = writerecord.toString();
		return jsonString;
	}
	
	
	public int saveWrite(String username,String summary,String name,String project,String type,String severity,String priority,String description){
		Connection con = DBConnection.getCon();
		int status=0;
		String query;
		try{
			PreparedStatement ps = con.prepareStatement("select USERNAME from save where USERNAME=?");
			ps.setString(1, username);
			ResultSet rs = ps.executeQuery();
			
			if(rs.next() == true && username.equals(rs.getString(1)) ) {
				query = "update save set SUMMARY=?, NAME=?, PROJECT=?, TYPE=?, SEVERITy=?, PRIORITY=?, DESCRIPTION=? where USERNAME=?";
				PreparedStatement ps1=con.prepareStatement(query);
				ps1.setString(1,summary);
				ps1.setString(2,name);
				ps1.setString(3,project);
				ps1.setString(4,type);
				ps1.setString(5,severity);
				ps1.setString(6,priority);
				ps1.setString(7,description);
				ps1.setString(8,username);
				status=ps1.executeUpdate();
			}else {
				query = "insert into save(USERNAME,SUMMARY,NAME,PROJECT,TYPE,SEVERITY,PRIORITY,DESCRIPTION) values(?,?,?,?,?,?,?,?)";
				PreparedStatement ps2=con.prepareStatement(query);
				ps2.setString(1,username);
				ps2.setString(2,summary);
				ps2.setString(3,name);
				ps2.setString(4,project);
				ps2.setString(5,type);
				ps2.setString(6,severity);
				ps2.setString(7,priority);
				ps2.setString(8,description);
				status=ps2.executeUpdate();
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
		return status;
	}
	
	
	@SuppressWarnings("unchecked")
	public String getSaveData(String username) {
		Connection con = DBConnection.getCon();
		JSONArray saverecord = new JSONArray();
		try{
			PreparedStatement ps=con.prepareStatement("select * from save where USERNAME=?");
			ps.setString(1, username);
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
	            saverecord.add(map);
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
		String jsonString = saverecord.toString();
		return jsonString;
	}
	
	
	public int clearData(String username) {
		Connection con = DBConnection.getCon();
		int status=0;
		try{
			PreparedStatement ps=con.prepareStatement("delete from save where USERNAME=?");
			ps.setString(1, username);
			status=ps.executeUpdate();
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

	
		
}
