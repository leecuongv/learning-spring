package com.cuonglv.learning_spring.data;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Employee {

    // create a class named Employee with the following fields:
    // name of type String
    // age of type int
    // salary of type double
    // position of type String
    private String name;
    private int age;
    private double salary;
    private String position;
    private Date birthDate;
    private String address;
    private String phoneNumber;
    private String email;
    private String department;
    private Date hireDate;
    private String notes;
    private String status;
    private String gender;
}
