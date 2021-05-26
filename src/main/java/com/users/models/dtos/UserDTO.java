package com.users.models.dtos;

import com.users.controllers.UserController;
import com.users.models.entities.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.Getter;

/**
 * Request / Response object for REST call service.
 * 
 * @author anilKumar
 * 
 * @see UserController
 */
@Data
@Builder
@ApiModel(description = "Request/Response object for REST call service.")
public class UserDTO {

    @ApiModelProperty(notes = "The unique id of users.")
    private Long id;
    
    @ApiModelProperty(notes = "Fist name of the user.")
    private String firstName;

    @ApiModelProperty(notes = "Last name of the user.")
    private String lastName;

    @ApiModelProperty(notes = "Full name of the user.")    
    @Getter(value = AccessLevel.NONE)
    private String fullName;

    @ApiModelProperty(notes = "Current location of users.")
	private String city;
    
    @ApiModelProperty(notes = "Mobile number need to be unique per user.")
	private String mobileNumber;
    
	public UserDTO()	{
		
	}
	
	public UserDTO(Long id, String firstName, String lastName, String city, String mobileNumber)	{
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.mobileNumber = mobileNumber;
	}

	/* to avoid builder pattern error */
	public UserDTO(Long id, String firstName, String lastName, String fullName, String city, String mobileNumber)	{
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.mobileNumber = mobileNumber;
	}
	
	public static UserDTO mapToUserDTO(User user) {
		
		return UserDTO.builder()
			.id(user.getId())
			.firstName(user.getFirstName())
			.lastName(user.getLastName())
			.city(user.getCity())
			.fullName(user.getFirstName() + " " + user.getLastName())
			.mobileNumber(user.getMobileNumber())
			.build();
	}
	
	public String getFullName() {
		return fullName;
	}
}
