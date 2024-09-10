package com.cuonglv.learning_spring.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Document
@Data
public class Room {
	@MongoId(FieldType.OBJECT_ID)
	private String id;
	private String name;
	private String roomNumber;
	private String bedInfo;

}
