package com.cuonglv.learning_spring.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cuonglv.learning_spring.data.Room;
import com.cuonglv.learning_spring.repository.RoomRepository;

@Service
public class RoomService {
    @Autowired
    public RoomRepository productRepository;

    public Room createRoom(Room product) {
        return productRepository.save(product);
    }

    public List<Room> getAllRooms() {
        return productRepository.findAll();
    }

    public Optional<Room> getRoomById(String id) {
        return productRepository.findById(id);
    }

    public Room updateRoom(String id, Room productDetails) {
        Room product = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Room not found"));
        product.setName(productDetails.getName());
        product.setBedInfo(productDetails.getBedInfo());
        product.setRoomNumber(productDetails.getRoomNumber());
        return productRepository.save(product);
    }

    public void deleteRoom(String id) {
        productRepository.deleteById(id);
    }
}
