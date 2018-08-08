/**
 * 
 */
package com.demo.lombok.withoutlombok.model;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

/**
 * @author resulav
 *
 */
public class UserTests {

	private final Long id = 1L;
	private final String name = "dummyName";

	private User user;

	@Before
	public void setup() {
		user = new User();
		user.setId(id);
		user.setName(name);
	}

	@Test
	public void testConstructorNoParam() {
		Assert.assertNotNull("user can not be null", new User());
	}

	@Test
	public void testConstructorNotNullParams() {
		Assert.assertNotNull("user can not be null", new User(RandomUtils.nextLong()));
	}

	@Test
	public void testConstructorAllParam() {
		Assert.assertNotNull("user can not be null",
				new User(RandomUtils.nextLong(), RandomStringUtils.randomAlphanumeric(10)));
	}

	@Test
	public void testGetterSetter() {
		Assert.assertNotNull("user can not be null", user);
		Assert.assertEquals("user.id mismatched", id, user.getId());
		Assert.assertEquals("user.name mismatched", name, user.getName());
	}

    @Test
    public void testToString() {
        User userNew = new User(id, name);
        Assert.assertEquals("user.toString mismatched", user.toString(), userNew.toString());
    }

    @Test
    public void testToStringNotSame() {
	    User userNew = new User(RandomUtils.nextLong(), RandomStringUtils.randomAlphanumeric(10));
        Assert.assertNotEquals("user.toString same", user.toString(), userNew.toString());
    }

    @Test
    public void testHashCode() {
        User userNew = new User(id, name);
        Assert.assertEquals("user.hashCode mismatched", user.hashCode(), userNew.hashCode());
    }

    @Test
    public void testHashCodeNotSame() {
        User userNew = new User(RandomUtils.nextLong(), RandomStringUtils.randomAlphanumeric(10));
        Assert.assertNotEquals("user.hashCode same", user.hashCode(), userNew.hashCode());
    }

	@Test
	public void testUserBuilder() {
		User user = User.newBuilder().id(RandomUtils.nextLong()).name(RandomStringUtils.randomAlphanumeric(10)).build();
		Assert.assertNotNull("user can not be null", user);
	}

	@Test(expected = NullPointerException.class)
	public void testUserBuilderNullId() {
		User.newBuilder().name(RandomStringUtils.randomAlphanumeric(10)).build();
	}

}
