package com.example.demo.controller.movies;

import com.example.demo.repository.movies.MovieRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Martin Dolinsky
 */
@RestController
@RequestMapping(path = "/movie")
public class MovieController {

	@Autowired
	private MovieRepository movieRepository;

	@GetMapping(path = "/list")
	public List<String> getAllUsers() {
		return movieRepository.getAllMovies();
	}

}
