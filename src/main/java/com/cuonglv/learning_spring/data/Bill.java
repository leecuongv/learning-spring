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
	public String billId;
	public String creatorId;
	public Double amount;
	public String status;
	public String description;
	public String method;
	public String transactionId;
	public Date createdAt;
	public Date updatedAt;
}