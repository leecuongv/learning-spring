package com.cuonglv.learning_spring.data;

import java.util.Set;

import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.FieldType;
import org.springframework.data.mongodb.core.mapping.MongoId;

import lombok.Data;

@Data
@Document
public class User {
	@MongoId(FieldType.OBJECT_ID)
	public String id;
	public String username;
	public String password;
	public Set<Role> roles;
	public String fullname;
	public String phone;
	public String address;
	public String birthday;
	public String email;
	public String gender;
	public String status;
	public String notes;
	public double salary;
}
