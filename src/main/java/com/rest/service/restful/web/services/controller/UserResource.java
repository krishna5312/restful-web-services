package com.rest.service.restful.web.services.controller;

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
import com.rest.service.restful.web.services.post.Post;
import com.rest.service.restful.web.services.post.jpa.PostJpaRepository;
import com.rest.service.restful.web.services.user.User;
import com.rest.service.restful.web.services.user.jpa.UserSpringDataJpaRepository;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/users")
public class UserResource {

	// private UserDaoService userService;

	private UserSpringDataJpaRepository userRepository;

	private PostJpaRepository postRepository;

	public UserResource(UserSpringDataJpaRepository jpaRepository, PostJpaRepository postRepository) {
		this.userRepository = jpaRepository;
		this.postRepository = postRepository;
	}

	@GetMapping
	public ResponseEntity<List<User>> retrieveAllUsers() {
		return new ResponseEntity<>(userRepository.findAll(), HttpStatus.OK);
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
		User addedUser = userRepository.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(addedUser.getId())
				.toUri();
		return new ResponseEntity(HttpStatus.CREATED).created(location).build();
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<User> removeUser(@PathVariable int id) {
		userRepository.deleteById(id);
		return new ResponseEntity<User>(HttpStatus.ACCEPTED);
	}

	@GetMapping("/{userId}/posts")
	public Set<Post> getAllPostsMadeByUser(@PathVariable int userId) throws UserNotFoundException {
		User user = getUserWithUserId(userId);

		return user.getPosts();

	}

	private User getUserWithUserId(int userId) throws UserNotFoundException {
		User user = userRepository.findById(userId).orElse(null);
		if (user != null) {
			return user;
		} else {
			throw new UserNotFoundException("User with the " + userId + " Not found");
		}
	}

	@GetMapping("/{userId}/posts/{postId}")
	public Post getPostWithPostId(@PathVariable int userId, @PathVariable int postId) throws UserNotFoundException {
		getUserWithUserId(userId);
		return postRepository.findById(postId).orElse(null);

	}

	@PostMapping("/{userId}/posts")
	public ResponseEntity<User> addPostToUser(@PathVariable int userId, @RequestBody Post post)
			throws UserNotFoundException {
		User user = getUserWithUserId(userId);
		
		post.setUser(user);
		Post addedPost = postRepository.saveAndFlush(post);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}/posts")
				.buildAndExpand(addedPost.getId()).toUri();
		return new ResponseEntity<User>(HttpStatus.CREATED).created(location).build();
	}

}
