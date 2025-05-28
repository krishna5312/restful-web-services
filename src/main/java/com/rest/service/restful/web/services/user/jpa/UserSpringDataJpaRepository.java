package com.rest.service.restful.web.services.user.jpa;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.service.restful.web.services.user.User;

public interface UserSpringDataJpaRepository extends JpaRepository<User, Integer> {
	
	Optional<User> getUserById(int id);

}
