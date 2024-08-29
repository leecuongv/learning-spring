package com.cuonglv.learning_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import java.util.List;
import com.cuonglv.learning_spring.data.Animal;

public class AnimalService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Animal createAnimal(Animal animal) {
        mongoTemplate.save(animal);
        return animal;
    }

    public boolean deleteAnimal(String id) {
        Animal animal = mongoTemplate.findById(id, Animal.class);
        if (animal == null) {
            return false;
        }
        mongoTemplate.remove(animal);
        return true;
    }

    public Animal updateAnimal(String id, Animal updatedAnimal) {
        Animal animal = mongoTemplate.findById(id, Animal.class);
        if (animal == null) {
            throw new RuntimeException("Animal not found");
        }
        animal.setName(updatedAnimal.getName());
        animal.setType(updatedAnimal.getType());
        animal.setBirthDate(updatedAnimal.getBirthDate());
        animal.setWeight(updatedAnimal.getWeight());
        mongoTemplate.save(animal);
        return animal;
    }

    public Animal getAnimalById(String id) {
        return mongoTemplate.findById(id, Animal.class);
    }

    public List<Animal> getAllAnimals() {
        return mongoTemplate.findAll(Animal.class);
    }

}
