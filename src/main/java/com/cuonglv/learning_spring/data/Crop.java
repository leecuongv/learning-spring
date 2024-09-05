package com.cuonglv.learning_spring.data;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Crop {
    @Id
    private ObjectId id;
    private String name;
    private String type;
    private Date plantDate;
    private double weight;

    private ObjectId species;

}
