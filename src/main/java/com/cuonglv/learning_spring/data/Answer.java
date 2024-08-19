package com.cuonglv.learning_spring.data;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Answer {
	// create a class named Answer with the following fields:
	// content of type String
	// isCorrect of type boolean
	// type of type String, default value is "equal"
	private String content;
	private boolean isCorrect;
	private String type = "equal";

}
