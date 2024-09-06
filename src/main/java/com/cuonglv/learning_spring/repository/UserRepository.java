package com.cuonglv.learning_spring.repository;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.cuonglv.learning_spring.data.User;

import java.util.Optional;

public interface UserRepository extends MongoRepository<User, ObjectId> {

	Optional<User> findByUsername(String username);

	Optional<User> findByEmail(String email);
}
