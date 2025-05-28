package com.rest.service.restful.web.services.user.dao;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.function.Predicate;

import org.springframework.stereotype.Component;

import com.rest.service.restful.web.services.user.User;

@Component
public class UserDaoService {

	static List<User> users = new ArrayList<>();

	static int count = 0;
	static {
		users.add(new User(++count, "chaitanya", LocalDate.now().minusYears(30L)));
		users.add(new User(++count, "Krishna", LocalDate.now().minusYears(25L)));
		users.add(new User(++count, "Riaz", LocalDate.now().minusYears(35L)));
	}

	public List<User> getUsers() {
		return users;
	}

	public User findOne(int id) {
		return users.stream().filter(user -> user.getId() == id).findFirst().orElse(null);
	}

	public User saveUser(User user) {
		user.setId(++count);
		users.add(user);
		return user;
	}

	public void deleteUser(int id) {
		Predicate<User> findUser = user -> (user.getId() == id);
		users.removeIf(findUser);
		
	}

}
