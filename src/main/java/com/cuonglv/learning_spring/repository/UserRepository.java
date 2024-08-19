package com.cuonglv.learning_spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cuonglv.learning_spring.data.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, String> {
	Optional<User> findByUsername(String username);
}
