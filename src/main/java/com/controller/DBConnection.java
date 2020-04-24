package com.controller;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	private static final String DRIVER = "com.mysql.cj.jdbc.Driver";
	private static final String URL = "jdbc:mysql://localhost:3306/cogent";
	private static final String USERNAME = "root";
	private static final String PASSWORD = "jam3s";
	private static Connection con = null;
	private static Integer count = 0;
	private static String tableName = "user";
	
	public static Connection getConnection() {
		try {
			if(con == null || con.isClosed()) {
					try {
						Class.forName(DRIVER);
					} catch (ClassNotFoundException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					//Class.forName("oracle.jdbc.driver.OracleDriver");
					con = DriverManager.getConnection(URL,USERNAME,PASSWORD);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return con;
	}
	
	public static String getTableName() {
		String tName = tableName + count.toString();
		count++;
		return tName;
	}
}
