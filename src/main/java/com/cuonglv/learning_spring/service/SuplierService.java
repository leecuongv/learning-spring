package com.cuonglv.learning_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.cuonglv.learning_spring.data.Supplier;
import java.util.List;

public class SuplierService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public Supplier createSupplier(Supplier supplier) {
        mongoTemplate.save(supplier);
        return supplier;
    }

    public boolean deleteSupplier(String id) {
        Supplier supplier = mongoTemplate.findById(id, Supplier.class);
        if (supplier == null) {
            return false;
        }
        mongoTemplate.remove(supplier);
        return true;
    }

    public Supplier updateSupplier(String id, Supplier updatedSupplier) {
        Supplier supplier = mongoTemplate.findById(id, Supplier.class);
        if (supplier == null) {
            throw new RuntimeException("Supplier not found");
        }
        supplier.setName(updatedSupplier.getName());
        supplier.setAddress(updatedSupplier.getAddress());

        supplier.setContactInfo(updatedSupplier.getContactInfo());
        mongoTemplate.save(supplier);
        return supplier;
    }

    public Supplier getSupplierById(String id) {
        return mongoTemplate.findById(id, Supplier.class);
    }

    public List<Supplier> getAllSuppliers() {
        return mongoTemplate.findAll(Supplier.class);
    }
}
