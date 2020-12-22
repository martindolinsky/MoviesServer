package com.example.demo.repository.users;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Martin Dolinsky
 */
@Repository
public class UserRepository {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	public List<String> getAllUsers() {
		List<String> users = new ArrayList<>();
		users.addAll(jdbcTemplate.queryForList("select uidUsers from Users", String.class));
		return users;
	}
}
