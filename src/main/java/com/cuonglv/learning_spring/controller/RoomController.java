package com.cuonglv.learning_spring.controller;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuonglv.learning_spring.context.RequestContext;
import com.cuonglv.learning_spring.data.Room;
import com.cuonglv.learning_spring.service.RoomService;
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;
import com.cuonglv.learning_spring.utility.response.handler.ResponseHandler;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
	@Autowired
	private RoomService roomService;
	@Inject
	RequestContext requestContext;

	@Autowired
	ResponseHandler responseHandler;

	@PostMapping
	public Room createRoom(@RequestBody Room room) {
		return roomService.createRoom(room);
	}

	@GetMapping
	public List<Room> getAllRooms() {
		return roomService.getAllRooms();
	}

	@GetMapping("/{id}")
	public ResponseMessage<?> getRoomById(@PathVariable String id) {

		String requestId = UUID.randomUUID().toString();

		Optional<Room> room = roomService.getRoomById(id);
		System.err.println(room.get().toString());
		JsonObject roomData = new JsonObject();
		roomData.addProperty("room", room.get().toString());
		return responseHandler.generateResponseMessage(roomData, requestId);
		// return room.map(ResponseEntity::ok).orElseGet(() ->
		// ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Room> updateRoom(@PathVariable String id, @RequestBody Room roomDetails) {
		Room updatedRoom = roomService.updateRoom(id, roomDetails);
		return ResponseEntity.ok(updatedRoom);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRoom(@PathVariable String id) {
		roomService.deleteRoom(id);
		return ResponseEntity.ok().build();
	}
}
