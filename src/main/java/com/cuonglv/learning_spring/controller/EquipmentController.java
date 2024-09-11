package com.cuonglv.learning_spring.controller;

import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuonglv.learning_spring.context.RequestContext;
import com.cuonglv.learning_spring.data.Equipment;
import com.cuonglv.learning_spring.service.EquipmentService;
import com.cuonglv.learning_spring.utility.helper.GsonHelper;
import com.cuonglv.learning_spring.utility.helper.ObjectIdAdapter;
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;
import com.cuonglv.learning_spring.utility.response.handler.ResponseHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    @Inject
    RequestContext requestContext;

    @Autowired
    ResponseHandler responseHandler;

    @Autowired
    EquipmentService equipmentService;

    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
            .registerTypeAdapter(ObjectId.class, new ObjectIdAdapter()).create();

    @PostMapping
    public ResponseMessage<?> create(@RequestBody JsonObject req) throws Exception {
        try {
            Equipment equipment = gson.fromJson(req, Equipment.class);
            equipmentService.create(equipment);
            return responseHandler.generateResponseMessage(equipment, requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }
    }

    @PutMapping("/{id}")
    public ResponseMessage<?> updateEquipment(@RequestParam String id, @RequestBody JsonObject req) throws Exception {

        try {
            ObjectId objectId = new ObjectId(id);
            Equipment equipment = gson.fromJson(req, Equipment.class);
            return responseHandler.generateResponseMessage(
                    equipmentService.update(objectId, equipment, Equipment.class),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }
    }

    @GetMapping("/{id}")
    public ResponseMessage<?> getById(@PathVariable String id) {

        try {
            ObjectId objectId = new ObjectId(id);
            return responseHandler.generateResponseMessage(equipmentService.getById(objectId, Equipment.class),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }
    }

    @GetMapping
    public ResponseMessage<?> getAll() {
        try {
            return responseHandler.generateResponseMessage(equipmentService.getAll(Equipment.class),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }
    }

}
