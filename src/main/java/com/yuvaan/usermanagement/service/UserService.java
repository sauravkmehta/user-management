package com.yuvaan.usermanagement.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yuvaan.usermanagement.entity.User;
import com.yuvaan.usermanagement.repository.UserRepository;

/**
 * 
 * @author saurav mehta
 *
 */
@Service
public class UserService {

	@Autowired
	private UserRepository userRepository;
	
	public Optional<User> getUserById(final Integer userId) {
		return userRepository.findById(userId);
	}

	
	public List<User> getUserByUsername(final String username) {
		return userRepository.findByUsername(username);
	}
	
	public void deleteUserById(final Integer userId) {
		userRepository.deleteById(userId);
		
	}

	public User createUser(final User user) {
		return userRepository.save(user);
	}

	public List<User> findAllUser() {
		return userRepository.findAll();
	}

	public List<User> findUserByLastName(final String lastName) {
		return userRepository.findByLastName(lastName);
	}
	
	public List<User> findUserByFirstName(final String firstName) {
		return userRepository.findByFirstName(firstName);
	}
}
