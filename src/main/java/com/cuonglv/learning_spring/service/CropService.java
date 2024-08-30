package com.cuonglv.learning_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.cuonglv.learning_spring.data.Crop;
import java.util.List;

@Service
public class CropService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Crop createCrop(Crop crop) {
        mongoTemplate.save(crop);
        return crop;
    }

    public boolean deleteCrop(String id) {
        Crop crop = mongoTemplate.findById(id, Crop.class);
        if (crop == null) {
            return false;
        }
        mongoTemplate.remove(crop);
        return true;
    }

    public Crop updateCrop(String id, Crop updatedCrop) {
        Crop crop = mongoTemplate.findById(id, Crop.class);
        if (crop == null) {
            throw new RuntimeException("Crop not found");

        }
        crop.setName(updatedCrop.getName());
        crop.setType(updatedCrop.getType());
        crop.setPlantDate(updatedCrop.getPlantDate());
        crop.setWeight(updatedCrop.getWeight());
        mongoTemplate.save(crop);
        return crop;
    }

    public Crop getCropById(String id) {
        return mongoTemplate.findById(id, Crop.class);
    }

    public List<Crop> getAllCrops() {
        return mongoTemplate.findAll(Crop.class);
    }
}
