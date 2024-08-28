package com.cuonglv.learning_spring.controller;

import com.cuonglv.learning_spring.model.AuthRequest;
import com.cuonglv.learning_spring.model.RegisterRequest;
import com.cuonglv.learning_spring.repository.RoleRepository;
import com.cuonglv.learning_spring.repository.UserRepository;
import com.cuonglv.learning_spring.service.UserService;
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;
import com.cuonglv.learning_spring.utility.response.handler.ResponseHandler;
import com.google.gson.JsonObject;
import com.cuonglv.learning_spring.security.JwtUtil;
import com.cuonglv.learning_spring.context.RequestContext;
import com.cuonglv.learning_spring.data.Role;
import com.cuonglv.learning_spring.data.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;
import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import javax.inject.Inject;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private UserService userDetailsService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	@Inject
	RequestContext requestContext;

	@Autowired
	ResponseHandler responseHandler;

	public static final String REQUEST_ID = UUID.randomUUID().toString();

	@PostMapping("/login")
	public ResponseMessage<?> login(@RequestBody AuthRequest authRequest) throws Exception {
		try {
			authenticationManager.authenticate(
					new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

			final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
			final String token = jwtUtil.generateToken(userDetails);
			JsonObject res = new JsonObject();
			res.addProperty("token", token);
			return responseHandler.generateResponseMessage(res, REQUEST_ID);
		} catch (Exception e) {
			return responseHandler.generateResponseMessage(e, REQUEST_ID);
		}

	}

	@PostMapping("/register")
	public ResponseMessage<?> register(@RequestBody RegisterRequest registerRequest) {
		Optional<User> existingUser = userRepository.findByUsername(registerRequest.getUsername());
		if (existingUser.isPresent()) {
			return responseHandler.generateResponseMessage(new Exception("Username is already taken."), "requestId");
		}

		// Mã hóa mật khẩu
		String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());

		// Tạo user mới
		User newUser = new User();
		newUser.setUsername(registerRequest.getUsername());
		newUser.setPassword(encodedPassword);

		// Gán quyền cho user
		Set<Role> roles = new HashSet<>();
		Optional<Role> role = roleRepository.findByName(registerRequest.getRole());
		if (role.isPresent()) {
			roles.add(role.get());
		} else {
			// Tạo quyền mới nếu chưa tồn tại
			Role newRole = new Role();
			newRole.setName(registerRequest.getRole());
			roleRepository.save(newRole);
			roles.add(newRole);
		}
		newUser.setRoles(roles);

		// Lưu user vào MongoDB
		userRepository.save(newUser);
		return responseHandler.generateResponseMessage("User registered successfully.", REQUEST_ID);
	}

	// Create api to change password
	@PutMapping("/change-password")
	public ResponseMessage<?> changePassword(@RequestBody AuthRequest authRequest) {
		Optional<User> existingUser = userRepository.findByUsername(authRequest.getUsername());
		if (!existingUser.isPresent()) {
			return responseHandler.generateResponseMessage(new Exception("User not found."), REQUEST_ID);
		}

		// Mã hóa mật khẩu
		String encodedPassword = passwordEncoder.encode(authRequest.getPassword());

		// Cập nhật mật khẩu mới
		User user = existingUser.get();
		user.setPassword(encodedPassword);
		userRepository.save(user);
		return responseHandler.generateResponseMessage("Password changed successfully.", REQUEST_ID);
	}

}
