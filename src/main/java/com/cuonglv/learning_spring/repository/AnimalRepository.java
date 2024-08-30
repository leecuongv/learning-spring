package com.cuonglv.learning_spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import com.cuonglv.learning_spring.data.Animal;

public interface AnimalRepository extends MongoRepository<Animal, String> {
}
