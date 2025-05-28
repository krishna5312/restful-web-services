package com.rest.service.restful.web.services.service;

import java.util.List;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.restful.web.services.exception.UserNotFoundException;
import com.rest.service.restful.web.services.user.User;
import com.rest.service.restful.web.services.user.jpa.UserSpringDataJpaRepository;

@Service
public class UserService {
	
	private UserSpringDataJpaRepository userRepository;

	public UserService(UserSpringDataJpaRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User getUserById(int userId) throws UserNotFoundException {
		return userRepository.getUserById(userId).orElseThrow(()->new UserNotFoundException("user with User Id "+userId +" doesn't exists"));
	}
	
	  @Transactional
	public List<User> getAllUsers(){
		return userRepository.findAll();
	}
	
	  @Transactional
    public User addUser(User user) {
    	return userRepository.save(user);
    }
    
    @Transactional
    public void deleteUser(int id) throws UserNotFoundException {
    	userRepository.getUserById(id).orElseThrow(()->new UserNotFoundException("user with User Id "+id +" doesn't exists"));
    	userRepository.deleteById(id);
    }
    
    @Transactional
    public User updateUser(User user) {
    	return userRepository.saveAndFlush(user);
    }
}
