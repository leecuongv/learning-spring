package com.cuonglv.learning_spring.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuonglv.learning_spring.context.RequestContext;
import com.cuonglv.learning_spring.data.Supplier;
import com.cuonglv.learning_spring.service.SuplierService;
import com.cuonglv.learning_spring.utility.helper.GsonHelper;
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;
import com.cuonglv.learning_spring.utility.response.handler.ResponseHandler;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/api/supliers")
public class SuplierController {
    @Inject
    RequestContext requestContext;

    @Autowired
    ResponseHandler responseHandler;

    SuplierService suplierService;

    @PostMapping("/create")
    public ResponseMessage<?> createSuplier() {
        return responseHandler.generateResponseMessage("Create Suplier", requestContext.getRequestId());
    }

    @GetMapping("/{id}")
    public ResponseMessage<?> getSuplierById(@RequestParam String id) {
        try {
            return responseHandler.generateResponseMessage(suplierService.getSupplierById(id),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e.getMessage(), requestContext.getRequestId());
        }

    }

    @GetMapping
    public ResponseMessage<?> getAllSupliers() {
        try {
            return responseHandler.generateResponseMessage(suplierService.getAllSuppliers(),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e.getMessage(), requestContext.getRequestId());
        }
    }

    @PutMapping("/{id}")
    public ResponseMessage<?> updateSuplier(@RequestParam String id, @RequestBody JsonObject req) {

        try {

            Supplier supplier = new Supplier();
            supplier.setName(GsonHelper.getAsString(req, "name"));
            supplier.setAddress(GsonHelper.getAsString(req, "address"));
            supplier.setContactInfo(GsonHelper.getAsString(req, "contactInfo"));
            supplier.setNotes(GsonHelper.getAsString(req, "notes"));

            return responseHandler.generateResponseMessage(suplierService.updateSupplier(id, supplier),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e.getMessage(), requestContext.getRequestId());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<?> deleteSuplier(@RequestParam String id) {
        try {
            return responseHandler.generateResponseMessage(suplierService.deleteSupplier(id),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e.getMessage(), requestContext.getRequestId());
        }
    }
}
