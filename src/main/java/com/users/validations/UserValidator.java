package com.users.validations;

import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

import com.users.models.dtos.UserDTO;
import com.users.utils.StringUtil;

/**
 * Validation class
 * 
 * @author anilKumar
 *
 */
@Component
public class UserValidator	implements	Validator	{

	@Override
	public boolean supports(Class<?> clazz) {
		return UserDTO.class.equals(clazz);
	}

	@Override
	public void validate(Object object, Errors errors) {

		/* Default validation for null or empty inputs */
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "firstName", "first name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "lastName", "last name is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "city", " city is required");
		ValidationUtils.rejectIfEmptyOrWhitespace(errors, "mobileNumber", "mobile number is required");
		
		UserDTO newUser = (UserDTO) object;

		String firstName = newUser.getFirstName();
		if(!StringUtil.isNullOrEmpty(firstName))		{
			if(firstName.length() > 100)	{
				errors.reject("firstName", "first name should not exceed.");
			}
		}
		
		String lastName = newUser.getLastName();
		if(!StringUtil.isNullOrEmpty(lastName))	{
			if(lastName.length() > 100)	{
				errors.reject("lastName", "Last name should not exceed.");
			}
		}
	}
}
