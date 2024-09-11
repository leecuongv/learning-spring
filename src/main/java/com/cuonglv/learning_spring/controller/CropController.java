package com.cuonglv.learning_spring.controller;

import javax.inject.Inject;

import org.bson.types.ObjectId;
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
import com.cuonglv.learning_spring.utility.helper.ObjectIdAdapter;
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;
import com.cuonglv.learning_spring.utility.response.handler.ResponseHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/api/crop")
public class CropController {

    @Inject
    RequestContext requestContext;

    @Autowired
    ResponseHandler responseHandler;

    @Autowired
    CropService cropService;
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
            .registerTypeAdapter(ObjectId.class, new ObjectIdAdapter()).create();

    @PutMapping("/{id}")
    public ResponseMessage<?> updateCrop(@RequestParam String id, @RequestBody JsonObject req) throws Exception {

        ObjectId objectId = new ObjectId(id);
        Crop crop = gson.fromJson(req, Crop.class);

        return responseHandler.generateResponseMessage(cropService.update(objectId, crop, Crop.class),
                requestContext.getRequestId());
    }

    @PostMapping
    public ResponseMessage<?> create(@RequestBody JsonObject req) throws Exception {
        Crop crop = gson.fromJson(req, Crop.class);
        cropService.create(crop);
        return responseHandler.generateResponseMessage(crop, requestContext.getRequestId());

    }

    @GetMapping("/{id}")
    public ResponseMessage<?> getById(@RequestParam String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return responseHandler.generateResponseMessage(cropService.getById(objectId, Crop.class),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }

    }

    @GetMapping
    public ResponseMessage<?> getAll() {
        try {
            return responseHandler.generateResponseMessage(cropService.getAll(Crop.class),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }
    }

}
