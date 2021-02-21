package com.yuvaan.usermanagement;

import java.time.LocalDate;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import com.yuvaan.usermanagement.entity.User;
import com.yuvaan.usermanagement.service.UserService;

/**
 *
 * {@link UserManagementApplication} is main
 * {@link org.springframework.boot.autoconfigure.SpringBootApplication}.
 * 
 * @author saurav
 *
 */
@SpringBootApplication
public class UserManagementApplication {

	private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}

	@Bean
	ApplicationRunner applicationRunner(final UserService userService) {
		return args -> {
			IntStream.range(0, 10).forEach(id -> {
				User user = new User("first_name_" + id, "last_name_" + id, "username_" + 1,
						"email_" + id + "@email.com", LocalDate.of(1985, 12, 1));
				userService.createUser(user);
			});
			LOGGER.info("The default users were successfully created!");
		};
	}

}