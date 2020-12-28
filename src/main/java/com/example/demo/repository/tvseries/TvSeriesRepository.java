package com.example.demo.repository.tvseries;

import com.example.demo.DemoApplication;
import com.example.demo.model.TvSeries;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Dolinsky
 */
@Repository
public class TvSeriesRepository {

	public List<TvSeries> getAllTvSeries() {
		TvSeries tvSeries;
		List<TvSeries> list = new ArrayList<>();

		try {
			Connection connection = DriverManager.getConnection(
					DemoApplication.URL, DemoApplication.ROOT, DemoApplication.ROOT);

			PreparedStatement statement = connection.prepareStatement("select * from tvseries");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				tvSeries = new TvSeries(rs.getInt("serialID"), rs.getString("titleEN"),
						rs.getString("titleSK"), rs.getInt("year"), rs.getString("director"),
						rs.getString("actors"), rs.getString("description"), rs.getString("genre"),
						rs.getString("secondTitleEN"), rs.getString("secondTitleSK"), rs.getString("src"),
						rs.getString("srcImg"), rs.getString("data-title"));

				list.add(tvSeries);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean createSerial(TvSeries serial) {

		try {
			Connection connection = DriverManager.getConnection(
					DemoApplication.URL, DemoApplication.ROOT, DemoApplication.ROOT);

			PreparedStatement statement = connection.prepareStatement(
					"INSERT INTO `tvseries` (`titleEN`, `titleSK`, `year`, `director`, `actors`," +
							" `description`, `genre`, `secondTitleEN`, `secondTitleSK`, `src`, `srcImg`, `data-title`) " +
							"VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			statement.setString(1, serial.getTitleEN());
			statement.setString(2, serial.getTitleSK());
			statement.setInt(3, serial.getYear());
			statement.setString(4, serial.getDirector());
			statement.setString(5, serial.getActors());
			statement.setString(6, serial.getDescription());
			statement.setString(7, serial.getGenre());
			statement.setString(8, serial.getSecondTitleEN());
			statement.setString(9, serial.getSecondTitleSK());
			statement.setString(10, serial.getSrc());
			statement.setString(11, serial.getSrcImg());
			statement.setString(12, serial.getDataTitle());


			int executeUpdate = statement.executeUpdate();

			if (executeUpdate == 1) {
				System.out.println("Serial is created: " +
						serial.getTitleEN() + " " + serial.getSecondTitleEN());
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateSerialById(TvSeries serial, int id) {

		try {
			Connection connection = DriverManager.getConnection(
					DemoApplication.URL, DemoApplication.ROOT, DemoApplication.ROOT);

			PreparedStatement statement = connection.prepareStatement(
					"UPDATE tvseries SET titleEN = ?, titleSK = ?, genre = ?, year = ?, director = ?, actors = ?, " +
							"description = ?, secondTitleEN = ?, secondTitleSK = ?, src = ?, srcImg = ?, " +
							"`data-title` = ? WHERE serialID = ?");

			statement.setString(1, serial.getTitleEN());
			statement.setString(2, serial.getTitleSK());
			statement.setString(3, serial.getGenre());
			statement.setInt(4, serial.getYear());
			statement.setString(5, serial.getDirector());
			statement.setString(6, serial.getActors());
			statement.setString(7, serial.getDescription());
			statement.setString(8, serial.getSecondTitleEN());
			statement.setString(9, serial.getSecondTitleSK());
			statement.setString(10, serial.getSrc());
			statement.setString(11, serial.getSrcImg());
			statement.setString(12, serial.getDataTitle());
			statement.setInt(13, id);

			int executeUpdate = statement.executeUpdate();

			if (executeUpdate == 1) {
				System.out.println("Serial with ID: " + id + " is updaded: " +
						serial.getTitleEN() + " " + serial.getSecondTitleEN());
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteSerialById(Integer serialId) {
		try {
			Connection connection = DriverManager.getConnection(
					DemoApplication.URL, DemoApplication.ROOT, DemoApplication.ROOT);
			PreparedStatement statement = connection.prepareStatement(
					"DELETE FROM tvseries where serialID = ?");
			statement.setInt(1, serialId);
			int executeUpdate = statement.executeUpdate();

			if (executeUpdate == 1) {
				System.out.println("Serial is deleted with ID: " + serialId);
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
