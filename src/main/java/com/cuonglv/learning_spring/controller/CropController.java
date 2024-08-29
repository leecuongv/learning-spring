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
import com.cuonglv.learning_spring.data.Crop;
import com.cuonglv.learning_spring.service.CropService;
import com.cuonglv.learning_spring.utility.helper.GsonHelper;
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;
import com.cuonglv.learning_spring.utility.response.handler.ResponseHandler;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/api/crop")
public class CropController {

    @Inject
    RequestContext requestContext;

    @Autowired
    ResponseHandler responseHandler;

    CropService cropService;

    @PutMapping("/{id}")
    public ResponseMessage<?> updateCrop(@RequestParam String id, @RequestBody JsonObject req) throws Exception {

        Crop crop = new Crop();
        crop.setName(GsonHelper.getAsString(req, "name"));
        crop.setPlantDate(GsonHelper.getAsDate(req, "plantDate"));
        crop.setWeight(GsonHelper.getAsDouble(req, "weight"));

        return responseHandler.generateResponseMessage(cropService.updateCrop(id, crop), requestContext.getRequestId());
    }

    @PostMapping
    public ResponseMessage<?> create(@RequestBody JsonObject req) throws Exception {
        Crop crop = new Crop();
        crop.setName(GsonHelper.getAsString(req, "name"));
        crop.setPlantDate(GsonHelper.getAsDate(req, "plantDate"));
        crop.setWeight(GsonHelper.getAsDouble(req, "weight"));
        cropService.createCrop(crop);
        return responseHandler.generateResponseMessage(crop, requestContext.getRequestId());

    }

    @GetMapping("/{id}")
    public ResponseMessage<?> getById(@RequestParam String id) {
        return responseHandler.generateResponseMessage(cropService.getCropById(id), requestContext.getRequestId());
    }

    @GetMapping
    public ResponseMessage<?> getAll() {
        return responseHandler.generateResponseMessage(cropService.getAllCrops(), requestContext.getRequestId());
    }

}
