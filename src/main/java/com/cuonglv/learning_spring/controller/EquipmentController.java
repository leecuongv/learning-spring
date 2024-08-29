package com.cuonglv.learning_spring.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
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
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;
import com.cuonglv.learning_spring.utility.response.handler.ResponseHandler;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/api/equipment")
public class EquipmentController {
    @Inject
    RequestContext requestContext;

    @Autowired
    ResponseHandler responseHandler;

    EquipmentService equipmentService;

    @PostMapping
    public ResponseMessage<?> create(@RequestBody JsonObject req) throws Exception {
        Equipment equipment = new Equipment();
        equipment.setName(GsonHelper.getAsString(req, "name"));
        equipment.setType(GsonHelper.getAsString(req, "type"));
        equipment.setPurchaseDate(GsonHelper.getAsDate(req, "purchaseDate"));
        equipment.setStatus(GsonHelper.getAsString(req, "status"));
        equipment.setPrice(GsonHelper.getAsDouble(req, "price"));
        equipment.setDescription(GsonHelper.getAsString(req, "description"));
        equipmentService.createEquipment(equipment);
        return responseHandler.generateResponseMessage(equipment, requestContext.getRequestId());

    }

    @PutMapping("/{id}")
    public ResponseMessage<?> updateEquipment(@RequestParam String id, @RequestBody JsonObject req) throws Exception {

        Equipment equipment = new Equipment();
        equipment.setName(GsonHelper.getAsString(req, "name"));
        equipment.setType(GsonHelper.getAsString(req, "type"));
        equipment.setPurchaseDate(GsonHelper.getAsDate(req, "purchaseDate"));
        equipment.setStatus(GsonHelper.getAsString(req, "status"));
        equipment.setPrice(GsonHelper.getAsDouble(req, "price"));
        equipment.setDescription(GsonHelper.getAsString(req, "description"));

        return responseHandler.generateResponseMessage(equipmentService.updateEquipment(id, equipment),
                requestContext.getRequestId());
    }

    @GetMapping("/{id}")
    public ResponseMessage<?> getById(@RequestParam String id) {
        return responseHandler.generateResponseMessage(equipmentService.getEquipmentById(id),
                requestContext.getRequestId());
    }

    @GetMapping
    public ResponseMessage<?> getAll() {
        return responseHandler.generateResponseMessage(equipmentService.getAllEquipments(),
                requestContext.getRequestId());
    }

}
