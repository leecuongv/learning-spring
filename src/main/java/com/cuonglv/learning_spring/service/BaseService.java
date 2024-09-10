package com.cuonglv.learning_spring.service;

import java.util.List;

import org.bson.Document;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.cuonglv.learning_spring.data.Supplier;

@Service
public class BaseService {

    @Autowired
    private MongoTemplate mongoTemplate;

    public <T> T create(T object) {
        System.out.println("Creating object");
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
        // Document filter = new Document("_id", id);
        Query query = new Query(Criteria.where("_id").is(id));
        System.out.println(query);
        Update update = new Update();
        for (java.lang.reflect.Field iterable_element : clazz.getDeclaredFields()) {
            try {
                iterable_element.setAccessible(true);
                if (iterable_element.get(updatedObject) != null) {
                    update.set(iterable_element.getName(), iterable_element.get(updatedObject));
                }
            } catch (IllegalArgumentException | IllegalAccessException e) {
                e.printStackTrace();
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

}
