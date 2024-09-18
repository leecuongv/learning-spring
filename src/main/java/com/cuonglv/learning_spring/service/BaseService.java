package com.cuonglv.learning_spring.service;

import java.lang.reflect.Field;
import java.util.List;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

@Service
public class BaseService {

    @Autowired
    public MongoTemplate mongoTemplate;

    public <T> T create(T object) {
        mongoTemplate.insert(object);
        return object;
    }

    public <T> boolean delete(ObjectId id, Class<T> clazz) {
        Object object = mongoTemplate.findById(id, clazz);
        if (object == null) {
            return false;
        }
        mongoTemplate.remove(object);
        return true;
    }

    public <T> Object update(ObjectId id, Object updatedObject, Class<T> clazz) {
        Object object = mongoTemplate.findById(id.toHexString(), clazz);
        if (object == null) {
            throw new RuntimeException("Object not found");
        }
        Query query = new Query(Criteria.where("_id").is(id));
        System.out.println(query);
        Update update = new Update();
        for (Field iterable_element : clazz.getDeclaredFields()) {
            try {
                // iterable_element.setAccessible(true);
                if (iterable_element.get(updatedObject) != null) {
                    update.set(iterable_element.getName(), iterable_element.get(updatedObject));
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
            }
        }
        System.out.println(update);

        mongoTemplate.findAndModify(query, update, clazz);
        return updatedObject;
    }

    public <T> Object getById(ObjectId id, Class<T> clazz) {
        for (java.lang.reflect.Field iterable_element : clazz.getDeclaredFields()) {
            System.out.println(iterable_element.getName() + " " + iterable_element.getType());
        }
        return mongoTemplate.findById(id, clazz);
    }

    public <T> List<T> getAll(Class<T> clazz) {
        return mongoTemplate.findAll(clazz);
    }

    public <T> List<T> getAllByField(String fieldName, Object value, Class<T> clazz) {
        Query query = new Query(Criteria.where(fieldName).is(value));
        return mongoTemplate.find(query, clazz);
    }

    public <T> List<T> getAllByFields(String[] fieldNames, Object[] values, Class<T> clazz) {
        Query query = new Query();
        for (int i = 0; i < fieldNames.length; i++) {
            query.addCriteria(Criteria.where(fieldNames[i]).is(values[i]));
        }
        return mongoTemplate.find(query, clazz);
    }

    public <T> List<T> getAllByFields(String[] fieldNames, Object[] values, String[] operators, Class<T> clazz) {
        Query query = new Query();
        for (int i = 0; i < fieldNames.length; i++) {
            if (operators[i].equals("eq")) {
                query.addCriteria(Criteria.where(fieldNames[i]).is(values[i]));
            } else if (operators[i].equals("gt")) {
                query.addCriteria(Criteria.where(fieldNames[i]).gt(values[i]));
            } else if (operators[i].equals("lt")) {
                query.addCriteria(Criteria.where(fieldNames[i]).lt(values[i]));
            } else if (operators[i].equals("gte")) {
                query.addCriteria(Criteria.where(fieldNames[i]).gte(values[i]));
            } else if (operators[i].equals("lte")) {
                query.addCriteria(Criteria.where(fieldNames[i]).lte(values[i]));
            } else if (operators[i].equals("ne")) {
                query.addCriteria(Criteria.where(fieldNames[i]).ne(values[i]));
            } else if (operators[i].equals("in")) {
                query.addCriteria(Criteria.where(fieldNames[i]).in(values[i]));
            } else if (operators[i].equals("nin")) {
                query.addCriteria(Criteria.where(fieldNames[i]).nin(values[i]));
            } else if (operators[i].equals("regex")) {
                query.addCriteria(Criteria.where(fieldNames[i]).regex((String) values[i]));
            }
        }
        return mongoTemplate.find(query, clazz);
    }

}
