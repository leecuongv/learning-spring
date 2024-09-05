package com.cuonglv.learning_spring.data;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

import java.util.Set;

@Data
@Document(collection = "users")
public class User {
	@Id
	private ObjectId id;
	private String username;
	private String password;
	private Set<Role> roles;
	private String fullname;
	private String phone;
	private String address;
	private String birthday;
	private String email;
	private String gender;
	private String status;
	private String notes;
	private double salary;
}
