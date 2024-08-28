package com.cuonglv.learning_spring.data;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Equipment {

    // create a class named Equipment with the following fields:
    // name of type String
    // type of type String
    // purchaseDate of type Date
    // price of type double
    private String name;
    private String type;
    private String purchaseDate;
    private double price;
    private String status;
    private String description;
}