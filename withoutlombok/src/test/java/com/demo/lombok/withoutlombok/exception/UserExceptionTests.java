/**
 * 
 */
package com.demo.lombok.withoutlombok.exception;

import org.junit.Assert;
import org.junit.Test;

import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;

/**
 * @author resulav
 *
 */
public class UserExceptionTests {

	@Test(expected = UserException.class)
	public void testConstructorAll() {
		UserException exception = new UserException("Dummy", new RuntimeException("runtime"));
		Assert.assertNotNull("UserException can not be null", exception);
		throw exception;
	}

	@Test(expected = UserException.class)
	public void testConstructor() {
		UserException exception = new UserException("Dummy");
		Assert.assertNotNull("UserException can not be null", exception);
		throw exception;
	}

	@Test
	public void testConstructorAuto() {
		Assertions.assertPojoMethodsFor(UserException.class).testing(Method.CONSTRUCTOR).areWellImplemented();
	}
}
