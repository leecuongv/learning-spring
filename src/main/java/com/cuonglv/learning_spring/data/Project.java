package com.cuonglv.learning_spring.data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;
import java.util.Date;

@Document
@Data
public class Project {
    @MongoId(FieldType.OBJECT_ID)
    public String id;
    public String name;
    public String type;
    public Date startDate;
    public Date endDate;
    public String status;
    public String description;
    public String manager;
    public String department;

}
