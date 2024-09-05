package com.cuonglv.learning_spring.data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Supplier {
    @Id
    private ObjectId id;
    private String name;
    private String contactInfo;
    private String address;
    private String notes;
}
