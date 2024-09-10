package com.cuonglv.learning_spring.data;

import java.util.Date;

import org.bson.types.ObjectId;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Data
@Document
// Lớp giống cho cây trồng và vật nuôi
public class Species {

    @MongoId(FieldType.OBJECT_ID)
    private String id;
    private String name;
    private String type;
    private String description;
    private String status;
    private Date buyDate;
    private Date expireDate;

    private Double productivity;
    private Double density;
    private Float weight;
    private Double price;
    private Double harvestTime;
    private Double growthTime;

    // @MongoId(FieldType.OBJECT_ID)
    private String supplier;

}
