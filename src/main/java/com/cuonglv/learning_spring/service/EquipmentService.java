package com.cuonglv.learning_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Service;

import com.cuonglv.learning_spring.data.Equipment;
import java.util.List;

@Service
public class EquipmentService extends BaseService {

    @Autowired
    private MongoTemplate mongoTemplate;

}
