package com.users.model.entity;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

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
	
	@Column
    private String name;

	@Column(name = "dob")
	@Basic
	@Temporal(TemporalType.DATE)
	private Date dateOfBirth;

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
	public User(Long id, String name, Date dob, String mobileNum, String city)	{
		
		this.id = id;
		this.name = name;
		this.dateOfBirth = dob;
		this.mobileNumber = mobileNum;
		this.city = city;
	}
}
