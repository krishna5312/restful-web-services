package com.rest.service.restful.web.services.service;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import com.rest.service.restful.web.services.user.User;
import com.rest.service.restful.web.services.user.jpa.UserSpringDataJpaRepository;

@Service
public class UserService {
	
	private UserSpringDataJpaRepository userRepository;

	public UserService(UserSpringDataJpaRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User getUserById(int userId) {
		return userRepository.getUserById(userId).orElseThrow(()->new ResourceNotFoundException("user with User Id "+userId +" doesn't exists"));
	}
	
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
    public User addUser(User user) {
    	return userRepository.save(user);
    }
}
