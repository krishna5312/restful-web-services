package com.rest.service.restful.web.services.social.media.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.rest.service.restful.web.services.social.media.entity.User;

public interface UserRepository extends JpaRepository<User, Long> {
	
	Optional<User> getUserById(Long id);

}
