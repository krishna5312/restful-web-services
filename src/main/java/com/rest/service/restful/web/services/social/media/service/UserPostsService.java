package com.rest.service.restful.web.services.social.media.service;

import java.util.Collections;
import java.util.Set;

import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.restful.web.services.exception.UserNotFoundException;
import com.rest.service.restful.web.services.social.media.entity.Post;
import com.rest.service.restful.web.services.social.media.entity.User;
import com.rest.service.restful.web.services.social.media.repository.PostRepository;

@Service
public class UserPostsService {

	private PostRepository postRepository;

	private UserService userService;

	public UserPostsService(PostRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}

	@Transactional
	public Post getPostById(Long postId, Long userId) throws UserNotFoundException {
		User user = userService.getUserById(userId);
		Post post = postRepository.getPostById(postId)
				.orElseThrow(() -> new ResourceNotFoundException("Post with Id " + postId + " is not present"));
		if (user.getPosts().contains(post)) {
			return post;
		} else {
			throw new ResourceNotFoundException("User and Post Id don't match");
		}
	}

	@Transactional
	public Post savePost(Post post, Long userId) throws UserNotFoundException {
		User user = userService.getUserById(userId);
		post.setUser(user);
		return postRepository.save(post);
	}

	@Transactional
	public void deletePost(Long postId, Long userId) throws UserNotFoundException {
		Post post = getPostById(postId, userId);
		User user = post.getUser();
		user.getPosts().remove(post);
		postRepository.delete(post);

	}

	@Transactional
	public void removeAllUserPosts(Long userId) throws UserNotFoundException {
		User user = userService.getUserById(userId);
		Set<Post> userPosts = user.getPosts();
		user.setPosts(Collections.emptySet());
		postRepository.deleteAll(userPosts);
	}

}
