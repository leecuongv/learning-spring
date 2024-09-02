package com.cuonglv.learning_spring.data;

import java.util.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Animal {
    @Id
    private String id;
    private String name;
    private String type;
    private Date birthDate;
    private double weight;

    @DBRef
    private Species species;
}
