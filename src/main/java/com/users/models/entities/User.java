package com.users.models.entities;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;
/**
 * User entity which maps to user table in database
 * 
 * @author anilKumar
 *
 */
@Data
@Entity
@Table(name = "users")
public class User {
	
	/**
	 * user_table_seq is defined in schema.sql
	 */
	@Id
	@GeneratedValue(generator = "user_table_seq")
    private Long id;
	
	@Column(name = "FIRST_NAME")
    private String firstName;

	@Column(name = "LAST_NAME")
    private String lastName;

	@Column
	private String city;
	
	@Column(name = "mobile_num")
	private String mobileNumber;
	
	
	/**
	 * default constructor
	 */
	public User()	{
		
	}

	/**
	 * 
	 * @param id
	 * @param name
	 * @param dob
	 * @param mobileNum
	 * @param city
	 */
	public User(Long id, String firstName, String lastName, String city, String mobileNumber)	{
		
		this.id = id;
		this.firstName = firstName;
		this.lastName = lastName;
		this.city = city;
		this.mobileNumber = mobileNumber;
	}
}
