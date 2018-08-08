/**
 * 
 */
package com.demo.lombok.withoutlombok.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.io.IOException;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;

import com.demo.lombok.withoutlombok.contoller.UserController;
import com.demo.lombok.withoutlombok.model.User;
import com.demo.lombok.withoutlombok.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;

/**
 * @author resulav
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class UserControllerIT {

	private ObjectMapper mapper;
	private TypeFactory typeFactory;

	private String methodList;
	private String methodPut;

	private User user;
	private String userJson;

	@Autowired
	private MockMvc mockMvc;

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Before
	public void setup() throws JsonProcessingException {
		mapper = new ObjectMapper();
		typeFactory = mapper.getTypeFactory();

		methodList = String.format("/%s/%s", UserController.SERVICE, UserController.METHOD_USER_LIST);
		methodPut = String.format("/%s/", UserController.SERVICE);

		user = User.newBuilder().id(1l).name("testPutUser").build();
		userJson = mapper.writeValueAsString(user);
	}

	@Test
	public void testList() throws Exception {
		MvcResult result = mockMvc.perform(get(methodList).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		Assert.assertNotNull("mvc result can not be null", result);
		Assert.assertNotNull("mvc result.response can not be null", result.getResponse());

		List<User> userList = toUserList(result.getResponse().getContentAsString());
		Assert.assertNotNull("userList can not be null", userList);
		Assert.assertTrue("userList is empty", !userList.isEmpty());
	}

	@Test
	public void testPutUser() throws Exception {
		mockMvc.perform(put(methodPut).contentType(MediaType.APPLICATION_JSON).content(userJson)).andDo(print())
				.andExpect(status().isOk());

		MvcResult result = mockMvc.perform(get(methodList).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		Assert.assertNotNull("mvc result can not be null", result);
		Assert.assertNotNull("mvc result.response can not be null", result.getResponse());

		List<User> userList = toUserList(result.getResponse().getContentAsString());
		Assert.assertNotNull("userList can not be null", userList);
		Assert.assertTrue("userList is empty", userList.contains(user));

	}

	@Test
	public void testPutUserIllegalArgumentException() throws Exception {
		Mockito.doThrow(new IllegalArgumentException("testPutUserIllegalArgumentException")).when(userService)
				.putUser(user);

		mockMvc.perform(put(methodPut).contentType(MediaType.APPLICATION_JSON).content("{}")).andDo(print())
				.andExpect(status().isBadRequest());
	}

	/**
	 * @param contentAsString
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	public List<User> toUserList(String contentAsString) throws JsonParseException, JsonMappingException, IOException {
		return mapper.readValue(contentAsString, typeFactory.constructCollectionType(List.class, User.class));
	}

}
