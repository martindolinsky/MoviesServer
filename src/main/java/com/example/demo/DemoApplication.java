package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@SpringBootApplication
@RestController
public class DemoApplication {

	public static final String URL = "jdbc:mysql://localhost:8889/movieDatabase?useUnicode=true&useJDBCCompliantTimezoneShift=true&useLegacyDatetimeCode=false&serverTimezone=UTC";
	public static final String ROOT = "root";
	public static final String PASSWORD = "root";

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

	public static Connection getConnection() throws SQLException {
		Connection connection = DriverManager.getConnection(
				DemoApplication.URL, DemoApplication.ROOT, DemoApplication.PASSWORD);

		return connection;
	}


}
