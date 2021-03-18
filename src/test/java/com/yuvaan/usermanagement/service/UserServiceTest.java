package com.yuvaan.usermanagement.service;


import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import com.yuvaan.usermanagement.entity.User;
import com.yuvaan.usermanagement.repository.UserRepository;

@ExtendWith(SpringExtension.class)
public class UserServiceTest {

    @Mock
    private UserRepository userRepository; 

    @InjectMocks
    private UserService userService;

    @Test
    @DisplayName("Given there are available users, when executing getAllUsers then all users are retrieved correctly")
    void testGetAllUsers() {
        final List<User> users = Arrays.asList(
        		new User("myFirstName", "myLastName", "username","myemail",  LocalDate.of(2021, 2, 16)),
        		new User("secondFirstName", "secondLastName", "username2","myemail2",  LocalDate.of(2021, 2, 16))
        );
        when(userRepository.findAll()).thenReturn(users); 
        final List<User> userList = userService.findAllUser();

        assertNotNull(userList);
        assertThat(userList.size(), is(users.size()));
        assertThat(userList.iterator().hasNext(), is(true));
    }

    @Test
    @DisplayName("Given there are no users, when retrieving the users then no users are retrieved")
    void testGetAllUsers_whenNoUsersExists() {
        when(userRepository.findAll()).thenReturn(new ArrayList<>());
        final List<User> userList = userService.findAllUser();
        assertNotNull(userList);
        assertThat(userList.size(), is(0));
    }

    @Test
    @DisplayName("Given there are available users, when retrieving a User by ID then the User is retrieved")
    void testGetUserWithUserId() {
        User user = new User("myFirstName", "myLastName", "username","myemail@email.com",  LocalDate.of(2021, 2, 16));
        Integer userId = 5;
        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        final Optional<User> optionalUser = userService.getUserById(userId);
        final User resultUser = optionalUser.get();
        assertNotNull(resultUser);
        assertThat(resultUser.getFirstName(), is("myFirstName"));
        assertThat(resultUser.getId(), is(0));
        assertThat(resultUser.getEmail(), is("myemail@email.com"));
    }

    @Test
    @DisplayName("Given there are no available users, when retrieving a user by ID then an NoSuchElementException is thrown")
    void testGetUserWithInvalidUserId() {
    	Optional<User> userWithInvalidId = userService.getUserById(13);
    	assertThat(userWithInvalidId.isPresent(), is(Boolean.FALSE));
    	assertThrows(NoSuchElementException.class, () -> userWithInvalidId.get());
    }

    @Test
    @DisplayName("Test Create User use case")
    void testCreateUser () {
    	User user = new User("myFirstName", "myLastName", "username","myemail",  LocalDate.of(2021, 2, 16));
    	when(userRepository.save(any(User.class))).thenReturn(user);
        final User newUser = userService.createUser(user);
        verify(userRepository, times(1)).save(any(User.class));
        assertNotNull(newUser);
    }
}
