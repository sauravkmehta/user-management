package com.yuvaan.usermanagement.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.yuvaan.usermanagement.entity.User;

/**
 * Repository class which extends {@link org.springframework.data.jpa.repository.JpaRepository}.
 * 
 * @author saurav
 *
 */
public interface UserRepository extends JpaRepository<User, Integer> {

	List<User> findByLastName(final String lastName);
	
	List<User> findByFirstName(final String firstName);

	Optional<User> findById(final Integer id);
	
	List<User> findByUsername(final String username);

	List<User> findAll();

}
