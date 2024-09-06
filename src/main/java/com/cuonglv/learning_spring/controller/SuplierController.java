package com.cuonglv.learning_spring.controller;

import javax.inject.Inject;

import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cuonglv.learning_spring.context.RequestContext;
import com.cuonglv.learning_spring.data.Supplier;
import com.cuonglv.learning_spring.service.SupplierService;
import com.cuonglv.learning_spring.utility.helper.GsonHelper;
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;
import com.cuonglv.learning_spring.utility.response.handler.ResponseHandler;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/api/suppliers")
public class SuplierController {
    @Inject
    RequestContext requestContext;

    @Autowired
    ResponseHandler responseHandler;

    @Autowired
    SupplierService supplierService;

    @PostMapping("/create")
    public ResponseMessage<?> createSupplier(@RequestBody JsonObject req) throws Exception {
        try {
            Supplier supplier = new Supplier();
            supplier.setName(GsonHelper.getAsString(req, "name"));
            supplier.setAddress(GsonHelper.getAsString(req, "address"));
            supplier.setContactInfo(GsonHelper.getAsString(req, "contactInfo"));
            supplier.setNotes(GsonHelper.getAsString(req, "notes"));
            System.err.println(supplier);
            supplierService.create(supplier);
            return responseHandler.generateResponseMessage(supplier, requestContext.getRequestId());
        } catch (Exception e) {
            e.printStackTrace();
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }

    }

    @GetMapping("/{id}")
    public ResponseMessage<?> getSupplierById(@PathVariable ObjectId id) {
        try {
            return responseHandler.generateResponseMessage(supplierService.getById(id),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e.getMessage(), requestContext.getRequestId());
        }

    }

    @GetMapping
    public ResponseMessage<?> getAllSuppliers() {
        try {
            return responseHandler.generateResponseMessage(supplierService.getAll(),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e.getMessage(), requestContext.getRequestId());
        }
    }

    @PutMapping("/{id}")
    public ResponseMessage<?> updateSupplier(@PathVariable String id, @RequestBody JsonObject req) {

        try {

            Supplier supplier = new Supplier();
            supplier.setName(GsonHelper.getAsString(req, "name"));
            supplier.setAddress(GsonHelper.getAsString(req, "address"));
            supplier.setContactInfo(GsonHelper.getAsString(req, "contactInfo"));
            supplier.setNotes(GsonHelper.getAsString(req, "notes"));

            return responseHandler.generateResponseMessage(supplierService.update(id, supplier),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e.getMessage(), requestContext.getRequestId());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<?> deleteSupplier(@PathVariable String id) {
        try {
            return responseHandler.generateResponseMessage(supplierService.delete(id),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e.getMessage(), requestContext.getRequestId());
        }
    }
}
