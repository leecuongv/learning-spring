package com.cuonglv.learning_spring.data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;
import java.util.Date;

@Document
@Data
public class Equipment {
    @Id
    private ObjectId id;
    private String name;
    private String type;
    private Date purchaseDate;
    private double price;
    private String status;
    private String description;
}
