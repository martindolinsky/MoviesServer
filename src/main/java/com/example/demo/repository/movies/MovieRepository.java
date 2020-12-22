package com.example.demo.repository.movies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Dolinsky
 */
@Repository
public class MovieRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<String> getAllMovies() {
		List<String> movies = new ArrayList<>();
		movies.addAll(jdbcTemplate.queryForList("select titleEN from movies", String.class));
		return movies;
	}
}
