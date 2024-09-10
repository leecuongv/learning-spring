package com.cuonglv.learning_spring.data;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Data
@Document
public class Supplier {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String name;
    private String contactInfo;
    private String address;
    private String notes;
}
