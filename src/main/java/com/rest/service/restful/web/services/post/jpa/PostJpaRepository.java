package com.rest.service.restful.web.services.post.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import com.rest.service.restful.web.services.post.Post;

@Transactional(propagation=Propagation.REQUIRED, readOnly=false)
public interface PostJpaRepository extends JpaRepository<Post, Integer> {
	


}
