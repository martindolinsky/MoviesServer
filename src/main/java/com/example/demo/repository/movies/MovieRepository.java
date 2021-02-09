package com.example.demo.repository.movies;

import com.example.demo.DemoApplication;
import com.example.demo.model.Comment;
import com.example.demo.model.Movie;
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
public class MovieRepository {


	public List<Movie> getAllMovies() {
		Movie movie;
		List<Movie> list = new ArrayList<>();

		try {

			PreparedStatement statement = DemoApplication.getConnection().prepareStatement("select * from movies");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				movie = new Movie();
				movie.setMovieID(rs.getInt("movieID"));
				movie.setTitleEN(rs.getString("titleEN"));
				movie.setTitleSK(rs.getString("titleSK"));
				movie.setGenre(rs.getString("genre"));
				movie.setYear(rs.getInt("year"));
				movie.setDirector(rs.getString("director"));
				movie.setActors(rs.getString("actors"));
				movie.setDescription(rs.getString("description"));
				movie.setSecondTitleEN(rs.getString("secondTitleEN"));
				movie.setSecondTitleSK(rs.getString("secondTitleSK"));
				movie.setSrc(rs.getString("src"));
				movie.setSrcImg(rs.getString("srcImg"));
				movie.setLength(rs.getInt("length"));
				movie.setImdbSrc(rs.getString("imdbSrc"));
				movie.setDataTitle(rs.getString("dataTitle"));

				list.add(movie);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

	public Movie getMovieById(int id) {
		Movie movie = new Movie();

		try {

			PreparedStatement statement = DemoApplication.getConnection().prepareStatement("select * from movies where movieID = ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				movie = new Movie();
				movie.setMovieID(id);
				movie.setTitleEN(rs.getString("titleEN"));
				movie.setTitleSK(rs.getString("titleSK"));
				movie.setGenre(rs.getString("genre"));
				movie.setYear(rs.getInt("year"));
				movie.setDirector(rs.getString("director"));
				movie.setActors(rs.getString("actors"));
				movie.setDescription(rs.getString("description"));
				movie.setSecondTitleEN(rs.getString("secondTitleEN"));
				movie.setSecondTitleSK(rs.getString("secondTitleSK"));
				movie.setSrc(rs.getString("src"));
				movie.setSrcImg(rs.getString("srcImg"));
				movie.setLength(rs.getInt("length"));
				movie.setImdbSrc(rs.getString("imdbSrc"));
				movie.setDataTitle(rs.getString("dataTitle"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return movie;
	}

	public boolean createMovie(Movie movie) {

		try {
			PreparedStatement statement = DemoApplication.getConnection().prepareStatement(
					"INSERT INTO `movies`(`titleEN`, `titleSK`, `genre`, `year`, `director`, `actors`, " +
							"`description`, `secondTitleEN`, `secondTitleSK`, `src`, `srcImg`, `length`, `imdbSrc`, " +
							"`dataTitle`)  VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)");

			statement.setString(1, movie.getTitleEN());
			statement.setString(2, movie.getTitleSK());
			statement.setString(3, movie.getGenre());
			statement.setInt(4, movie.getYear());
			statement.setString(5, movie.getDirector());
			statement.setString(6, movie.getActors());
			statement.setString(7, movie.getDescription());
			statement.setString(8, movie.getSecondTitleEN());
			statement.setString(9, movie.getSecondTitleSK());
			statement.setString(10, movie.getSrc());
			statement.setString(11, movie.getSrcImg());
			statement.setInt(12, movie.getLength());
			statement.setString(13, movie.getImdbSrc());
			statement.setString(14, movie.getDataTitle());


			int executeUpdate = statement.executeUpdate();

			if (executeUpdate == 1) {
				System.out.println("Movie is created: " +
						movie.getTitleEN() + " " + movie.getSecondTitleEN());
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean updateMovieById(Movie movie, int id) {

		try {


			PreparedStatement statement = DemoApplication.getConnection().prepareStatement(
					"UPDATE movies SET titleEN = ?, titleSK = ?, genre = ?, year = ?, director = ?, actors = ?, " +
							"description = ?, secondTitleEN = ?, secondTitleSK = ?, src = ?, srcImg = ?, length = ?, " +
							"imdbSrc = ?, `dataTitle` = ? WHERE movieID = ?");

			statement.setString(1, movie.getTitleEN());
			statement.setString(2, movie.getTitleSK());
			statement.setString(3, movie.getGenre());
			statement.setInt(4, movie.getYear());
			statement.setString(5, movie.getDirector());
			statement.setString(6, movie.getActors());
			statement.setString(7, movie.getDescription());
			statement.setString(8, movie.getSecondTitleEN());
			statement.setString(9, movie.getSecondTitleSK());
			statement.setString(10, movie.getSrc());
			statement.setString(11, movie.getSrcImg());
			statement.setInt(12, movie.getLength());
			statement.setString(13, movie.getImdbSrc());
			statement.setString(14, movie.getDataTitle());
			statement.setInt(15, id);

			int executeUpdate = statement.executeUpdate();

			if (executeUpdate == 1) {
				System.out.println("Movie with ID: " + id + " is updated: " +
						movie.getTitleEN() + " " + movie.getSecondTitleEN());
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public boolean deleteMovieById(Integer movieID) {
		try {

			PreparedStatement statement = DemoApplication.getConnection().prepareStatement(
					"DELETE FROM movies where movieID = ?");
			statement.setInt(1, movieID);
			int executeUpdate = statement.executeUpdate();

			if (executeUpdate == 1) {
				System.out.println("Movie is deleted with ID: " + movieID);
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
					"select * from comments where movieID like ?");
			statement.setInt(1, id);
			ResultSet rs = statement.executeQuery();

			PreparedStatement preparedStatement = DemoApplication.getConnection().prepareStatement(
					"select * from users where id like ?");

			while (rs.next()) {
				comment = new Comment();
				comment.setId(rs.getInt("commentID"));
				comment.setUserId(rs.getInt("userID"));
				comment.setDate(rs.getString("date"));
				comment.setMessage(rs.getString("message"));
				comment.setMovieId(rs.getInt("movieID"));
				comment.setSerialID(rs.getInt("serialID"));
				preparedStatement.setInt(1, rs.getInt("userID"));
				ResultSet resultSet = preparedStatement.executeQuery();
				if (resultSet.next()) {
					comment.setUsername(resultSet.getString("username"));
				}

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
					"INSERT INTO `comments`(`userID`, `date`, `message`, `movieID`)  " +
							"VALUES (?, ?, ?, ?)");


			statement.setInt(1, comment.getUserId());
			statement.setString(2, comment.getDate());
			statement.setString(3, comment.getMessage());
			statement.setInt(4, comment.getMovieId());

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
