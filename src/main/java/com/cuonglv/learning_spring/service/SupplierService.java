package com.cuonglv.learning_spring.service;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.cuonglv.learning_spring.data.Supplier;
import com.google.gson.Gson;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.mongodb.client.model.Field;

import java.util.List;

@Service
public class SupplierService extends BaseService {

    @Autowired
    public MongoTemplate mongoTemplate;
}
