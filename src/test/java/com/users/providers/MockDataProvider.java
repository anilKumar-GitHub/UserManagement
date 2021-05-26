package com.users.providers;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Component;

import com.users.models.dtos.UserDTO;
import com.users.models.entities.User;

@Component
public class MockDataProvider {

	public List<User> getUserList() {
		return List.of(
			new User(101L, "Karna", "Sharma", "Bangalore", "987654210"),
			new User(102L, "Surya", "Patil", "Delhi", "98765433211"),
			new User(103L, "Chandra", "Maulya", "Mumbai", "9876543212")
		);
	}
	
	public List<UserDTO> getUserDTOList()	{
		
		return getUserList().stream().map(UserDTO::mapToUserDTO).collect(Collectors.toList());
	}
	
	
	public User getUser()	{
		return new User(104L, "Rajesh", "Puri", "Bangalore", "9876543215");
	}

	public UserDTO getUserDTO()	{
		return UserDTO.mapToUserDTO(getUser());
	}

	public User getNewUser() {
		// TODO Auto-generated method stub
		return new User(105L, "Sharan", "Hebbal", "Bangalore", "9876543216");
	}

	public UserDTO getNewUserDTO() {
		// TODO Auto-generated method stub
		return UserDTO.mapToUserDTO(getNewUser());
	}

	public User getExistingUser() {
		// TODO Auto-generated method stub
		return getUserList().get(0);
	}

	public UserDTO getExistingUserDTO() {
		// TODO Auto-generated method stub
		return UserDTO.mapToUserDTO(getExistingUser());
	}

	public User getUpdatedUser() {
		// TODO Auto-generated method stub
		return new User(100L, "Karna Kumar", "Sharma", "Bangalore", "9060544800");
	}

	public UserDTO getUpdatedUserDTO() {
		// TODO Auto-generated method stub
		return UserDTO.mapToUserDTO(getUpdatedUser());
	}
}
