package com.cuonglv.learning_spring.data;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Data
@Document
public class Crop {
    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String name;
    private String type;
    private Date plantDate;

    private ObjectId species;

}
