package com.cuonglv.learning_spring.data;

import java.sql.Date;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
// Lớp giống cho cây trồng và vật nuôi
public class Species {

    @Id
    private String id;
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

    @DBRef
    private Supplier suplier;

}
