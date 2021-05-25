package com.users.providers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.users.models.dtos.UserDTO;

public class UserServiceDataProvider {

	public static List<UserDTO> getUserList() {

		List<UserDTO> list = new ArrayList<UserDTO>();
		
		list.add(new UserDTO(101L, "Karna", new Date(System.currentTimeMillis()), "Bangalore", "987654210"));
		list.add(new UserDTO(102L, "Surya", new Date(System.currentTimeMillis()), "Delhi", "98765433211"));
		list.add(new UserDTO(103L, "Chandra", new Date(System.currentTimeMillis()), "Mumbai", "9876543212"));
		
		return list;
	}
	
	
	public static UserDTO getUser()	{
		return new UserDTO(104L, "Rajesh", new Date(System.currentTimeMillis()), "Bangalore", "9876543215");
	}


	public static UserDTO getNewUser() {
		// TODO Auto-generated method stub
		return new UserDTO(105L, "Sharan", new Date(System.currentTimeMillis()), "Bangalore", "9876543216");
	}


	public static UserDTO getExistingUser() {
		// TODO Auto-generated method stub
		return getUserList().get(0);
	}


	public static UserDTO getUpdatedUser() {
		// TODO Auto-generated method stub
		return new UserDTO(100L, "Karna Kumar", new Date(System.currentTimeMillis()), "Bangalore", "9060544800");
	}
}
