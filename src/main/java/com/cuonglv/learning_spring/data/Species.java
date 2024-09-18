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
    public String id;
    public String name;
    public String type;
    public String description;
    public String status;
    public Date buyDate;
    public Date expireDate;

    public Double productivity;
    public Double density;
    public Float weight;
    public Double price;
    public Double harvestTime;
    public Double growthTime;

    // @MongoId(FieldType.OBJECT_ID)
    public String supplier;

}
