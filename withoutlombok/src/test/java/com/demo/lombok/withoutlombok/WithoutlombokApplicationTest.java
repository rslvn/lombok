/**
 * 
 */
package com.demo.lombok.withoutlombok;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.lombok.withoutlombok.service.UserService;

/**
 * @author resulav
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = WithoutlombokApplication.class)
public class WithoutlombokApplicationTest {

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
		Assert.assertNotNull("userService can not be null", userService);
	}
	
}
