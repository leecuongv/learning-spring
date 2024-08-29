package com.cuonglv.learning_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.cuonglv.learning_spring.data.Equipment;
import java.util.List;

public class EquipmentService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Equipment createEquipment(Equipment equipment) {
        mongoTemplate.save(equipment);
        return equipment;
    }

    public boolean deleteEquipment(String id) {
        Equipment equipment = mongoTemplate.findById(id, Equipment.class);
        if (equipment == null) {
            return false;
        }
        mongoTemplate.remove(equipment);
        return true;
    }

    public Equipment updateEquipment(String id, Equipment updatedEquipment) {
        Equipment equipment = mongoTemplate.findById(id, Equipment.class);
        if (equipment == null) {
            throw new RuntimeException("Equipment not found");
        }
        equipment.setName(updatedEquipment.getName());
        equipment.setType(updatedEquipment.getType());
        equipment.setPurchaseDate(updatedEquipment.getPurchaseDate());
        equipment.setPrice(updatedEquipment.getPrice());
        equipment.setStatus(updatedEquipment.getStatus());
        equipment.setDescription(updatedEquipment.getDescription());
        mongoTemplate.save(equipment);
        return equipment;
    }

    public Equipment getEquipmentById(String id) {
        return mongoTemplate.findById(id, Equipment.class);
    }

    public List<Equipment> getAllEquipments() {
        return mongoTemplate.findAll(Equipment.class);
    }

}
