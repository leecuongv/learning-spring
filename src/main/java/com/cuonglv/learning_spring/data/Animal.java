package com.cuonglv.learning_spring.data;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Animal {
    // create a class named Animal with the following fields:
    // name of type String
    // type of type String
    // age of type int
    // weight of type double
    // ngày sinh của con vật
    private String name;
    private String type;
    private Date birthDate;
    private double weight;

}
