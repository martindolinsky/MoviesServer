package com.example.demo.controller.tvseries;

import com.example.demo.repository.tvseries.TvSeriesRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Martin Dolinsky
 */
@RestController
@RequestMapping(path = "/tvseries")
public class TvSeriesController {

	@Autowired
	private TvSeriesRepository tvSeriesRepository;

	@GetMapping(path = "/list")
	public List<String> getAllUsers() {
		return tvSeriesRepository.getAllTvSeries();
	}

}
