package com.cuonglv.learning_spring.controller;

import com.cuonglv.learning_spring.context.RequestContext;
import com.cuonglv.learning_spring.data.Species;
import com.cuonglv.learning_spring.service.SpeciesService;
import com.cuonglv.learning_spring.utility.helper.GsonHelper;
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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PutMapping;

@RestController
@RequestMapping("/api/species")
public class SpeciesController {

    @Autowired
    SpeciesService speciesService;
    @Inject
    RequestContext requestContext;

    @Autowired
    ResponseHandler responseHandler;
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
            .registerTypeAdapter(ObjectId.class, new ObjectIdAdapter()).create();

    @PostMapping
    public ResponseMessage<?> create(@RequestBody JsonObject req) throws Exception {
        try {
            System.out.println("Create species");

            Species species = gson.fromJson(req, Species.class);
            speciesService.create(species);
            return responseHandler.generateResponseMessage(species, requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }

    }

    @GetMapping
    public ResponseMessage<?> getAll() {
        try {
            return responseHandler.generateResponseMessage(speciesService.getAll(Species.class),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }

    }

    @GetMapping("/{id}")
    public ResponseMessage<?> getById(@PathVariable String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return responseHandler.generateResponseMessage(speciesService.getById(objectId, Species.class),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }

    }

    @DeleteMapping("/{id}")
    public ResponseMessage<?> deleteById(@PathVariable String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return responseHandler.generateResponseMessage(speciesService.delete(objectId, Species.class),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }

    }

    @PutMapping("/{id}")
    public ResponseMessage<?> updateById(@PathVariable String id, @RequestBody JsonObject req) throws Exception {
        try {
            ObjectId objectId = new ObjectId(id);
            Species species = gson.fromJson(req, Species.class);

            return responseHandler.generateResponseMessage(speciesService.update(objectId, species, Species.class),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }

    }

}
