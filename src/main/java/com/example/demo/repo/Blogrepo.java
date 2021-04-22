package com.example.demo.repo;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;

import com.example.demo.entity.Blog;


@RepositoryRestResource(collectionResourceRel = "blogs", path = "blogs")
public interface Blogrepo extends JpaRepository<Blog, Long> {

}
