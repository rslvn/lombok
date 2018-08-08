/**
 * 
 */
package com.demo.lombok.withoutlombok.model;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;

/**
 * @author resulav
 *
 */
public class UserValueTest {
	@Test
	public void testUserData() {
		Assertions.assertPojoMethodsFor(UserValue.class)
				.testing(Method.CONSTRUCTOR, Method.GETTER, Method.EQUALS, Method.HASH_CODE,Method.TO_STRING)
				.areWellImplemented();
	}
}
