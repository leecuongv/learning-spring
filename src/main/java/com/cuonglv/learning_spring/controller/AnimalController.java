package com.cuonglv.learning_spring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.cuonglv.learning_spring.context.RequestContext;
import com.cuonglv.learning_spring.data.Animal;
import com.cuonglv.learning_spring.service.AnimalService;
import com.cuonglv.learning_spring.utility.helper.GsonHelper;
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;
import com.cuonglv.learning_spring.utility.response.handler.ResponseHandler;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/animal")

public class AnimalController {
    AnimalService animalService;
    @Inject
    RequestContext requestContext;

    @Autowired
    ResponseHandler responseHandler;

    @PostMapping("/create")
    public ResponseMessage<?> create(@RequestBody JsonObject req) throws Exception {
        try {
            System.out.println("Create animal");
            Animal animal = new Animal();
            animal.setName(GsonHelper.getAsString(req, "name"));
            animal.setBirthDate(GsonHelper.getAsDate(req, "birthDate"));
            animal.setType(GsonHelper.getAsString(req, "type"));
            animal.setWeight(GsonHelper.getAsDouble(req, "weight"));
            animalService.createAnimal(animal);
            return responseHandler.generateResponseMessage(animal, requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }

    }

    @GetMapping
    public ResponseMessage<?> getAll() {
        return responseHandler.generateResponseMessage(animalService.getAllAnimals(), requestContext.getRequestId());
    }

    @GetMapping("/{id}")
    public ResponseMessage<?> getById(@RequestParam String id) {
        return responseHandler.generateResponseMessage(animalService.getAnimalById(id), requestContext.getRequestId());
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<?> deleteById(@RequestParam String id) {
        return responseHandler.generateResponseMessage(animalService.deleteAnimal(id), requestContext.getRequestId());
    }

    @PutMapping("/{id}")
    public ResponseMessage<?> updateById(@RequestParam String id, @RequestBody JsonObject req) throws Exception {
        Animal animal = new Animal();
        animal.setName(GsonHelper.getAsString(req, "name"));
        animal.setBirthDate(GsonHelper.getAsDate(req, "birthDate"));
        animal.setType(GsonHelper.getAsString(req, "type"));
        animal.setWeight(GsonHelper.getAsDouble(req, "weight"));
        return responseHandler.generateResponseMessage(animalService.updateAnimal(id, animal),
                requestContext.getRequestId());
    }

}
