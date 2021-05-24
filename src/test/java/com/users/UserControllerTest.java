package com.users;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.users.controllers.UserController;
import com.users.model.dtos.UserDTO;
import com.users.model.entities.User;
import com.users.services.UserService;

/**
 * 
 * Test case for UserController class using Mockito.
 * 
 * @author anilKumar
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserControllerTest {

	@InjectMocks
	UserController userController;

	@Mock
	UserService userService;

	/**
	 * Test case for get all users
	 */
	@Test
	public void getAllUsersTest()	{

		List<User> list = new ArrayList<User>();
		list.add(new User(11L, "Karna", new Date(System.currentTimeMillis()), "Bangalore", "9876543210"));
		list.add(new User(11L, "Karma", new Date(System.currentTimeMillis()), "Bangalore", "9876543211"));
		list.add(new User(11L, "Katha", new Date(System.currentTimeMillis()), "Bangalore", "9876543212"));

		when(userService.getAllUsers()).thenReturn(list);

		ResponseEntity<List<UserDTO>> response = userController.getAllUsers();
		List<UserDTO> usersList = response.getBody();

		assertTrue(usersList.size() > 0);
		assertEquals(3, usersList.size());

		verify(userService, times(1)).getAllUsers();
	}


	/**
	 * Test case: Get user by id
	 */
	@Test
	public void getUserByIdTest()	{

		when(userService.getUserById(21L))
		.thenReturn(new User(21L, "Rajesh", new Date(System.currentTimeMillis()), "Bangalore", "9060544800"));

		UserDTO user = userController.getUserById(21L).getBody();

		assertNotNull(user);
		assertEquals("Rajesh", user.getName());
		assertEquals("Bangalore", user.getCity());
		assertEquals("9060544800", user.getMobileNumber());
	}


	/**
	 * Negative test case for get user by id
	 */
	@Test
	public void getUserByIdTest2()	{

		when(userService.getUserById(21L))
		.thenReturn(new User(21L, "Rajesh", new Date(System.currentTimeMillis()), "Bangalore", "9060544800"));

		UserDTO user = userController.getUserById(21L).getBody();

		assertNotNull(user);
		assertNotEquals(1, user.getId());
	}


	/**
	 * Test case for adding new user
	 */
	@Test
	public void createUserByIdTest()	{

		UserDTO userDto = new UserDTO(21L, "Sharan", new Date(System.currentTimeMillis()), "Bangalore", "9060544800");
		User user = new User(21L, "Sharan", new Date(System.currentTimeMillis()), "Bangalore", "9060544800");

		when(userService.addNewUserEntry(any(UserDTO.class))).thenReturn(user);

		ResponseEntity<UserDTO> reponse = userController.addNewUser(userDto);

		assertNotNull(reponse.getBody());
		assertEquals(HttpStatus.CREATED, reponse.getStatusCode());
	}


	/**
	 * Test case for adding new user
	 */
	@Test
	public void updateUserTest()	{

		UserDTO userDto = new UserDTO(21L, "Sham", new Date(System.currentTimeMillis()), "Bangalore", "9060544800");
		User user = new User(21L, "Sham Rao", new Date(System.currentTimeMillis()), "Bangalore", "9060544800");

		when(userService.updateExistingUser(21L, userDto)).thenReturn(user);

		ResponseEntity<UserDTO> response = userController.updateUser(21L, userDto);

		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotEquals("Sham", response.getBody().getName());
	}


	/**
	 * Test case for adding new user
	 */
	@Test
	public void deleteUserByIdTest()	{

		User user = new User(31L, "Devendra", new Date(System.currentTimeMillis()), "Bangalore", "9060544800");

		when(userService.deleteUser(any(Long.class))).thenReturn(user);

		ResponseEntity<UserDTO> response = userController.deleteUser(21L);

		assertNotNull(response);
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertEquals("Devendra", response.getBody().getName());
	}	
}