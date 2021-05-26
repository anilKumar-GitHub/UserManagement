package com.users.services;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.users.models.dtos.UserDTO;
import com.users.models.entities.User;
import com.users.models.repositories.UserRepository;
import com.users.providers.MockDataProvider;
import com.users.services.impl.UserServiceImpl;

/**
 * Test cases for UserService class using Mockito
 * 
 * @author anilKumar
 *
 */
@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {


	@InjectMocks
	UserServiceImpl userService;

	@Mock
	UserRepository userRepository;


	@Test
	public void getAllUsersTest()	{

		List<User> list = MockDataProvider.getUserList();
		
		when(userRepository.findAll()).thenReturn(list);

		List<UserDTO> usersList = userService.getAllUsers();

		assertEquals(3, usersList.size());
		assertNotEquals(0, usersList.size());
		assertTrue(usersList.size() > 0);
	}


	/**
	 * Test case: Get user by id
	 */
	@Test
	public void getUserByIdTest()	{

		when(userRepository.findById(104L))
		.thenReturn(Optional.of(MockDataProvider.getUser()));

		UserDTO user = userService.getUserById(104L);

		assertNotEquals(null, user.getFirstName());
		assertEquals("Bangalore", user.getCity());
		assertEquals("9876543215", user.getMobileNumber());
	}


	/**
	 * Negative test case for get user by id
	 */
	@Test
	public void getUserByIdTest2()	{

		when(userRepository.findById(104L))
		.thenReturn(Optional.of(MockDataProvider.getUser()));

		UserDTO user = userService.getUserById(104L);

		assertNotNull(user);
		assertNotEquals(1, user.getId());
	}


	/**
	 * Test case for adding new user
	 */
	@Test
	public void createUserByIdTest()	{

		UserDTO userDto = MockDataProvider.getNewUserDTO();
		User user = MockDataProvider.getNewUser();

		when(userRepository.saveAndFlush(any(User.class))).thenReturn(user);

		UserDTO newUser = userService.addNewUserEntry(userDto);

		assertNotNull(newUser);
		assertNotEquals(null, newUser.getId());
	}


	/**
	 * Test case for adding new user
	 */
	@Test
	public void updateUserTest()	{

		UserDTO userDto = MockDataProvider.getExistingUserDTO();
		User user = MockDataProvider.getUpdatedUser();

		when(userRepository.existsById(any(Long.class))).thenReturn(true);
		when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
		when(userRepository.saveAndFlush(any(User.class))).thenReturn(user);

		UserDTO updateUser = userService.updateExistingUser(2L, userDto);

		assertNotNull(updateUser);
		assertNotEquals(21L, updateUser.getId());
	}
}
