package com.cuonglv.learning_spring.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import java.util.Date;

@Document
@Data
public class Project {
    @Id
    private String id;
    // create a class named Project with the following fields:
    // name of type String
    // type of type String
    // startDate of type Date
    // endDate of type Date
    private String name;
    private String type;
    private Date startDate;
    private Date endDate;
    private String status;
    private String description;
    private String manager;
    private String department;

}
