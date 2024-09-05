package com.cuonglv.learning_spring.data;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Animal {
    @Id
    private ObjectId id;
    private String name;
    private String type;
    private Date birthDate;
    private double weight;

    private ObjectId species;
}
