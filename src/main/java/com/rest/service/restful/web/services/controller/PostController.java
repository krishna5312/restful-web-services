package com.rest.service.restful.web.services.controller;

import java.net.URI;

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
import com.rest.service.restful.web.services.service.UserPostsService;

@RestController
@RequestMapping("/users/{userId}/posts")
public class PostController {
	
	private UserPostsService postsService;
	
	public PostController(UserPostsService postsService) {
		super();
		this.postsService = postsService;
	}

	@GetMapping("/{postId}")
	public Post getPostWithPostId(@PathVariable int userId, @PathVariable int postId) throws UserNotFoundException {
		return postsService.getPostById(postId, userId);

	}

	@PostMapping
	public ResponseEntity<Post> addPostToUser(@PathVariable int userId, @RequestBody Post post)
			throws UserNotFoundException {
		Post addedPost = postsService.savePost(post, userId);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{userId}/posts")
				.buildAndExpand(addedPost.getId()).toUri();
		return new ResponseEntity<Post>(HttpStatus.CREATED).created(location).build();
	}	
	
	@DeleteMapping("{postId}")
	@ResponseStatus(HttpStatus.ACCEPTED)
	public void removePost(@PathVariable int userId,@PathVariable int postId){
		postsService.deletePost(postId, userId);
		
	}
	
	@DeleteMapping
	public void removeAllPostsOfAUser(@PathVariable int userId) {
		postsService.removeAllUserPosts(userId);
	}

}
