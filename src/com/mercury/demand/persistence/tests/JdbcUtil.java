package com.mercury.demand.persistence.tests;

import java.sql.Connection;
import java.sql.DriverManager;

public class JdbcUtil {
	private static final String DRIVER = "oracle.jdbc.driver.OracleDriver";
	private static final String URL = "jdbc:oracle:thin:@MININT-RDRO494:1521:XE";
	private static final String USERNAME = "LilyJava";
	private static final String PASSWORD = "112233abc";
	
	public static Connection getConnection(){
		Connection conn = null;
		try{
			Class.forName(DRIVER);
			conn = DriverManager.getConnection(URL, USERNAME, PASSWORD);
		} catch (Exception e)
		{
			e.printStackTrace();
		}
		
		return conn;
	}
	
	

}
