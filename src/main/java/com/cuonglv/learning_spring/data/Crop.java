package com.cuonglv.learning_spring.data;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Crop {
    private String name;
    private String type;
    private Date plantDate;
    private double weight;

}
