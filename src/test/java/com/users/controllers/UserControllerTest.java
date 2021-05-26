package com.users.controllers;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.users.models.dtos.UserDTO;
import com.users.providers.MockDataProvider;
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

		List<UserDTO> list = MockDataProvider.getUserDTOList();

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

		when(userService.getUserById(104L)).thenReturn(MockDataProvider.getUserDTO());

		UserDTO user = userController.getUserById(104L).getBody();

		assertNotNull(user);
		assertEquals("Rajesh", user.getFirstName());
		assertEquals("Bangalore", user.getCity());
		assertEquals("9876543215", user.getMobileNumber());
	}


	/**
	 * Negative test case for get user by id
	 */
	@Test
	public void getUserByIdTest2()	{

		// user id 110 is not present in list
		when(userService.getUserById(110L)).thenReturn(null);

		UserDTO user = userController.getUserById(110L).getBody();

		assertNull(user);
	}


	/**
	 * Test case for adding new user
	 */
	@Test
	public void createUserByIdTest()	{

		UserDTO newUser = MockDataProvider.getNewUserDTO();

		when(userService.addNewUserEntry(any(UserDTO.class))).thenReturn(MockDataProvider.getNewUserDTO());

		ResponseEntity<UserDTO> reponse = userController.addNewUser(newUser);

		assertNotNull(reponse.getBody());
		assertEquals(HttpStatus.CREATED, reponse.getStatusCode());
	}


	/**
	 * Test case for adding new user
	 */
	@Test
	public void updateUserTest()	{

		UserDTO userDto = MockDataProvider.getExistingUserDTO();
		UserDTO user = MockDataProvider.getUpdatedUserDTO();

		when(userService.updateExistingUser(21L, userDto)).thenReturn(user);

		ResponseEntity<UserDTO> response = userController.updateUser(21L, userDto);

		assertNotNull(response.getBody());
		assertEquals(HttpStatus.OK, response.getStatusCode());
		assertNotEquals("Karna", response.getBody().getFirstName());
	}
	
	
	/**
	 * Test case for adding new user
	 */
	@Test
	public void deleteUserByIdTest()	{

		UserDTO user = MockDataProvider.getUserDTO();

		verify(userService, times(0)).deleteUser(user.getId());
	}
}
