package com.example.demo.controller.tvseries;

import com.example.demo.DemoApplication;
import com.example.demo.model.Comment;
import com.example.demo.model.TvSeries;
import com.example.demo.repository.tvseries.TvSeriesRepository;
import org.apache.tomcat.util.json.ParseException;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author Martin Dolinsky
 */
@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/tvseries")
public class TvSeriesController {

	@Autowired
	private TvSeriesRepository tvSeriesRepository;

	@GetMapping(path = "/list")
	public List<TvSeries> getAllTvSeries() {
		return tvSeriesRepository.getAllTvSeries();
	}

	@GetMapping(path = "/{id}")
	public TvSeries getSerialById(@PathVariable int id) {
		return tvSeriesRepository.getSerialById(id);
	}

	@DeleteMapping(path = "/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteSerialById(@PathVariable int id) throws JSONException {
		JSONObject response = new JSONObject();
		boolean b = tvSeriesRepository.deleteSerialById(id);
		if (b) {
			response.put("success", "Serial deleted with ID: " + id);
			return ResponseEntity.status(200).body(response.toString());
		} else {
			response.put("error", "Serial with ID: " + id + " not available");
			return ResponseEntity.status(400).body(response.toString());
		}
	}

	@PostMapping(path = "/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> createSerial(@RequestBody String body) throws JSONException, ParseException {
		JSONObject response = new JSONObject();
		TvSeries serial = new TvSeries();

		try {
			System.out.println("BODY: \n" + body);
			JSONObject job = new JSONObject(body);
			response = new JSONObject();
			serial = new TvSeries(
					job.getString("titleEN"), job.getString("titleSK"), job.getInt("year"),
					job.getString("director"), job.getString("actors"), job.getString("description"),
					job.getString("genre"), job.getString("secondTitleEN"), job.getString("secondTitleSK"),
					job.getString("src"), job.getString("srcImg"), job.getString("dataTitle"));
			boolean b = tvSeriesRepository.createSerial(serial);
			if (b) {
				response.put("success", "Serial created with title: " + serial.getTitleEN());
				return ResponseEntity.status(200).body(response.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("error", "Serial with title: " + serial.getTitleEN() + " not created");
			return ResponseEntity.status(400).body(response.toString());
		}
		return null;
	}

	@PostMapping(path = "/update/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> updateSerial(@PathVariable int id, @RequestBody String body) throws JSONException, ParseException {
		JSONObject response = new JSONObject();
		TvSeries serial;

		try {
			System.out.println("BODY: \n" + body);
			JSONObject job = new JSONObject(body);
			response = new JSONObject();
			serial = new TvSeries(
					job.getString("titleEN"), job.getString("titleSK"), job.getInt("year"),
					job.getString("director"), job.getString("actors"), job.getString("description"),
					job.getString("genre"), job.getString("secondTitleEN"), job.getString("secondTitleSK"),
					job.getString("src"), job.getString("srcImg"), job.getString("dataTitle"));
			boolean b = tvSeriesRepository.updateSerialById(serial, id);
			if (b) {
				response.put("success", "Serial updated with ID: " + id);
				return ResponseEntity.status(200).body(response.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("error", "Serial with ID: " + id + " not updated");
			return ResponseEntity.status(400).body(response.toString());
		}
		return null;
	}

	@GetMapping(path = "/comments/{id}")
	public List<Comment> getRelatedComments(@PathVariable int id) {
		return tvSeriesRepository.getRelatedComments(id);
	}

	@PostMapping(path = "/comments/{serialID}/create")
	public ResponseEntity<?> createComment(@PathVariable int serialID, @RequestBody String body) throws JSONException, SQLException {
		JSONObject response = new JSONObject();
		Comment comment = new Comment();
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		String userName = auth.getName();
		int id = 0;

		PreparedStatement userStatement = DemoApplication.getConnection().prepareStatement(
				"SELECT * FROM users where username like ?");
		userStatement.setString(1, userName);
		ResultSet rs = userStatement.executeQuery();
		while (rs.next()) {
			id = rs.getInt("id");
		}

		try {
			System.out.println("BODY: \n" + body);
			JSONObject job = new JSONObject(body);
			response = new JSONObject();
			comment = new Comment();
			comment.setUserId(id);
			comment.setDate(new Timestamp(System.currentTimeMillis()).toString());
			comment.setMessage(job.getString("message"));
			comment.setSerialID(serialID);

			boolean b = tvSeriesRepository.createComment(comment);
			if (b) {
				response.put("success", "Comment created with message: " + comment.getMessage());
				return ResponseEntity.status(200).body(response.toString());
			}
		} catch (Exception e) {
			e.printStackTrace();
			response.put("error", "Comment with message: " + comment.getMessage() + " not created");
			return ResponseEntity.status(400).body(response.toString());
		}
		return null;
	}

}
