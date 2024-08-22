package com.cuonglv.learning_spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cuonglv.learning_spring.data.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);

	Optional<User> findByUsernameAndUpdateOptional(String username, User user);

	Optional<Boolean> findByUsernameAndDeleteOptional(String email, User user);
}
