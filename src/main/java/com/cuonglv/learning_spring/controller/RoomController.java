package com.cuonglv.learning_spring.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuonglv.learning_spring.data.Room;
import com.cuonglv.learning_spring.service.RoomService;

@RestController
@RequestMapping("/api/rooms")
public class RoomController {
	@Autowired
	private RoomService productService;

	@PostMapping
	public Room createRoom(@RequestBody Room product) {
		return productService.createRoom(product);
	}

	@GetMapping
	public List<Room> getAllRooms() {
		return productService.getAllRooms();
	}

	@GetMapping("/{id}")
	public ResponseEntity<Room> getRoomById(@PathVariable String id) {
		Optional<Room> product = productService.getRoomById(id);
		return product.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
	}

	@PutMapping("/{id}")
	public ResponseEntity<Room> updateRoom(@PathVariable String id, @RequestBody Room productDetails) {
		Room updatedRoom = productService.updateRoom(id, productDetails);
		return ResponseEntity.ok(updatedRoom);
	}

	@DeleteMapping("/{id}")
	public ResponseEntity<Void> deleteRoom(@PathVariable String id) {
		productService.deleteRoom(id);
		return ResponseEntity.ok().build();
	}
}
