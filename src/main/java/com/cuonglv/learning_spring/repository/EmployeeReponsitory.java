package com.cuonglv.learning_spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cuonglv.learning_spring.data.Employee;

public interface EmployeeReponsitory extends MongoRepository<Employee, String> {

}
