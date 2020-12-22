package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static void main(String[] args) {
		SpringApplication.run(DemoApplication.class, args);
	}

	@GetMapping("/hello")
	public String sayHello(@RequestParam(value = "myName", defaultValue = "World") String name) {
		return String.format("Hello %s!", name);
	}

	@GetMapping("/links")
	public String showLinks() {
		List<String> links = new ArrayList<>();
		links.add("<a href=" + "http://localhost:8080/user/list" + ">users</a>");
		links.add("<a href=" + "http://localhost:8080/movie/list" + ">movies</a>");
		links.add("<a href=" + "http://localhost:8080/tvseries/list" + ">tvseries</a>");
		return links.toString();
	}


}
