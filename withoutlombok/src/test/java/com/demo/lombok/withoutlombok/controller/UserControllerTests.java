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
import org.mockito.MockitoAnnotations;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

import com.demo.lombok.withoutlombok.contoller.UserController;
import com.demo.lombok.withoutlombok.contoller.UserControllerAdvice;
import com.demo.lombok.withoutlombok.exception.UserException;
import com.demo.lombok.withoutlombok.model.User;
import com.demo.lombok.withoutlombok.service.UserService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.google.common.collect.Lists;

/**
 * @author resulav
 *
 */
@RunWith(SpringRunner.class)
public class UserControllerTests {

	private ObjectMapper mapper;
	private TypeFactory typeFactory;

	private String methodList;
	private String methodPut;

	private User user;
	private String userJson;

	private MockMvc mockMvc;

	@Mock
	private UserService userService;

	@InjectMocks
	private UserController userController;

	@Before
	public void setup() throws JsonProcessingException {
		MockitoAnnotations.initMocks(this);
		mockMvc = MockMvcBuilders.standaloneSetup(userController).setControllerAdvice(UserControllerAdvice.class)
				.build();

		mapper = new ObjectMapper();
		typeFactory = mapper.getTypeFactory();

		methodList = String.format("/%s/%s", UserController.SERVICE, UserController.METHOD_USER_LIST);
		methodPut = String.format("/%s/", UserController.SERVICE);

		user = User.newBuilder().id(1l).name("testPutUser").build();
		userJson = mapper.writeValueAsString(user);
	}

	@Test
	public void testList() throws Exception {
		Mockito.when(userService.getUserList())
				.thenReturn(Lists.newArrayList(User.newBuilder().id(1l).name("dummyname").build()));

		MvcResult result = mockMvc.perform(get(methodList).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andReturn();
		Assert.assertNotNull("mvc result can not be null", result);
		Assert.assertNotNull("mvc result.response can not be null", result.getResponse());

		List<User> userList = toUserList(result.getResponse().getContentAsString());
		Assert.assertNotNull("userList can not be null", userList);
		Assert.assertTrue("userList is empty", !userList.isEmpty());
	}

	@Test
	public void testListUserException() throws Exception {
		Mockito.when(userService.getUserList()).thenThrow(new UserException("Dummy Exception"));
		mockMvc.perform(get(methodList).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isServiceUnavailable());
	}

	@Test
	public void testListIllegalArgumentException() throws Exception {
		Mockito.when(userService.getUserList())
				.thenThrow(new IllegalArgumentException("Dummy IllegalArgumentException"));
		mockMvc.perform(get(methodList).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testListIllegalStateException() throws Exception {
		Mockito.when(userService.getUserList()).thenThrow(new IllegalStateException("Dummy IllegalStateException"));
		mockMvc.perform(get(methodList).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testListException() throws Exception {
		Mockito.when(userService.getUserList()).thenThrow(new RuntimeException("Dummy RuntimeException"));
		mockMvc.perform(get(methodList).contentType(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isInternalServerError());
	}

	@Test
	public void testPutUser() throws Exception {
		Mockito.doNothing().when(userService).putUser(user);

		mockMvc.perform(put(methodPut).contentType(MediaType.APPLICATION_JSON).content(userJson)).andDo(print())
				.andExpect(status().isOk());
	}

	@Test
	public void testPutUserIllegalArgumentException() throws Exception {
		Mockito.doThrow(new IllegalArgumentException("testPutUserIllegalArgumentException")).when(userService)
				.putUser(user);

		mockMvc.perform(put(methodPut).contentType(MediaType.APPLICATION_JSON).content(userJson)).andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testPutUserIllegalStateException() throws Exception {
		Mockito.doThrow(new IllegalStateException("testPutUserIllegalStateException")).when(userService).putUser(user);

		mockMvc.perform(put(methodPut).contentType(MediaType.APPLICATION_JSON).content(userJson)).andDo(print())
				.andExpect(status().isBadRequest());
	}

	@Test
	public void testPutUserUserException() throws Exception {
		Mockito.doThrow(new UserException("testPutUserUserException")).when(userService).putUser(user);

		mockMvc.perform(put(methodPut).contentType(MediaType.APPLICATION_JSON).content(userJson)).andDo(print())
				.andExpect(status().isServiceUnavailable());
	}

	@Test
	public void testPutUserException() throws Exception {
		Mockito.doThrow(new RuntimeException("testPutUserException")).when(userService).putUser(user);

		mockMvc.perform(put(methodPut).contentType(MediaType.APPLICATION_JSON).content(userJson)).andDo(print())
				.andExpect(status().isInternalServerError());
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
