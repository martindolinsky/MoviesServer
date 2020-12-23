package com.example.demo.repository.movies;

import com.example.demo.DemoApplication;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Dolinsky
 */
@Repository
public class MovieRepository {

	public List<String> getAllMovies() {
		String t = "";
		List<String> list = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection(DemoApplication.URL, DemoApplication.root, DemoApplication.root);

			PreparedStatement statement = connection.prepareStatement("select * from movies");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				t = rs.getString("titleEN") + " " + rs.getString("secondTitleEN") + " " + rs.getInt("year");
				list.add(t);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}


}
