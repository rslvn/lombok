/**
 * 
 */
package com.demo.lombok.withlombok.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.lombok.withlombok.model.User;
import com.demo.lombok.withlombok.service.UserService;

import lombok.extern.slf4j.Slf4j;

/**
 * @author resulav
 *
 */

@RestController
@RequestMapping(UserController.SERVICE)
@Slf4j
public class UserController {

	static final String SERVICE = "user";
	static final String METHOD_USER_LIST = "list";

	private static final String LOG_METHOD_CALLED = "{} called";

	@Autowired
	private UserService userService;

	@RequestMapping(value = METHOD_USER_LIST, method = RequestMethod.GET)
	public ResponseEntity<List<User>> list() {
		log.info(LOG_METHOD_CALLED, METHOD_USER_LIST);
		return ResponseEntity.ok(userService.getUserList());
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Void> put(@RequestBody User user) {
		log.info(LOG_METHOD_CALLED, "put");
		userService.putUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
