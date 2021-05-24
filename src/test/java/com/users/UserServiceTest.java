package com.users;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import com.users.model.dtos.UserDTO;
import com.users.model.entities.User;
import com.users.model.repositories.UserRepository;
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

		List<User> list = new ArrayList<User>();
		list.add(new User(11L, "Karna", new Date(System.currentTimeMillis()), "Bangalore", "9876543210"));
		list.add(new User(11L, "Karma", new Date(System.currentTimeMillis()), "Bangalore", "9876543211"));
		list.add(new User(11L, "Katha", new Date(System.currentTimeMillis()), "Bangalore", "9876543212"));

		when(userRepository.findAll()).thenReturn(list);

		List<User> usersList = userService.getAllUsers();

		assertEquals(3, usersList.size());
		assertNotEquals(0, usersList.size());
		assertTrue(usersList.size() > 0);
	}


	/**
	 * Test case: Get user by id
	 */
	@Test
	public void getUserByIdTest()	{

		when(userRepository.findById(21L))
		.thenReturn(Optional.of(new User(21L, "Rajesh", new Date(System.currentTimeMillis()), "Bangalore", "9060544800")));

		User user = userService.getUserById(21L);

		assertNotEquals(null, user.getName());
		assertEquals("Bangalore", user.getCity());
		assertEquals("9060544800", user.getMobileNumber());
	}


	/**
	 * Negative test case for get user by id
	 */
	@Test
	public void getUserByIdTest2()	{

		when(userRepository.findById(21L))
		.thenReturn(Optional.of(new User(21L, "Rajesh", new Date(System.currentTimeMillis()), "Bangalore", "9060544800")));

		User user = userService.getUserById(21L);

		assertNotNull(user);
		assertNotEquals(1, user.getId());
	}


	/**
	 * Test case for adding new user
	 */
	@Test
	public void createUserByIdTest()	{

		UserDTO userDto = new UserDTO(null, "Sharan", new Date(System.currentTimeMillis()), "Bangalore", "9060544800");
		User user = new User(21L, "Sharan", new Date(System.currentTimeMillis()), "Bangalore", "9060544800");

		when(userRepository.saveAndFlush(any(User.class))).thenReturn(user);

		User newUser = userService.addNewUserEntry(userDto);

		assertNotNull(newUser);
		assertNotEquals(null, newUser.getId());
	}


	/**
	 * Test case for adding new user
	 */
	@Test
	public void updateUserTest()	{

		UserDTO userDto = new UserDTO(2L, "Sham", new Date(System.currentTimeMillis()), "Bangalore", "9060544800");
		User user = new User(2L, "Sham Rao", new Date(System.currentTimeMillis()), "Bangalore", "9060544800");

		when(userRepository.existsById(any(Long.class))).thenReturn(true);
		when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));
		when(userRepository.saveAndFlush(any(User.class))).thenReturn(user);

		User updateUser = userService.updateExistingUser(2L, userDto);

		assertNotNull(updateUser);
		assertNotEquals(21L, updateUser.getId());
	}


	/**
	 * Test case for adding new user
	 */
	@Test
	public void deleteUserByIdTest()	{

		User user = new User(31L, "Devendra", new Date(System.currentTimeMillis()), "Bangalore", "9060544800");

		when(userRepository.findById(any(Long.class))).thenReturn(Optional.of(user));

		User deletedUser = userService.deleteUser(31L);

		verify(userRepository).deleteById(any(Long.class));

		assertNotNull(deletedUser);
		assertEquals(31L, deletedUser.getId());
	}
}
