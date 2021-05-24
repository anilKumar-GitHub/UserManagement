package com.users;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * 
 * User management application is to demonstrate the CRUD operation.
 * Rest end-points documentation is provided using SWAGGER API.
 * User service is implemented using Interface so that it can have different implementation in future.
 * 
 * @author anilKumar
 *
 */
@SpringBootApplication
public class UserManagementApplication {

	public static void main(String[] args) {
	
		SpringApplication.run(UserManagementApplication.class, args);
	}
}
