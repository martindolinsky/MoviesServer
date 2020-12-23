package com.example.demo.repository.users;

import com.example.demo.DemoApplication;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Dolinsky
 */
@Repository
public class UserRepository {


	public List<String> getAllUsers() {
		String t = "";
		List<String> list = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection(DemoApplication.URL, DemoApplication.root, DemoApplication.root);

			PreparedStatement statement = connection.prepareStatement("select * from Users");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				t = "User: " + rs.getString(2) + " Email:  " + rs.getString(3);
				list.add(t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
