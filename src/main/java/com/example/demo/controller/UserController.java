package com.example.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginRequest;
import com.example.demo.dto.RegisterRequest;
import com.example.demo.entity.User;
import com.example.demo.repo.UserRepo;

@RestController
public class UserController {
	
	@Autowired
	private UserRepo userrepo;
	
	
	@PostMapping("/register")
	public ResponseEntity<Object> register(@RequestBody RegisterRequest request) {
		// TODO Auto-generated method stub
		BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
		User user = new User();
		user.setUserName(request.getUserName());
		user.setPassword(passwordEncoder.encode(request.getPassword()));
		userrepo.save(user);
		return new ResponseEntity<>(HttpStatus.OK);

	}
	
	@PostMapping("/login")
	public ResponseEntity<Object> login(@RequestBody LoginRequest request) {
		// TODO Auto-generated method stub
		try {
			System.out.println("Reached login");
			User user = userrepo.findByUserName(request.getUserName());
			BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();
//			System.out.println("User found, Password:" + user.getPassword() + " " + passwordEncoder.encode(request.getPassword()));
			if (passwordEncoder.matches(user.getPassword(),request.getPassword())) {
				System.out.println("Password Doesn't Match");
				return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
			}
			else {
				System.out.println("Password Match");
				return new ResponseEntity<>(HttpStatus.ACCEPTED);
			}
		}
		
		 catch(Exception e) {
			 System.out.println("User Not Found");
			 return new ResponseEntity<>(HttpStatus.NOT_FOUND);
			 
		 }
		}

}
