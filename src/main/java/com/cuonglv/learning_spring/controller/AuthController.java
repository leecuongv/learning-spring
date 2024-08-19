package com.cuonglv.learning_spring.controller;

import com.cuonglv.learning_spring.model.AuthRequest;
import com.cuonglv.learning_spring.model.AuthResponse;
import com.cuonglv.learning_spring.model.RegisterRequest;
import com.cuonglv.learning_spring.repository.RoleRepository;
import com.cuonglv.learning_spring.repository.UserRepository;
import com.cuonglv.learning_spring.service.CustomUserDetailsService;
import com.cuonglv.learning_spring.security.JwtUtil;
import com.cuonglv.learning_spring.data.Role;
import com.cuonglv.learning_spring.data.User;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private CustomUserDetailsService userDetailsService;

	@Autowired
	private UserRepository userRepository;

	@Autowired
	private RoleRepository roleRepository;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	private JwtUtil jwtUtil;

	@PostMapping("/login")
	public ResponseEntity<AuthResponse> login(@RequestBody AuthRequest authRequest) {
		authenticationManager.authenticate(
				new UsernamePasswordAuthenticationToken(authRequest.getUsername(), authRequest.getPassword()));

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authRequest.getUsername());
		System.err.println("UserDetails: ");
		System.out.println(userDetails);
		final String token = jwtUtil.generateToken(userDetails);

		return ResponseEntity.ok(new AuthResponse(token));
	}

	@PostMapping("/register")
	public ResponseEntity<String> register(@RequestBody RegisterRequest registerRequest) {
		Optional<User> existingUser = userRepository.findByUsername(registerRequest.getUsername());
		if (existingUser.isPresent()) {
			return ResponseEntity.badRequest().body("Username is already taken.");
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

		return ResponseEntity.ok("User registered successfully.");
	}
}
