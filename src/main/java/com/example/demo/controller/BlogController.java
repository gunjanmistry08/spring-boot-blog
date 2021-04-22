package com.example.demo.controller;

import java.time.Instant;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.BlogRequest;
import com.example.demo.entity.Blog;
import com.example.demo.entity.User;
import com.example.demo.repo.Blogrepo;
import com.example.demo.repo.UserRepo;

@RestController
public class BlogController {
	
	@Autowired
	private UserRepo userrepo;
	
	@Autowired
	private Blogrepo blogrepo;
	
	@PostMapping("/postblog")
	public ResponseEntity  postblog(@RequestBody BlogRequest request) {
		// TODO Auto-generated method stub
		Blog blog = new Blog();
		blog.setTitle(request.getTitle());
		blog.setContent(request.getContent());
		blog.setCreatedOn(Instant.now());
		User user = userrepo.getOne(request.getUserId());
		blog.setAuthor(user);
		user.getBlogs().add(blog);
		blogrepo.save(blog);
		return new ResponseEntity(HttpStatus.OK);
		

	}

}
