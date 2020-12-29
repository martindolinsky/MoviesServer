package com.example.demo.controller.movies;

import com.example.demo.model.Movie;
import com.example.demo.repository.movies.MovieRepository;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author Martin Dolinsky
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/movies")
public class MovieController {

	@Autowired
	private MovieRepository movieRepository;

	@GetMapping(path = "/list")
	public List<Movie> getAllMovies() {
		return movieRepository.getAllMovies();
	}

	@GetMapping(path = "/{id}")
	public Movie getMovieById(@PathVariable int id) {
		return movieRepository.getMovieById(id);
	}

	@DeleteMapping(path = "/delete/{id}")
	public ResponseEntity<?> deleteMovieById(@PathVariable int id) throws JSONException {
		JSONObject response = new JSONObject();
		boolean b = movieRepository.deleteMovieById(id);
		if (b) {
			response.put("success", "Movie deleted with ID: " + id);
			return ResponseEntity.status(200).body(response.toString());
		} else {
			response.put("error", "Movie with ID: " + id + " not available");
			return ResponseEntity.status(400).body(response.toString());
		}
	}

	@PostMapping(path = "/create")
	public ResponseEntity<?> createMovie(@RequestBody String body) throws JSONException, ParseException {
		JSONObject response = new JSONObject();
		Movie movie = new Movie();

		try {
			System.out.println("BODY: \n" + body);
			JSONObject job = new JSONObject(body);
			response = new JSONObject();
			movie = new Movie(
					job.getString("titleEN"), job.getString("titleSK"),
					job.getString("genre"), job.getInt("year"), job.getString("director"),
					job.getString("actors"), job.getString("description"), job.getString("secondTitleEN"),
					job.getString("secondTitleSK"), job.getString("src"), job.getString("srcImg"),
					job.getInt("length"), job.getString("imdbSrc"), job.getString("dataTitle"));
			boolean b = movieRepository.createMovie(movie);
			if (b) {
				response.put("success", "Movie created with title: " + movie.getTitleEN());
				return ResponseEntity.status(200).body(response.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("error", "Movie with title: " + movie.getTitleEN() + " not created");
			return ResponseEntity.status(400).body(response.toString());
		}
		return null;
	}

	@PostMapping(path = "/update/{id}")
	public ResponseEntity<?> updateMovie(@PathVariable int id, @RequestBody String body) throws JSONException, ParseException {
		JSONObject response = new JSONObject();
		Movie movie;

		try {
			System.out.println("BODY: \n" + body);
			JSONObject job = new JSONObject(body);
			response = new JSONObject();
			movie = new Movie(
					job.getString("titleEN"), job.getString("titleSK"),
					job.getString("genre"), job.getInt("year"), job.getString("director"),
					job.getString("actors"), job.getString("description"), job.getString("secondTitleEN"),
					job.getString("secondTitleSK"), job.getString("src"), job.getString("srcImg"),
					job.getInt("length"), job.getString("imdbSrc"), job.getString("dataTitle"));
			boolean b = movieRepository.updateMovieById(movie, id);
			if (b) {
				response.put("success", "Movie updated with ID: " + id);
				return ResponseEntity.status(200).body(response.toString());
			} else {
				response.put("error", "Movie with ID: " + id + " not updated");
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("error", "Movie with ID: " + id + " not updated");
			return ResponseEntity.status(400).body(response.toString());
		}
		return null;
	}
}
