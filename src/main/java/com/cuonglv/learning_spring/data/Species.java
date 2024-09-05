package com.cuonglv.learning_spring.data;

import java.sql.Date;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
// Lớp giống cho cây trồng và vật nuôi
public class Species {

    @Id
    private ObjectId id;
    private String name;
    private String type;
    private String description;
    private String status;
    private Date buyDate;
    private Date expireDate;
    private Number productivity;
    private Number density;
    private Number weight;
    private Number price;
    private Number harvestTime;
    private Number growthTime;

    private ObjectId supplier;

}
