package com.demo.lombok.withlombok.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.Instant;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.RandomUtils;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import com.demo.lombok.withlombok.model.User;
import com.google.common.base.Preconditions;
import com.google.common.collect.Lists;

import lombok.Getter;
import lombok.SneakyThrows;
import lombok.var;
import lombok.extern.slf4j.Slf4j;

/**
 * Created by resulav on 07.08.2018.
 */
@Service
@Slf4j
public class UserService {

	private static final String LOG_FORMATTER = "{}: {}";

	@Getter(lazy = true)
	private final List<User> userList = loadUsers();

	@Getter private long lastScheduleTime = 0;

	public User getUser() {
		var user = new User();
		user.setId(RandomUtils.nextLong());
		user.setName(RandomStringUtils.randomAlphanumeric(10));
		return user;
	}

	public User getUserAllParam() {
		return new User(RandomUtils.nextLong(), RandomStringUtils.randomAlphanumeric(10));
	}

	public User getUserWithBuilder() {
		return User.builder().id(RandomUtils.nextLong()).name(RandomStringUtils.randomAlphanumeric(10)).build();
	}

	private List<User> loadUsers() {
		log.info("loadUsers called");
		List<User> userList = Lists.newArrayListWithCapacity(100000);
		for (int i = 0; i < 100000; i++) {
			userList.add(getUserWithBuilder());
		}
		log.info("{} users loaded", userList.size());
		return userList;
	}

	/**
	 * a dummy method with declared exception
	 * 
	 * @param user
	 * @throws InterruptedException
	 * @throws IOException
	 */
	@SneakyThrows
	public void putUser(User user) {
		Preconditions.checkArgument(user != null, "user can not be null");
		Preconditions.checkArgument(user.getId() != null, "user.id can not be null");
		getUserList().add(user);
		log.debug("New size: {}", getUserList().size());
		// this is just for exception
		TimeUnit.MILLISECONDS.sleep(1);

		// this is just for exception
		Path path = Paths.get(RandomStringUtils.randomAlphanumeric(10));
		if (path.toFile().exists()) {
			Files.delete(path);
		}
	}
	
	@Scheduled(fixedRate = 1000)
	private void schedule() {
		log.info("------------");
		log.info(LOG_FORMATTER, "getUser", getUser().toString());
		log.info(LOG_FORMATTER, "getUserAllParam", getUserAllParam().toString());
		log.info(LOG_FORMATTER, "getUserWithBuilder", getUserWithBuilder().toString());
		lastScheduleTime = Instant.now().toEpochMilli();
	}
}
