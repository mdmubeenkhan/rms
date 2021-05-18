package com.javapoc.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection implements DBInitializer{
	
	public static Connection con;	
	
	public static Connection getCon(){
		try {
			Class.forName(DRIVER);
			con = DriverManager.getConnection(CONURL,USERNAME,PASSWORD);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return con;
	}
}
