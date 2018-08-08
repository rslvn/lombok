/**
 * 
 */
package com.demo.lombok.withlombok.model;

import org.junit.Test;

import pl.pojo.tester.api.assertion.Assertions;
import pl.pojo.tester.api.assertion.Method;

/**
 * @author resulav
 *
 */
public class UserDataTests {
	@Test
	public void testUserData() {
		Assertions.assertPojoMethodsFor(UserData.class).testing(Method.values()).areWellImplemented();
	}
}
