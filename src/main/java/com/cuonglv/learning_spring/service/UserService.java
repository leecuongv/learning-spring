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

	public User getUserByUsername(String username) {
		return userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException("User not found"));
	}

	public User updateUser(String username, User updatedUser) throws Exception {

		try {
			User user = userRepository.findByUsername(username)
					.orElseThrow(() -> new RuntimeException("User not found"));
			// if (updatedUser.getUsername() != null) {
			// user.setUsername(updatedUser.getUsername());
			// }
			// if (updatedUser.getPassword() != null) {
			// user.setPassword(updatedUser.getPassword());
			// }
			if (updatedUser.getEmail() != null) {
				user.setEmail(updatedUser.getEmail());
			}
			if (updatedUser.getRoles() != null) {
				user.setRoles(updatedUser.getRoles());
			}
			if (updatedUser.getFullname() != null) {
				user.setFullname(updatedUser.getFullname());
			}
			if (updatedUser.getPhone() != null) {
				user.setPhone(updatedUser.getPhone());
			}
			if (updatedUser.getAddress() != null) {
				user.setAddress(updatedUser.getAddress());
			}

			return userRepository.save(user);
		} catch (Exception e) {
			throw new Exception("Error updating user" + e.getMessage());
		}

	}

	public void deleteUser(String username) {
		try {
			User user = userRepository.findByUsername(username)
					.orElseThrow(() -> new RuntimeException("User not found"));
			userRepository.delete(user);
		} catch (Exception e) {
			throw new RuntimeException("Error deleting user" + e.getMessage());
		}
	}
}
