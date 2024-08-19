package com.cuonglv.learning_spring.repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cuonglv.learning_spring.data.Role;

public interface RoleRepository extends MongoRepository<Role, String> {
	Optional<Role> findByName(String name);
}
