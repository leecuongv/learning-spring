package com.cuonglv.learning_spring.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuonglv.learning_spring.context.RequestContext;
import com.cuonglv.learning_spring.data.User;
import com.cuonglv.learning_spring.security.JwtUtil;
import com.cuonglv.learning_spring.service.UserService;
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;
import com.cuonglv.learning_spring.utility.response.handler.ResponseHandler;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RestController
@RequestMapping("/api/user")
public class UserController {

	@Autowired
	private UserDetailsService userDetailsService;

	@Autowired
	private UserService userService;
	@Autowired
	private JwtUtil jwtUtil;
	@Inject
	RequestContext requestContext;

	@Autowired
	ResponseHandler responseHandler;
	Gson gson = new Gson();

	@GetMapping("/me")
	public ResponseMessage<?> getAuthenticatedUser() throws Exception {

		ResponseMessage<?> responseMessage = null;
		try {
			UserDetails userDetail = userDetailsService.loadUserByUsername(getUsername());
			User userEntity = userService.getUserByUsername(userDetail.getUsername());
			userEntity.setPassword("");
			responseMessage = responseHandler.generateResponseMessage(userEntity, requestContext.getRequestId());
		} catch (Exception e) {
			responseMessage = responseHandler.generateResponseMessage(e, requestContext.getRequestId());
		}
		return responseMessage;

	}

	// update user
	@PutMapping("/me")
	public ResponseMessage<?> updateUser(@RequestBody JsonObject jsonObject) {
		ResponseMessage<?> responseMessage = null;
		try {

			User user = gson.fromJson(jsonObject, User.class);
			User updatedUser = userService.updateUser(getUsername(), user);
			updatedUser.setPassword("");
			responseMessage = responseHandler.generateResponseMessage(updatedUser, requestContext.getRequestId());
		} catch (Exception e) {
			responseMessage = responseHandler.generateResponseMessage(e, requestContext.getRequestId());
		}

		return responseMessage;
	}

	@DeleteMapping("/me")
	public ResponseMessage<?> deleteUser() {
		ResponseMessage<?> responseMessage = null;
		try {
			userService.deleteUser(getUsername());
			responseMessage = responseHandler.generateResponseMessage("User deleted successfully",
					requestContext.getRequestId());
		} catch (Exception e) {
			responseMessage = responseHandler.generateResponseMessage(e, requestContext.getRequestId());
		}

		return responseMessage;
	}

	public String getUsername() {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		return authentication.getName();
	}
}
