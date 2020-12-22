package com.example.demo.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 * @author Martin Dolinsky
 */
public class TestJDBC {
	public static void main(String[] args) {

		String jdbcUrl = "jdbc:mysql://localhost:8889/movieDatabase?useSSL=false&serverTimezone=UTC";
		String user = "root";
		String pass = "root";

		try {
			System.out.println("Connecting to database: " + jdbcUrl);
			Connection connection = DriverManager.getConnection(jdbcUrl, user, pass);
			System.out.println("Connection successful!");
		} catch (Exception exc) {
			exc.printStackTrace();
		}
	}
}
