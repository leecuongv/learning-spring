package com.cuonglv.learning_spring.data;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Document
@Data
public class Animal {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String name;
    private String type;
    private Date birthDate;
    private double weight;

    private String species;

    // Create a sample Json Animal object
    // {
    // "id": "5f8b1b3b7f3b9b1b3b7f3b9b",
    // "name": "Lion",
    // "type": "Mammal",
    // "birthDate": "2020-10-17T00:00:00.000+00:00",
    // "weight": 190.0,
    // "species": "5f8b1b3b7f3b9b1b3b7f3b9b"

    // }

}
