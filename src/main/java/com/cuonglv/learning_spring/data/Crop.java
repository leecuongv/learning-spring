package com.cuonglv.learning_spring.data;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Data
@Document
public class Crop {
    @MongoId(FieldType.OBJECT_ID)
    public String id;
    public String name;
    public String type;
    public Date plantDate;

    public String species;

}
