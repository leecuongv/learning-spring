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
    private String id;
    private String name;
    private String type;
    private Date startDate;
    private Date endDate;
    private String status;
    private String description;
    private String manager;
    private String department;

}
