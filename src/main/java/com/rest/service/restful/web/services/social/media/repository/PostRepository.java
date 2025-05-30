package com.rest.service.restful.web.services.social.media.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.restful.web.services.social.media.entity.Post;

@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public interface PostRepository extends JpaRepository<Post, Long> {
	
Optional<Post> getPostById(Long postId);

}
