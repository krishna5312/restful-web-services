package com.rest.service.restful.web.services.service;

import java.util.Collections;
import java.util.Set;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.restful.web.services.post.Post;
import com.rest.service.restful.web.services.post.jpa.PostJpaRepository;
import com.rest.service.restful.web.services.user.User;

@Service
public class UserPostsService {
	
	private PostJpaRepository postRepository;
	
	private UserService userService;

	public UserPostsService(PostJpaRepository postRepository, UserService userService) {
		this.postRepository = postRepository;
		this.userService = userService;
	}
	
	@Transactional
	public Post getPostById(int postId, int userId) {
		User user = userService.getUserById(userId);
		Post post= postRepository.getPostById(postId).orElse(null);
		if(user.getPosts().contains(post)) {
			return post;
		}
		return null;
	}
	
	@Transactional
	public Post savePost(Post post, int userId) {
		User user = userService.getUserById(userId);
		post.setUser(user);
		return postRepository.save(post);
	}
	
	@Transactional
	public void deletePost(int postId, int userId) {
		Post post = getPostById(postId, userId);
		if(post!=null) {
			User user = post.getUser();
			user.getPosts().remove(post);
			postRepository.delete(post);
		}
		
	}

	@Transactional
	public void removeAllUserPosts(int userId) {
		User user = userService.getUserById(userId);
		Set<Post> userPosts = user.getPosts();
		user.setPosts(Collections.emptySet());
		postRepository.deleteAll(userPosts);
	}

}
