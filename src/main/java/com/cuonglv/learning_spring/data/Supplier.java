package com.cuonglv.learning_spring.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Data
@Document
public class Supplier {
    @Id
    private String id;
    private String name;
    private String contactInfo;
    private String address;
    private String notes;
}
