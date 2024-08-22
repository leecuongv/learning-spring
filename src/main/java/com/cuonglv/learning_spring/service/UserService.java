package com.cuonglv.learning_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.cuonglv.learning_spring.data.User;
import com.cuonglv.learning_spring.repository.UserRepository;

@Service
public class UserService implements UserDetailsService {

	@Autowired
	private UserRepository userRepository;

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		User user = userRepository.findByUsername(username)
				.orElseThrow(() -> new UsernameNotFoundException("User not found"));

		return org.springframework.security.core.userdetails.User.builder().username(user.getUsername())
				.password(user.getPassword())
				.roles(user.getRoles().stream().map(role -> role.getName()).toArray(String[]::new)).build();

	}

	public User updateUser(String id, User updatedUser) {
		return userRepository.findById(id).map(user -> {
			user.setUsername(updatedUser.getUsername());
			user.setEmail(updatedUser.getEmail());
			user.setRoles(updatedUser.getRoles());
			// Cập nhật các trường khác nếu có
			return userRepository.save(user);
		}).orElseThrow(() -> new RuntimeException("User not found with id " + id));
	}

	public void deleteUser(String id) {
		if (userRepository.existsById(id)) {
			userRepository.deleteById(id);
		} else {
			throw new RuntimeException("User not found with id " + id);
		}
	}

}
