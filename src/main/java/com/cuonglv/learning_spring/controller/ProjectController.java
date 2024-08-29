package com.cuonglv.learning_spring.controller;

import org.springframework.web.bind.annotation.RestController;

import com.cuonglv.learning_spring.context.RequestContext;
import com.cuonglv.learning_spring.data.Project;
import com.cuonglv.learning_spring.service.ProjectService;
import com.cuonglv.learning_spring.utility.helper.GsonHelper;
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;
import com.cuonglv.learning_spring.utility.response.handler.ResponseHandler;
import com.google.gson.JsonObject;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
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

    ProjectService projectService;

    @PostMapping
    public ResponseMessage<?> create(@RequestBody JsonObject req) throws Exception {
        Project project = new Project();
        project.setName(GsonHelper.getAsString(req, "name"));
        project.setStartDate(GsonHelper.getAsDate(req, "startDate"));
        project.setEndDate(GsonHelper.getAsDate(req, "endDate"));
        project.setDescription(GsonHelper.getAsString(req, "description"));
        project.setStatus(GsonHelper.getAsString(req, "status"));
        project.setManager(GsonHelper.getAsString(req, "manager"));
        project.setDepartment(GsonHelper.getAsString(req, "department"));
        projectService.createProject(project);
        return responseHandler.generateResponseMessage(project, requestContext.getRequestId());

    }

    @GetMapping("/{id}")
    public ResponseMessage<?> getById(@RequestParam String id) {
        return responseHandler.generateResponseMessage(projectService.getProjectById(id),
                requestContext.getRequestId());
    }

    @DeleteMapping("/{id}")
    public ResponseMessage<?> deleteProject(@RequestParam String id) {
        return responseHandler.generateResponseMessage(projectService.deleteProject(id), requestContext.getRequestId());
    }

    @GetMapping
    public ResponseMessage<?> getAll() {
        return responseHandler.generateResponseMessage(projectService.getAll(), requestContext.getRequestId());
    }

    @PutMapping("/{id}")
    public ResponseMessage<?> updateProject(@RequestParam String id, @RequestBody JsonObject req) throws Exception {

        Project project = new Project();
        project.setName(GsonHelper.getAsString(req, "name"));
        project.setStartDate(GsonHelper.getAsDate(req, "startDate"));
        project.setEndDate(GsonHelper.getAsDate(req, "endDate"));
        project.setDescription(GsonHelper.getAsString(req, "description"));
        project.setStatus(GsonHelper.getAsString(req, "status"));
        project.setManager(GsonHelper.getAsString(req, "manager"));
        project.setDepartment(GsonHelper.getAsString(req, "department"));

        return responseHandler.generateResponseMessage(projectService.updateProject(id, project),
                requestContext.getRequestId());
    }

}
