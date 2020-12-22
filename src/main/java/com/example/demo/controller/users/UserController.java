package com.example.demo.controller.users;

import com.example.demo.repository.users.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author Martin Dolinsky
 */

@RestController
@RequestMapping(path = "/user")
public class UserController {

	@Autowired
	private UserRepository userRepository;

	@GetMapping(path = "/list")
	public List<String> getAllUsers() {
		return userRepository.getAllUsers();
	}

}
