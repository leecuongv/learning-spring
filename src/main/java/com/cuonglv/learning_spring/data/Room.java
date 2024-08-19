package com.cuonglv.learning_spring.data;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;
import lombok.Data;

@Document
@Data
public class Room {
	@Id
	private String id;
	private String name;
	private String roomNumber;
	private String bedInfo;

	public String getId() {
		return id;
	}

	public void setBedInfo(String bedInfo) {
		this.bedInfo = bedInfo;
	}

	public String getBedInfo() {
		return bedInfo;
	}

	public void setId(String id) {
		this.id = id;
	}

	public void setName(String name) {
		this.name = name;
	}

	public void setRoomNumber(String roomNumber) {
		this.roomNumber = roomNumber;
	}

	public String getName() {
		return name;
	}

	public String getRoomNumber() {
		return roomNumber;
	}

	@Override
	public String toString() {

		return "Room [id=" + id + ", name=" + name + ", roomNumber=" + roomNumber + ", bedInfo=" + bedInfo + "]";
	}
}
