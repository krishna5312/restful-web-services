package com.rest.service.restful.web.services.social.media.controller;

import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.linkTo;

import java.net.URI;
import java.util.List;
import java.util.Set;

import org.springframework.hateoas.EntityModel;
import org.springframework.hateoas.server.mvc.WebMvcLinkBuilder;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.rest.service.restful.web.services.exception.UserNotFoundException;
import com.rest.service.restful.web.services.social.media.entity.Post;
import com.rest.service.restful.web.services.social.media.entity.User;
import com.rest.service.restful.web.services.social.media.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserController {

	// private UserDaoService userService;

	private UserService userService;

	public UserController(UserService userService) {
		this.userService = userService;
	}

	@GetMapping
	public ResponseEntity<List<User>> retrieveAllUsers() {
		return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public EntityModel<User> retrieveUserWithId(@PathVariable int id) throws UserNotFoundException {
		User user = getUserWithUserId(id);

		EntityModel<User> entityModel = EntityModel.of(user);
		WebMvcLinkBuilder link = linkTo(this.getClass(), retrieveAllUsers());
		entityModel.add(link.withRel("all-users"));
		return entityModel;

	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ResponseEntity<User> addUser(@RequestBody @Valid User user) {
		User addedUser = userService.addUser(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedUser.getId())
				.toUri();
		return new ResponseEntity(HttpStatus.CREATED).created(location).build();
	}

	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void removeUser(@PathVariable int id) throws UserNotFoundException {
		userService.deleteUser(id);
	}

	@GetMapping("/{userId}/posts")
	public Set<Post> getAllPostsMadeByUser(@PathVariable int userId) throws UserNotFoundException {
		User user = getUserWithUserId(userId);

		return user.getPosts();

	}

	private User getUserWithUserId(int userId) throws UserNotFoundException {
		return userService.getUserById(userId);
	}



}
