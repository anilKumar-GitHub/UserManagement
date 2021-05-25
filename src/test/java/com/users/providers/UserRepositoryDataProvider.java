package com.users.providers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.users.models.dtos.UserDTO;
import com.users.models.entities.User;

public class UserRepositoryDataProvider {

	public static List<User> getUserList() {

		List<User> list = new ArrayList<User>();
		
		list.add(new User(101L, "Karna", new Date(System.currentTimeMillis()), "Bangalore", "987654210"));
		list.add(new User(102L, "Surya", new Date(System.currentTimeMillis()), "Delhi", "98765433211"));
		list.add(new User(103L, "Chandra", new Date(System.currentTimeMillis()), "Mumbai", "9876543212"));
		
		return list;
	}
	
	
	public static User getUser()	{
		return new User(104L, "Rajesh", new Date(System.currentTimeMillis()), "Bangalore", "9876543215");
	}


	public static User getNewUser() {
		// TODO Auto-generated method stub
		return new User(105L, "Sharan", new Date(System.currentTimeMillis()), "Bangalore", "9876543216");
	}


	public static User getExistingUser() {
		// TODO Auto-generated method stub
		return getUserList().get(0);
	}


	public static User getUpdatedUser() {
		// TODO Auto-generated method stub
		return new User(100L, "Karna Kumar", new Date(System.currentTimeMillis()), "Bangalore", "9060544800");
	}
}
