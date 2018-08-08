/**
 * 
 */
package com.demo.lombok.withoutlombok.contoller;

import java.io.IOException;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.demo.lombok.withoutlombok.model.User;
import com.demo.lombok.withoutlombok.service.UserService;

/**
 * @author resulav
 *
 */

@RestController
@RequestMapping(UserController.SERVICE)
public class UserController {

	public static final String SERVICE = "user";
	public static final String METHOD_USER_LIST = "list";

	private static final Logger LOG = LoggerFactory.getLogger(UserController.class);
	private static final String LOG_METHOD_CALLED = "{} called";

	@Autowired
	private UserService userService;

	@RequestMapping(value = METHOD_USER_LIST, method = RequestMethod.GET)
	public ResponseEntity<List<User>> list() {
		LOG.info(LOG_METHOD_CALLED, METHOD_USER_LIST);
		return ResponseEntity.ok(userService.getUserList());
	}

	@RequestMapping(value = "/", method = RequestMethod.PUT)
	public ResponseEntity<Void> put(@RequestBody User user) throws InterruptedException, IOException {
		LOG.info(LOG_METHOD_CALLED, "put");
		userService.putUser(user);
		return new ResponseEntity<>(HttpStatus.OK);
	}

}
