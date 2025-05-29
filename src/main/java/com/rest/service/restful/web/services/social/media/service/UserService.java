package com.rest.service.restful.web.services.social.media.service;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.restful.web.services.exception.UserNotFoundException;
import com.rest.service.restful.web.services.social.media.entity.User;
import com.rest.service.restful.web.services.social.media.repository.UserRepository;

@Service
public class UserService {
	
	private UserRepository userRepository;

	public UserService(UserRepository userRepository) {
		this.userRepository = userRepository;
	}
	
	public User getUserById(Long userId) throws UserNotFoundException {
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
    public void deleteUser(Long id) throws UserNotFoundException {
    	userRepository.getUserById(id).orElseThrow(()->new UserNotFoundException("user with User Id "+id +" doesn't exists"));
    	userRepository.deleteById(id);
    }
    
    @Transactional
    public User updateUser(User user) {
    	return userRepository.saveAndFlush(user);
    }
}
