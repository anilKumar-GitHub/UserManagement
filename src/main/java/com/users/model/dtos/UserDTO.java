package com.users.model.dtos;

import java.util.Date;

import com.users.controllers.UserController;
import com.users.model.entities.User;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Builder;
import lombok.Data;

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
public class UserDTO extends AbstractDTO {

    @ApiModelProperty(notes = "The unique id of users.")
    private Long id;
    
    @ApiModelProperty(notes = "Name of users.")
    private String name;
    
    @ApiModelProperty(notes = "Date of Birt in formate [YYYY-MM-DD].")
	private Date dateOfBirth;
    
    @ApiModelProperty(notes = "Current location of users.")
	private String city;
    
    @ApiModelProperty(notes = "Mobile number need to be unique per user.")
	private String mobileNumber;

	public UserDTO()	{
		
	}
	
	public UserDTO(Long id, String name, Date dateOfBirht, String city, String mobileNumber)	{
		
		this.id = id;
		this.name = name;
		this.dateOfBirth = dateOfBirht;
		this.city = city;
		this.mobileNumber = mobileNumber;
	}
	
	public static UserDTO mapToUserDTO(User user) {
		
		return UserDTO.builder()
			.id(user.getId())
			.name(user.getName())
			.dateOfBirth(user.getDateOfBirth())
			.city(user.getCity())
			.mobileNumber(user.getMobileNumber())
			.build();
	}	
}
