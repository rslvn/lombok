/**
 * 
 */
package com.demo.lombok.withlombok.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Test;

import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;

/**
 * @author resulav
 *
 */
public class UserTests {

	@Test
	public void testUser() {
		Assertions.assertPojoMethodsFor(User.class).testing(Method.values()).areWellImplemented();
	}

	@Test
	public void testUserBuilder() {
		User user=User.builder().id(RandomUtils.nextLong()).name(RandomStringUtils.randomAlphanumeric(10)).build();
		Assert.assertNotNull("user can not be null",user);
	}

	@Test(expected = NullPointerException.class)
	public void testUserBuilderNullId() {
		User.builder().name(RandomStringUtils.randomAlphanumeric(10)).build();
	}

}
