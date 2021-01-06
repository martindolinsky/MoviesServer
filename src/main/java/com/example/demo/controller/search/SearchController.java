package com.example.demo.controller.search;

import com.example.demo.DemoApplication;
import com.example.demo.model.Movie;
import com.example.demo.model.TvSeries;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Dolinsky
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/search")
public class SearchController {

	@GetMapping(path = "/movies/{search}")
	public List<Movie> getAllMoviesResults(@PathVariable String search) {
		return SearchRepository.getAllMoviesResults(search);
	}

	@GetMapping(path = "/tvseries/{search}")
	public List<TvSeries> getAllTvSeriesResults(@PathVariable String search) {
		return SearchRepository.getAllTvSeriesResults(search);
	}
}

class SearchRepository {

	public static List<Movie> getAllMoviesResults(String search) {
		Movie movie;
		List<Movie> list = new ArrayList<>();

		try {

			PreparedStatement statement = DemoApplication.getConnection().prepareStatement(
					"SELECT * FROM movies where titleEN like ? OR secondTitleEN like ? OR secondTitleSK like ? " +
							"OR titleSK like ? or year like ?");
			statement.setString(1, "%" + search + "%");
			statement.setString(2, "%" + search + "%");
			statement.setString(3, "%" + search + "%");
			statement.setString(4, "%" + search + "%");
			statement.setString(5, "%" + search + "%");
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

	public static List<TvSeries> getAllTvSeriesResults(String search) {
		TvSeries tvSeries;
		List<TvSeries> list = new ArrayList<>();

		try {

			PreparedStatement statement = DemoApplication.getConnection().prepareStatement(
					"SELECT * FROM tvseries where titleEN like ? OR secondTitleEN like ? OR secondTitleSK like ? " +
							"OR titleSK like ? or year like ?");
			statement.setString(1, "%" + search + "%");
			statement.setString(2, "%" + search + "%");
			statement.setString(3, "%" + search + "%");
			statement.setString(4, "%" + search + "%");
			statement.setString(5, "%" + search + "%");
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				tvSeries = new TvSeries();
				tvSeries.setSerialID(rs.getInt("serialID"));
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

				list.add(tvSeries);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		}
		return list;
	}

}
