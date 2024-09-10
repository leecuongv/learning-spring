package com.cuonglv.learning_spring.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

import java.util.Date;

@Document
@Data
public class Bill {

	@MongoId(FieldType.OBJECT_ID)
	private String billId;
	private String creatorId;
	private Double amount;
	private String status;
	private String description;
	private String method;
	private String transactionId;
	private Date createdAt;
	private Date updatedAt;
}