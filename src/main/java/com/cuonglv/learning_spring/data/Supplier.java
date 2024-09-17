package com.cuonglv.learning_spring.data;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Data
@Document
public class Supplier {
    @MongoId(FieldType.OBJECT_ID)
    public String id;
    public String name;
    public String contactInfo;
    public String address;
    public String notes;
}
