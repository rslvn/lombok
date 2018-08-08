/**
 * 
 */
package com.demo.lombok.withoutlombok.model;

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
public class UserPojoTest {

	@Test
	public void testUser() {
		Assertions.assertPojoMethodsFor(User.class).testing(Method.values()).areWellImplemented();
	}

	@Test
	public void testUserBuilder() {
		User user=User.newBuilder().id(RandomUtils.nextLong()).name(RandomStringUtils.randomAlphanumeric(10)).build();
		Assert.assertNotNull("user can not be null",user);
	}

	@Test(expected = NullPointerException.class)
	public void testUserBuilderNullId() {
		User.newBuilder().name(RandomStringUtils.randomAlphanumeric(10)).build();
	}

}
