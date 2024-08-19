package com.cuonglv.learning_spring.data;

import java.util.Date;

import org.springframework.data.mongodb.core.mapping.Document;

import lombok.Data;

@Document
@Data
public class Assignment {

	private String name;
	private String content;
	private String creatorId;
	private Date startTime;
	private Date endTime;
	private String viewPoint;
	private Number maxPoints;
	private String courseId;
	private Number slug;
	private Boolean allowReSubmit;
	private Boolean allowSubmitLate;
	private String file;
	private String status;
	private Number toPass;
}
