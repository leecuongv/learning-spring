package com.cuonglv.learning_spring.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.cuonglv.learning_spring.data.Supplier;
import java.util.List;

@Service
public class SupplierService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Supplier create(Supplier supplier) {
        mongoTemplate.save(supplier);
        return supplier;
    }

    public boolean delete(String id) {
        Supplier supplier = mongoTemplate.findById(id, Supplier.class);
        if (supplier == null) {
            return false;
        }
        mongoTemplate.remove(supplier);
        return true;
    }

    public Supplier update(String id, Supplier updatedSupplier) {
        Supplier supplier = mongoTemplate.findById(id, Supplier.class);
        if (supplier == null) {
            throw new RuntimeException("Supplier not found");
        }
        supplier.setName(updatedSupplier.getName());
        supplier.setAddress(updatedSupplier.getAddress());

        supplier.setContactInfo(updatedSupplier.getContactInfo());
        supplier.setNotes(updatedSupplier.getNotes());
        mongoTemplate.save(supplier);
        return supplier;
    }

    public Supplier getById(ObjectId id) {
        return mongoTemplate.findById(id, Supplier.class);
    }

    public List<Supplier> getAll() {
        return mongoTemplate.findAll(Supplier.class);
    }
}
