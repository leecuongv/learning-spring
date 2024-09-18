package com.cuonglv.learning_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

@Service
public class CropService extends BaseService {
    @Autowired
    private MongoTemplate mongoTemplate;
}
