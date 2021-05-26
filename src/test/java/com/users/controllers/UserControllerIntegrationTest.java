package com.users.controllers;


import static org.junit.Assert.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.users.models.dtos.UserDTO;
import com.users.providers.MockDataProvider;
import com.users.services.UserService;
import com.users.utils.StringUtil;

@RunWith(SpringRunner.class)
@WebMvcTest(value = UserController.class)
public class UserControllerIntegrationTest {

	
	@Autowired
	MockMvc mockMvc;

	@MockBean
	UserService userService;

	/** Mock Data provider is not getting eligible for auto-wire */
	MockDataProvider mockData = new MockDataProvider();
	
	@Test
	public void testGetAllUsers() throws Exception	{
		
		String URI = "/users";
		
		Mockito.when(userService.getAllUsers())
		.thenReturn(mockData.getUserDTOList());
		
		RequestBuilder builder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		
		assertNotNull(response.getContentAsString());
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	
	@Test
	public void testGetUsersById() throws Exception	{
		
		String URI = "/users/104";
		UserDTO mockedUser = mockData.getUserDTO();

		Mockito.when(userService.getUserById(Mockito.anyLong()))
		.thenReturn(mockedUser);
		
		RequestBuilder builder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		
		UserDTO expectedUser = StringUtil.mapFromJson(response.getContentAsString(), UserDTO.class);
		
		assertEquals(mockedUser, expectedUser);
		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	

	@Test
	public void testGetUsersByNameFilter() throws Exception	{
		
		String URI = "/users/fname/Ra";
		
		Mockito.when(userService.getUserByNameLike(Mockito.any()))
		.thenReturn(
				mockData.getUserDTOList().stream()
				.filter(f -> f.getFirstName().startsWith("Ra"))
				.collect(Collectors.toList())
			);
		
		RequestBuilder builder = MockMvcRequestBuilders.get(URI).accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}
	
	
	@Test
	public void testCreateUsers() throws Exception	{
		
		String URI = "/users";
		UserDTO mockedUser = mockData.getNewUserDTO();
		
		Mockito.when(userService.addNewUserEntry(mockedUser))
		.thenReturn(mockedUser);
		
		RequestBuilder builder = MockMvcRequestBuilders.post(URI)
				.accept(MediaType.APPLICATION_JSON)
				.content(StringUtil.mapToJson(mockedUser))
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		
		UserDTO expectedUser = StringUtil.mapFromJson(response.getContentAsString(), UserDTO.class);
		
		assertEquals(HttpStatus.CREATED.value(), response.getStatus());
		assertEquals(mockedUser, expectedUser);
	}	
	
	
	@Test
	public void testUpadteUser() throws Exception	{
		
		String URI = "/users/1";
		UserDTO mockedUser = mockData.getExistingUserDTO();
		mockedUser.setFirstName("Chandrakanth");
		
		Mockito.when(userService.updateExistingUser(Mockito.anyLong(), Mockito.any(UserDTO.class)))
		.thenReturn(mockedUser);
		
		RequestBuilder builder = MockMvcRequestBuilders.put(URI)
				.accept(MediaType.APPLICATION_JSON)
				.content(StringUtil.mapToJson(mockedUser))
				.contentType(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();
		
		//UserDTO updateUser = StringUtil.mapFromJson(response.getContentAsString(), UserDTO.class);
		
		assertEquals(HttpStatus.OK.value(), response.getStatus());
		//assertNotNull(mockedUser.getFirstName(), updateUser.getFirstName());
	}	
	
	
	@Test
	public void testDeleteUserById() throws Exception	{
		
		String URI = "/users/1";

		RequestBuilder builder = MockMvcRequestBuilders.delete(URI)
				.accept(MediaType.APPLICATION_JSON);
		
		MvcResult mvcResult = mockMvc.perform(builder).andReturn();
		MockHttpServletResponse response = mvcResult.getResponse();

		assertEquals(HttpStatus.OK.value(), response.getStatus());
	}	
}
