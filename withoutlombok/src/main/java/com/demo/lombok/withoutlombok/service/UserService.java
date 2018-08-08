package com.demo.lombok.withoutlombok.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.demo.lombok.withoutlombok.model.User;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

/**
 * Created by resulav on 07.08.2018.
 */
@Service
public class UserService {

	private static final Logger LOG = LoggerFactory.getLogger(UserService.class);
	private static final String LOG_FORMATTER = "{}: {}";
	private final AtomicReference<List<User>> userList = new AtomicReference<List<User>>();

	private long lastScheduleTime = 0;

	public User getUser() {
		User user = new User();
		user.setId(RandomUtils.nextLong());
		user.setName(RandomStringUtils.randomAlphanumeric(10));
		return user;
	}

	public User getUserAllParam() {
		return new User(RandomUtils.nextLong(), RandomStringUtils.randomAlphanumeric(10));
	}

	public User getUserWithBuilder() {
		return User.newBuilder().id(RandomUtils.nextLong()).name(RandomStringUtils.randomAlphanumeric(10)).build();
	}

	public List<User> getUserList() {
		List<User> value = this.userList.get();
		if (value == null) {
			synchronized (this.userList) {
				value = this.userList.get();
				if (value == null) {
					final List<User> actualValue = loadUsers();
					value = actualValue == null ? null : actualValue;
					this.userList.set(value);
				}
			}
		}

		return value;
	}

	/**
	 * a dummy method with declared exception
	 * 
	 * @param user
	 * @throws InterruptedException
	 * @throws IOException
	 */
	public void putUser(User user) {
		Preconditions.checkArgument(user != null, "user can not be null");
		Preconditions.checkArgument(user.getId() != null, "user.id can not be null");
		getUserList().add(user);
		LOG.debug("New size: {}", getUserList().size());
		// this is just for exception
		try {
			TimeUnit.MILLISECONDS.sleep(1);
		} catch (InterruptedException e) {
			LOG.error("error while sleeping", e);
		}

		// this is just for exception
		Path path = Paths.get(RandomStringUtils.randomAlphanumeric(10));
		if (path.toFile().exists()) {
			try {
				Files.delete(path);
			} catch (IOException e) {
				LOG.error("error while delete file", e);
			}
		}
	}

	private List<User> loadUsers() {
		LOG.info("loadUsers called");
		List<User> userList = Lists.newArrayList();
		for (int i = 0; i < 100000; i++) {
			userList.add(getUserWithBuilder());
		}
		LOG.info("{} users loaded", userList.size());
		return userList;
	}

	@Scheduled(fixedRate = 1000)
	private void schedule() {
		LOG.info("------------");
		LOG.info(LOG_FORMATTER, "getUser", getUser().toString());
		LOG.info(LOG_FORMATTER, "getUserAllParam", getUserAllParam().toString());
		LOG.info(LOG_FORMATTER, "getUserWithBuilder", getUserWithBuilder().toString());
		lastScheduleTime = Instant.now().toEpochMilli();
	}

	/**
	 * @return the lastScheduleTime
	 */
	public long getLastScheduleTime() {
		return lastScheduleTime;
	}
}
