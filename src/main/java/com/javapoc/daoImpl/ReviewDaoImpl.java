package com.javapoc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;

import org.json.simple.JSONArray;

import com.javapoc.dao.ReviewDao;
import com.javapoc.util.DBConnection;

public class ReviewDaoImpl implements ReviewDao {

	@SuppressWarnings("unchecked")
	public String getReviewData(String username) {
		Connection con = DBConnection.getCon();
		JSONArray reviewrecord = new JSONArray();
		try{
			PreparedStatement ps=con.prepareStatement("select ID,SUMMARY,DESCRIPTION,CREATED_BY,CREATED_AT from requirements where STATUS=? and CREATED_BY!=?");
			ps.setString(1, "P");
			ps.setString(2, username);
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
				reviewrecord.add(map);
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
		String jsonString = reviewrecord.toString();
		return jsonString;
	}

	@SuppressWarnings("unchecked")
	@Override
	public String getShowReviewData(String ID) {
		Connection con = DBConnection.getCon();
		JSONArray showrecord = new JSONArray();
		try{
			PreparedStatement ps=con.prepareStatement("select SUMMARY,NAME,PROJECT,TYPE,SEVERITY,PRIORITY,DESCRIPTION from requirements where ID=? ORDER BY VERSION DESC LIMIT 1");
			ps.setString(1, ID);
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
	public int approveReviewData(String value) {
		Connection con = DBConnection.getCon();
		int result=0;
		try{
			PreparedStatement ps=con.prepareStatement("update requirements set STATUS=? where id=? and STATUS=?");
			ps.setString(1,"A");
			ps.setString(2,value);
			ps.setString(3,"P");
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
	public String getApproveData(String id) {
		Connection con = DBConnection.getCon();
		JSONArray approverecord = new JSONArray();
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
	            approverecord.add(map);
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
		String jsonString = approverecord.toString();
		return jsonString;
	}
	
public int rejectReviewData(String value) {
		
		Connection con = DBConnection.getCon();
		int result=0;
		try{
			PreparedStatement ps=con.prepareStatement("update requirements set STATUS=? where id=? and STATUS=?");
			ps.setString(1,"R");
			ps.setString(2,value);
			ps.setString(3,"P");
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
	public String getRejectData(String id) {
		Connection con = DBConnection.getCon();
		JSONArray rejectrecord = new JSONArray();
		try{
//			PreparedStatement ps=con.prepareStatement("select * from requirements where id=? and STATUS=?");
			PreparedStatement ps=con.prepareStatement("select * from requirements where id=?");
			ps.setString(1, id);
//			ps.setString(2, "R");
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
	            rejectrecord.add(map);
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
		String jsonString = rejectrecord.toString();
		return jsonString;
	}

	@Override
	public int modifyReviewData(String value) {
		Connection con = DBConnection.getCon();
		int result=0;
		try{
			PreparedStatement ps=con.prepareStatement("update requirements set STATUS=? where id=? and STATUS=?");
			ps.setString(1,"M");
			ps.setString(2,value);
			ps.setString(3,"P");
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
	public String getModifyData(String id) {
		Connection con = DBConnection.getCon();
		JSONArray modifyrecord = new JSONArray();
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
	            modifyrecord.add(map);
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
		String jsonString = modifyrecord.toString();
		return jsonString;
	}
	
}
