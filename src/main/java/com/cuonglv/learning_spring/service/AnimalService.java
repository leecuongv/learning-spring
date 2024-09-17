package com.cuonglv.learning_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import java.util.List;
import com.cuonglv.learning_spring.data.Animal;

@Service
public class AnimalService extends BaseService {
    @Autowired
    public MongoTemplate mongoTemplate;

}
