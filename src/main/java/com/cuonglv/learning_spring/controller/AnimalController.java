package com.cuonglv.learning_spring.controller;

import com.cuonglv.learning_spring.context.RequestContext;
import com.cuonglv.learning_spring.data.Animal;
import com.cuonglv.learning_spring.service.AnimalService;
import com.cuonglv.learning_spring.utility.helper.ObjectIdAdapter;
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;
import com.cuonglv.learning_spring.utility.response.handler.ResponseHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/animal")
public class AnimalController {

    @Autowired
    AnimalService animalService;
    @Inject
    RequestContext requestContext;

    @Autowired
    ResponseHandler responseHandler;
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
            .registerTypeAdapter(ObjectId.class, new ObjectIdAdapter()).create();

    @PostMapping
    public ResponseMessage<?> create(@RequestBody JsonObject req) throws Exception {
        try {
            Animal animal = gson.fromJson(req, Animal.class);
            animalService.create(animal);
            return responseHandler.generateResponseMessage(animal, requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }

    }

    @GetMapping
    public ResponseMessage<?> getAll() {
        return responseHandler.generateResponseMessage(animalService.getAll(Animal.class),
                requestContext.getRequestId());
    }

    @GetMapping("/{id}")
    public ResponseMessage<?> getById(@PathVariable String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return responseHandler.generateResponseMessage(animalService.getById(objectId, Animal.class),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseMessage<?> deleteById(@PathVariable String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return responseHandler.generateResponseMessage(animalService.delete(objectId, Animal.class),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }

    }

    @PutMapping("/{id}")
    public ResponseMessage<?> updateById(@PathVariable String id, @RequestBody JsonObject req) throws Exception {
        try {
            Animal animal = gson.fromJson(req, Animal.class);
            ObjectId objectId = new ObjectId(id);
            return responseHandler.generateResponseMessage(animalService.update(objectId, animal, Animal.class),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }

    }

}
