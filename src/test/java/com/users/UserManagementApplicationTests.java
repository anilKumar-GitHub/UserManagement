package com.users;

import static org.junit.Assert.assertTrue;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.io.IOException;
import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.users.model.dto.UserDTO;


/**
 * JUnit Test case for all CRUD operations.
 * 
 * @author anilKumar
 *
 */
@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = UserManagementApplication.class)
@WebAppConfiguration
public class UserManagementApplicationTests	{

	protected MockMvc mvc;

	@Autowired
	WebApplicationContext webApplicationContext;


	@Before
	public void setUp() throws Exception {

		mvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}

	protected String mapToJson(Object obj) throws JsonProcessingException {
		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.writeValueAsString(obj);
	}

	protected <T> T mapFromJson(String json, Class<T> clazz)
			throws JsonParseException, JsonMappingException, IOException {

		ObjectMapper objectMapper = new ObjectMapper();
		return objectMapper.readValue(json, clazz);
	}


	/**
	 * Test case for get all users
	 * 
	 * @throws Exception
	 */
	@Test
	public void getUsersList() throws Exception {

		String uri = "/users";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		UserDTO[] usersList = mapFromJson(content, UserDTO[].class);

		assertEquals(200, status);
		assertTrue(usersList.length > 0);
	}


	/**
	 * Test case for user by id
	 * 
	 * @throws Exception
	 */
	@Test
	public void getUsersById() throws Exception {

		String uri = "/users/1";
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
				.accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		UserDTO user = mapFromJson(content, UserDTO.class);

		assertEquals(200, status);
		assertEquals(user.getName(), "Ram");
	}


	/**
	 * Test case for creating user.
	 * 
	 * @throws Exception
	 */
	@Test
	public void createUsersEntries() throws Exception	{

		String uri = "/users";
		UserDTO dto = UserDTO.builder().name("Rajesh")
				.dateOfBirth(new Date()).city("Gulbarga")
				.mobileNumber("9060544810").build();

		String inputJSON = this.mapToJson(dto);
		MvcResult mvcResult = this.mvc.perform(
				MockMvcRequestBuilders.post(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJSON)).andReturn();


		int status = mvcResult.getResponse().getStatus();
		String content = mvcResult.getResponse().getContentAsString();
		UserDTO user = mapFromJson(content, UserDTO.class);

		assertEquals(201, status);
		assertEquals("Rajesh", user.getName());
		assertEquals("9060544810", user.getMobileNumber());
	}


	/**
	 * Testing case for updating user.
	 * 
	 * @throws Exception
	 */
	@Test
	public void updateProduct() throws Exception {

		String uri = "/users/3";

		UserDTO user = new UserDTO();
		user.setName("Sharan");
		String inputJson = mapToJson(user);

		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE)
				.content(inputJson)).andReturn();

		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	}


	/**
	 * Test case for deleting user from list.
	 * 
	 * @throws Exception
	 */
	@Test
	public void deleteUser() throws Exception {

		String uri = "/users/4";
		
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)).andReturn();
		int status = mvcResult.getResponse().getStatus();
		
		String content = mvcResult.getResponse().getContentAsString();
		UserDTO user = mapFromJson(content, UserDTO.class);

		assertEquals(200, status);
		assertEquals(user.getName(), "Dev");
	}
}
