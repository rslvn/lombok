package com.demo.lombok.withlombok;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.lombok.withlombok.service.UserService;

@RunWith(SpringRunner.class)
@SpringBootTest
public class WithlombokApplicationTest {

	@Autowired
	private UserService userService;

	@Test
	public void contextLoads() {
		Assert.assertNotNull("userService can not be null", userService);
	}

}
