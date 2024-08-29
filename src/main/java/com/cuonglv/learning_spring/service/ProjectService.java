package com.cuonglv.learning_spring.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;

import com.cuonglv.learning_spring.data.Project;

import java.util.List;

public class ProjectService {
    @Autowired
    private MongoTemplate mongoTemplate;

    public Project createProject(Project project) {
        mongoTemplate.save(project);
        return project;
    }

    public boolean deleteProject(String id) {
        Project project = mongoTemplate.findById(id, Project.class);
        if (project == null) {
            return false;
        }
        mongoTemplate.remove(project);
        return true;
    }

    public Project updateProject(String id, Project updatedProject) {
        Project project = mongoTemplate.findById(id, Project.class);
        if (project == null) {
            throw new RuntimeException("Project not found");
        }
        project.setName(updatedProject.getName());
        project.setStartDate(updatedProject.getStartDate());
        project.setEndDate(updatedProject.getEndDate());
        project.setType(updatedProject.getType());
        project.setManager(updatedProject.getManager());
        project.setDepartment(updatedProject.getDepartment());

        project.setDescription(updatedProject.getDescription());
        mongoTemplate.save(project);
        return project;
    }

    public Project getProjectById(String id) {
        return mongoTemplate.findById(id, Project.class);
    }

    public List<Project> getAll() {
        return mongoTemplate.findAll(Project.class);
    }
}
