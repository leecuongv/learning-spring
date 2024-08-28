package com.cuonglv.learning_spring.data;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Project {

    // create a class named Project with the following fields:
    // name of type String
    // type of type String
    // startDate of type Date
    // endDate of type Date
    private String name;
    private String type;
    private String startDate;
    private String endDate;
    private String status;
    private String description;
    private String manager;
    private String department;

}
