package com.bankapp.DBManager;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DbConnection {
	private static volatile Connection connection;
	 public static Connection getConnection()
	    {
	        try {
				if (connection == null) {

					synchronized (DbConnection.class) {

						if (connection == null) {
							String mysqlJDBCDriver = "oracle.jdbc.driver.OracleDriver";
							String url = "jdbc:oracle:thin:@localhost:1521:XE";
							String user = "Bank";
							String pass = "Bank";
							Class.forName(mysqlJDBCDriver);
							connection = DriverManager.getConnection(url, user, pass);
						}
					}
				}

	        }
	        catch (Exception e) {
	            System.out.println("Connection Failed!");
	        }
	        return connection;
	    }

	public static void closeConnection() throws SQLException {
		if (connection != null) {

			synchronized (DbConnection.class) {

				if (connection != null) {
					connection = null;
				}
			}
		}
	}
}
