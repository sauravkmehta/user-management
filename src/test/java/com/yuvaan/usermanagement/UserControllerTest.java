package com.yuvaan.usermanagement;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.skyscreamer.jsonassert.JSONAssert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.client.RestTemplate;

import com.yuvaan.usermanagement.controller.UserController;
import com.yuvaan.usermanagement.entity.User;
import com.yuvaan.usermanagement.service.UserService;

@AutoConfigureMockMvc
@ContextConfiguration(classes = { UserController.class })
@WebMvcTest
public class UserControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private RestTemplate mockRestTemplate;

	@MockBean
	UserService mockUserService;

	@Test
	@DisplayName("Test case to validate getUserWithId")
	public void testGetUserWithId() throws Exception {
		String expectedJson = "{\"id\" : 0, \"firstName\" : \"myFirstName\", \"lastName\" : \"myLastName\", \"username\" : \"username\", \"email\" : \"myemail\", \"dateOfBirth\" : \"2021-02-16\"}";
		User user = new User("myFirstName", "myLastName", "username","myemail",  LocalDate.of(2021, 2, 16));
		when(mockUserService.getUserById(anyInt()))
				.thenReturn(Optional.of(user));
		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/usermanagement/user/1")
				.accept(MediaType.APPLICATION_JSON);
		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
	}

	@Test
	@DisplayName("Test case to  validate getUserWithLastName")
	public void testGetUserWithLastName() throws Exception {
		String expectedJson = "[{\"id\" : 0, \"firstName\" : \"saurav\", \"lastName\" : \"mehta\", \"username\" : \"myusername\", \"email\" : \"myemail\", \"dateOfBirth\" : \"2021-02-16\"}]";
		List<User> userList = new ArrayList<>();
		userList.add(new User("saurav", "mehta", "myusername", "myemail", LocalDate.of(2021, 2, 16)));
		when(mockUserService.findUserByLastName(anyString())).thenReturn(userList);
		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/usermanagement/user?lastName=mehta")
				.accept(MediaType.APPLICATION_JSON);
		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertNotNull(result.getResponse().getContentAsString());
		JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
	}

	@Test
	@DisplayName("Test case to validate getAllUser")
	public void testGetAllUser() throws Exception {
		String expectedJson = "[{\"id\" : 0, \"firstName\" : \"myfirstName\", \"lastName\" : \"mylastName\", \"username\" : \"myusername\", \"email\" : \"myemail\",  \"dateOfBirth\" : \"2021-02-16\"}]";
		List<User> userList = new ArrayList<>();
		userList.add(new User("myfirstName", "mylastName", "myusername", "myemail", LocalDate.of(2021, 2, 16)));
		when(mockUserService.findAllUser()).thenReturn(userList);
		final RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/usermanagement/users/")
				.accept(MediaType.APPLICATION_JSON);
		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		JSONAssert.assertEquals(expectedJson, result.getResponse().getContentAsString(), false);
	}

	@Test
	@DisplayName("Test case to validate delete a User with UserId")
	public void testDeleteUser() throws Exception {
		List<User> userList = new ArrayList<>();
		userList.add(new User("firstName", "lastName",  "username", "email", LocalDate.now()));
		doNothing().when(mockUserService).deleteUserById(anyInt());
		final RequestBuilder requestBuilder = MockMvcRequestBuilders.delete("/usermanagement/user/0")
				.accept(MediaType.APPLICATION_JSON);
		final MvcResult result = mockMvc.perform(requestBuilder).andReturn();
		assertEquals(HttpStatus.OK.value(), result.getResponse().getStatus());
		assertNotNull(result.getResponse().getContentAsString());
	}
}
