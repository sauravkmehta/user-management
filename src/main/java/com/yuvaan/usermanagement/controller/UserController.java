package com.yuvaan.usermanagement.controller;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityNotFoundException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.yuvaan.usermanagement.entity.User;
import com.yuvaan.usermanagement.service.UserService;

/**
 * {@link UserController} is a spring {@link RestController} controller to
 * handle REST requests towards '/usermanagement' endpoint.
 * 
 * @author saurav
 *
 */
@RestController
@RequestMapping("/usermanagement")
public class UserController {
	private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

	@Autowired
	private UserService userService;

	@GetMapping("/user/{userId}")
	public ResponseEntity<User> getUserById(@PathVariable  final Integer userId) {
		LOGGER.info("Accessing User GET endpoint /user/{} for application", userId);
		final Optional<User> result = userService.getUserById(userId);
		if (result.isPresent()) {
			User user = result.get();
			return new ResponseEntity<>(user, HttpStatus.OK);
		} else {
			throw new EntityNotFoundException("User does not exists!!");
		}

	}

	@GetMapping("/user")
	public List<User> getUserByLastName(@RequestParam String lastName) {
		LOGGER.info("Accessing User GET endpoint /user?lastName={} for application", lastName);
		final List<User> resultList = userService.findUserByLastName(lastName);
		return resultList;
	}

	@GetMapping("/users")
	public List<User> getAllUser() {
		LOGGER.info("Accessing  GET /users endpoint to get all Usersfor application");
		final List<User> resultList = userService.findAllUser();
		return resultList;
	}

	@PostMapping("/user")
	public ResponseEntity<?> createUser(@RequestBody User user) {
		LOGGER.info("Accessing  POST /users endpoint to get all Usersfor application");
		final User result = userService.createUser(user);
		return new ResponseEntity<>(result, HttpStatus.OK);
	}

	@DeleteMapping("/user/{userId}")
	public ResponseEntity<?> deleteUser(@PathVariable final Integer userId) {
		LOGGER.info("Accessing  DELETE /users endpoint to get all Usersfor application");
		userService.deleteUserById(userId);
		return ResponseEntity.ok(HttpStatus.OK);
	}

}
