package com.cuonglv.learning_spring.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuonglv.learning_spring.data.User;
import com.cuonglv.learning_spring.security.JwtUtil;
import com.cuonglv.learning_spring.service.UserService;
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;
	@Autowired
	private JwtUtil jwtUtil;

	@GetMapping("/me")
	public UserDetails getAuthenticatedUser() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		return userDetailsService.loadUserByUsername(username);
	}

	// public ResponseMessage<?> getAuthenticatedUser() {
	// Authentication authentication =
	// SecurityContextHolder.getContext().getAuthentication();
	// String username = authentication.getName();

	// return userDetailsService.loadUserByUsername(username);
	// }

	// update user
	@PutMapping("/me")
	public UserDetails updateUser(@RequestBody UserDetails user) {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String username = authentication.getName();

		return userDetailsService.loadUserByUsername(username);
	}

	@PutMapping("/{id}")
	public ResponseEntity<User> updateUser(@PathVariable String id, @RequestBody User user) {
		User updatedUser = userService.updateUser(id, user);
		return ResponseEntity.ok(updatedUser);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteUser(@PathVariable String id) {
		userService.deleteUser(id);
		return ResponseEntity.noContent().build();
	}
}
