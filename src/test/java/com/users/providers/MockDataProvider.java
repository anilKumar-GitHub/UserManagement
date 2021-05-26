package com.users.providers;

import java.util.List;
import java.util.stream.Collectors;

import com.users.models.dtos.UserDTO;
import com.users.models.entities.User;

public class MockDataProvider {

	public static List<User> getUserList() {
		return List.of(
			new User(101L, "Karna", "Sharma", "Bangalore", "987654210"),
			new User(102L, "Surya", "Patil", "Delhi", "98765433211"),
			new User(103L, "Chandra", "Maulya", "Mumbai", "9876543212")
		);
	}
	
	public static List<UserDTO> getUserDTOList()	{
		
		return getUserList().stream().map(UserDTO::mapToUserDTO).collect(Collectors.toList());
	}
	
	
	public static User getUser()	{
		return new User(104L, "Rajesh", "Puri", "Bangalore", "9876543215");
	}

	public static UserDTO getUserDTO()	{
		return UserDTO.mapToUserDTO(getUser());
	}

	public static User getNewUser() {
		// TODO Auto-generated method stub
		return new User(105L, "Sharan", "Hebbal", "Bangalore", "9876543216");
	}

	public static UserDTO getNewUserDTO() {
		// TODO Auto-generated method stub
		return UserDTO.mapToUserDTO(getNewUser());
	}

	public static User getExistingUser() {
		// TODO Auto-generated method stub
		return getUserList().get(0);
	}

	public static UserDTO getExistingUserDTO() {
		// TODO Auto-generated method stub
		return UserDTO.mapToUserDTO(getExistingUser());
	}

	public static User getUpdatedUser() {
		// TODO Auto-generated method stub
		return new User(100L, "Karna Kumar", "Sharma", "Bangalore", "9060544800");
	}

	public static UserDTO getUpdatedUserDTO() {
		// TODO Auto-generated method stub
		return UserDTO.mapToUserDTO(getUpdatedUser());
	}
}
