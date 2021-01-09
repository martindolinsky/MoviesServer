package com.example.demo.controller.users;

import com.example.demo.DemoApplication;
import com.example.demo.model.ERole;
import com.example.demo.model.Role;
import com.example.demo.model.User;
import com.example.demo.payload.request.LoginRequest;
import com.example.demo.payload.request.SignupRequest;
import com.example.demo.payload.response.JwtResponse;
import com.example.demo.payload.response.MessageResponse;
import com.example.demo.repository.RoleRepository;
import com.example.demo.repository.users.UserRepository;
import com.example.demo.security.jwt.JwtUtils;
import com.example.demo.security.services.UserDetailsImpl;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping(path = "/api/auth")
public class UserController {

	@Autowired
	AuthenticationManager authenticationManager;

	@Autowired
	UserRepository userRepository;

	@Autowired
	RoleRepository roleRepository;

	@Autowired
	PasswordEncoder encoder;

	@Autowired
	JwtUtils jwtUtils;

	@PostMapping("/signup")
	public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) {
		if (userRepository.existsByUsername(signUpRequest.getUsername())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Username is already taken!"));
		}

		if (userRepository.existsByEmail(signUpRequest.getEmail())) {
			return ResponseEntity
					.badRequest()
					.body(new MessageResponse("Error: Email is already in use!"));
		}

		// Create new user's account
		User user = new User(signUpRequest.getUsername(),
				signUpRequest.getEmail(),
				encoder.encode(signUpRequest.getPassword()));

		Set<String> strRoles = signUpRequest.getRole();
		Set<Role> roles = new HashSet<>();

		if (strRoles == null) {
			Role userRole = roleRepository.findByName(ERole.ROLE_USER)
					.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
			roles.add(userRole);
		} else {
			strRoles.forEach(role -> {
				switch (role) {
					case "admin":
						Role adminRole = roleRepository.findByName(ERole.ROLE_ADMIN)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(adminRole);
						break;
					case "mod":
						Role modRole = roleRepository.findByName(ERole.ROLE_MODERATOR)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(modRole);
						break;
					default:
						Role userRole = roleRepository.findByName(ERole.ROLE_USER)
								.orElseThrow(() -> new RuntimeException("Error: Role is not found."));
						roles.add(userRole);
				}
			});
		}

		user.setRoles(roles);
		userRepository.save(user);

		return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
	}

	@PostMapping("/login")
	public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

		Authentication authentication = authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

		SecurityContextHolder.getContext().setAuthentication(authentication);
		String jwt = jwtUtils.generateJwtToken(authentication);

		UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
		List<String> roles = userDetails.getAuthorities().stream()
				.map(item -> item.getAuthority())
				.collect(Collectors.toList());

		return ResponseEntity.ok(new JwtResponse(jwt,
				userDetails.getId(),
				userDetails.getUsername(),
				userDetails.getEmail(),
				roles));
	}

	@GetMapping(path = "/list")
	public List<User> getAllUsers() {
		return UserRepositoryClass.getAllUsersFromDatabase();
	}

	//	TODO: Fix deleting from db
	@DeleteMapping(path = "/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> deleteUserById(@PathVariable int id) throws JSONException {
		JSONObject response = new JSONObject();
		boolean b = UserRepositoryClass.deleteUserById(id);
		if (b) {
			response.put("success", "user deleted with ID: " + id);
			return ResponseEntity.status(200).body(response.toString());
		} else {
			response.put("error", "User with ID: " + id + " not available");
			return ResponseEntity.status(400).body(response.toString());
		}
	}
}


class UserRepositoryClass {

	public static List<User> getAllUsersFromDatabase() {
		User user;
		List<User> list = new ArrayList<>();

		try {

			PreparedStatement statement = DemoApplication.getConnection().prepareStatement("" +
					"select users.id,users.username,users.email,r.name from users " +
					"join user_roles ur on users.id = ur.user_id " +
					"join roles r on ur.role_id = r.id order by users.id");
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString("r.name"));
				user = new User();
				user.setId(rs.getLong("users.id"));
				user.setUsername(rs.getString("username"));
				user.setEmail(rs.getString("email"));
//				TODO: Fix setting role
				Role role = new Role();
//				role.setName(rs.getString("r.name"));

//				TODO: Fix getting roles
//				user.setRoles((Set<Role>) rs.getString("r.name"));
				list.add(user);
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}


	public static boolean deleteUserById(Integer userID) {
		try {

			PreparedStatement statement = DemoApplication.getConnection().prepareStatement(
					"DELETE FROM users where id = ?");
			statement.setInt(1, userID);
			int executeUpdate = statement.executeUpdate();

			if (executeUpdate == 1) {
				System.out.println("User is deleted with ID: " + userID);
				return true;
			} else {
				System.out.println("User is not deleted with ID: " + userID);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}
}
