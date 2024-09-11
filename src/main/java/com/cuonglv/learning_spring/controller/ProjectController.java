package com.cuonglv.learning_spring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.cuonglv.learning_spring.context.RequestContext;
import com.cuonglv.learning_spring.data.Project;
import com.cuonglv.learning_spring.service.ProjectService;
import com.cuonglv.learning_spring.utility.helper.GsonHelper;
import com.cuonglv.learning_spring.utility.helper.ObjectIdAdapter;
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;
import com.cuonglv.learning_spring.utility.response.handler.ResponseHandler;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.itextpdf.text.pdf.PdfStructTreeController.returnType;

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
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@RestController
@RequestMapping("/api/project")

public class ProjectController {
    @Inject
    RequestContext requestContext;

    @Autowired
    ResponseHandler responseHandler;
    @Autowired
    ProjectService projectService;
    Gson gson = new GsonBuilder().setDateFormat("yyyy-MM-dd")
            .registerTypeAdapter(ObjectId.class, new ObjectIdAdapter()).create();

    @PostMapping
    public ResponseMessage<?> create(@RequestBody JsonObject req) throws Exception {
        try {
            Project project = gson.fromJson(req, Project.class);
            projectService.create(project);
            return responseHandler.generateResponseMessage(project, requestContext.getRequestId());

        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }
    }

    @GetMapping("/{id}")
    public ResponseMessage<?> getById(@PathVariable String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return responseHandler.generateResponseMessage(projectService.getById(objectId, Project.class),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<?> deleteProject(@PathVariable String id) {
        try {
            ObjectId objectId = new ObjectId(id);
            return responseHandler.generateResponseMessage(projectService.delete(objectId, Project.class),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }

    }

    @GetMapping
    public ResponseMessage<?> getAll() {
        try {
            return responseHandler.generateResponseMessage(projectService.getAll(Project.class),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }
    }

    @PutMapping("/{id}")
    public ResponseMessage<?> updateProject(@PathVariable String id, @RequestBody JsonObject req) throws Exception {

        try {
            ObjectId objectId = new ObjectId(id);

            Project project = gson.fromJson(req, Project.class);

            return responseHandler.generateResponseMessage(projectService.update(objectId, project, Project.class),
                    requestContext.getRequestId());
        } catch (Exception e) {
            return responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }
    }

}
