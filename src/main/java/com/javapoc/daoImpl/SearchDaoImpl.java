package com.javapoc.daoImpl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.util.LinkedHashMap;
import java.util.Map;
import org.json.simple.JSONArray;
import com.javapoc.dao.SearchDao;
import com.javapoc.util.DBConnection;

public class SearchDaoImpl implements SearchDao{

	@SuppressWarnings("unchecked")
	public String getSearchData(String searchText) {
		Connection con = DBConnection.getCon();
		JSONArray searchrecord = new JSONArray();
		String query;
		if(searchText.matches("^\\d+$") == true) {
			query = "select ID,SUMMARY,DESCRIPTION,CREATED_BY,CREATED_AT,STATUS from requirements where id=? and STATUS!=?";
		}else {
			searchText ="%" + searchText + "%";
			query = "select ID,SUMMARY,DESCRIPTION,CREATED_BY,CREATED_AT,STATUS from requirements where SUMMARY like ? and STATUS!=?";
		}		
		try{
			PreparedStatement ps=con.prepareStatement(query);
			ps.setString(1, searchText);
			ps.setString(2, "H");
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
				searchrecord.add(map);
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
		String jsonString = searchrecord.toString();
		return jsonString;
	}
	
}
