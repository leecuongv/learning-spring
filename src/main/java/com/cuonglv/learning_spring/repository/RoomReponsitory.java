package com.cuonglv.learning_spring.repository;

import org.springframework.data.mongodb.repository.MongoRepository;

import com.cuonglv.learning_spring.data.Room;

public interface RoomReponsitory extends MongoRepository<Room, String> {

}
