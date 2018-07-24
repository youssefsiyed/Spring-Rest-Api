package com.dev.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.dev.entity.Post;

@RepositoryRestResource
public interface PostRepository extends JpaRepository<Post, Long>{
	
	@Query("select p from Post p where p.nomPost like :x")
	public Page<Post> selectPost(@Param("x")String mot,Pageable p);

}
