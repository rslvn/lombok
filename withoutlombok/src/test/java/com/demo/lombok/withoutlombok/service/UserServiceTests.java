/**
 * 
 */
package com.demo.lombok.withoutlombok.service;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import java.util.stream.IntStream;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import com.demo.lombok.withoutlombok.model.User;

/**
 * @author resulav
 *
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class UserServiceTests {

	private static final Logger LOG = LoggerFactory.getLogger(UserServiceTests.class);

	@Autowired
	private UserService userService;

	@Test
	public void test() throws InterruptedException {
		ExecutorService executorService = Executors.newFixedThreadPool(5);
		IntStream.rangeClosed(1, 10).forEach(i -> executorService.execute(() -> LOG.info("Size of list: {} for {}",
				(userService == null ? null : userService.getUserList().size()), i)));

		TimeUnit.SECONDS.sleep(1);
		executorService.shutdownNow();
	}

	@Test
	public void testGetUser() {
		User user = userService.getUser();
		Assert.assertNotNull("user can not be null", user);
	}

	@Test
	public void testGetUserAllParam() {
		User user = userService.getUserAllParam();
		Assert.assertNotNull("user can not be null", user);
	}

	@Test
	public void testGetUserWithBuilder() {
		User user = userService.getUserWithBuilder();
		Assert.assertNotNull("user can not be null", user);
	}

	@Test
	public void testEqualsAndHashCode() {
		final User user1 = userService.getUserWithBuilder();
		final User user2 = User.newBuilder().id(user1.getId()).name(user1.getName()).build();
		boolean same = user1.equals(user2) && user1.hashCode() == user2.hashCode();

		Assert.assertTrue("equalsAndHashCode ", same);
	}

	@Test
	public void testLastScheduleTime() throws InterruptedException {
		long first = userService.getLastScheduleTime();
		TimeUnit.MILLISECONDS.sleep(1010);
		long second = userService.getLastScheduleTime();

		Assert.assertTrue("lastScheduleTime is 0", second > first);
	}

	@Test
	public void testPutUser() throws InterruptedException, IOException {
		User user = userService.getUserWithBuilder();
		userService.putUser(user);
		List<User> userList = userService.getUserList();
		LOG.info("list size: {}", userList.size());
		Assert.assertTrue("User not exist", userList.contains(user));
	}

}
