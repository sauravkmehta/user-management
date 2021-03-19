package com.yuvaan.usermanagement;

import java.time.LocalDate;
import java.util.stream.IntStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.ApplicationRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.servlet.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Bean;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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
@RestController
@SpringBootApplication
public class UserManagementApplication  extends SpringBootServletInitializer{
	
	//Intentional code to show PMD is working
	private int unusedvariable;

	private static final Logger LOGGER = LoggerFactory.getLogger(UserManagementApplication.class);

	public static void main(String[] args) {
		SpringApplication.run(UserManagementApplication.class, args);
	}
	
	@Override
	   protected SpringApplicationBuilder configure(SpringApplicationBuilder application) {
	      return application.sources(UserManagementApplication.class);
	   }
	
	
	//Dummy rest to check if application is deployed
	@RequestMapping(value = "/")
	   public String hello() {
	      return "Hello World from Tomcat";
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
