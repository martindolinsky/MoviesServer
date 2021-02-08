package com.example.demo.repository.tvseries;

import com.example.demo.DemoApplication;
import com.example.demo.model.Comment;
import com.example.demo.model.TvSeries;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
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
			PreparedStatement statement = DemoApplication.getConnection().prepareStatement("select * from tvseries");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				tvSeries = new TvSeries(rs.getInt("serialID"), rs.getString("titleEN"),
						rs.getString("titleSK"), rs.getInt("year"), rs.getString("director"),
						rs.getString("actors"), rs.getString("description"), rs.getString("genre"),
						rs.getString("secondTitleEN"), rs.getString("secondTitleSK"), rs.getString("src"),
						rs.getString("srcImg"), rs.getString("dataTitle"));

				list.add(tvSeries);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public TvSeries getSerialById(int id) {
		TvSeries tvSeries = new TvSeries();

		try {


			PreparedStatement statement = DemoApplication.getConnection().prepareStatement("select * from tvseries where serialID = ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				tvSeries = new TvSeries();
				tvSeries.setSerialID(id);
				tvSeries.setTitleEN(rs.getString("titleEN"));
				tvSeries.setTitleSK(rs.getString("titleSK"));
				tvSeries.setGenre(rs.getString("genre"));
				tvSeries.setYear(rs.getInt("year"));
				tvSeries.setDirector(rs.getString("director"));
				tvSeries.setActors(rs.getString("actors"));
				tvSeries.setDescription(rs.getString("description"));
				tvSeries.setSecondTitleEN(rs.getString("secondTitleEN"));
				tvSeries.setSecondTitleSK(rs.getString("secondTitleSK"));
				tvSeries.setSrc(rs.getString("src"));
				tvSeries.setSrcImg(rs.getString("srcImg"));
				tvSeries.setDataTitle(rs.getString("dataTitle"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return tvSeries;
	}

	public boolean createSerial(TvSeries serial) {

		try {


			PreparedStatement statement = DemoApplication.getConnection().prepareStatement(
					"INSERT INTO `tvseries` (`titleEN`, `titleSK`, `year`, `director`, `actors`," +
							" `description`, `genre`, `secondTitleEN`, `secondTitleSK`, `src`, `srcImg`, `dataTitle`) " +
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


			PreparedStatement statement = DemoApplication.getConnection().prepareStatement(
					"UPDATE tvseries SET titleEN = ?, titleSK = ?, genre = ?, year = ?, director = ?, actors = ?, " +
							"description = ?, secondTitleEN = ?, secondTitleSK = ?, src = ?, srcImg = ?, " +
							"`dataTitle` = ? WHERE serialID = ?");

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
				System.out.println("Serial with ID: " + id + " is updated: " +
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

			PreparedStatement statement = DemoApplication.getConnection().prepareStatement(
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

	public List<Comment> getRelatedComments(int id) {
		Comment comment;
		List<Comment> list = new ArrayList<>();

		try {
			PreparedStatement statement = DemoApplication.getConnection().prepareStatement(
					"select * from comments where serialID like ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();


			while (rs.next()) {
				comment = new Comment();
				comment.setId(rs.getInt("commentID"));
				comment.setUserId(rs.getInt("userID"));
				comment.setDate(rs.getString("date"));
				comment.setMessage(rs.getString("message"));
				comment.setMovieId(rs.getInt("movieID"));
				comment.setSerialID(rs.getInt("serialID"));

				list.add(comment);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public boolean createComment(Comment comment) {
		try {
			PreparedStatement statement = DemoApplication.getConnection().prepareStatement(
					"INSERT INTO `comments`(`userID`, `date`, `message`, `serialID`)  " +
							"VALUES (?, ?, ?, ?)");


			statement.setInt(1, comment.getUserId());
			statement.setString(2, comment.getDate());
			statement.setString(3, comment.getMessage());
			statement.setInt(4, comment.getSerialID());

			int executeUpdate = statement.executeUpdate();

			if (executeUpdate == 1) {
				System.out.println("Comment is created: " + comment.getMessage());
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
