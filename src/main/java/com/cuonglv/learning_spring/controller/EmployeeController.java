package com.cuonglv.learning_spring.controller;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cuonglv.learning_spring.data.User;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    // Create api to get employee info
    // Create api to update employee info
    // Create api to delete employee
    // Create api to create employee
    // Create api to get all employee
    // Create api to get employee by id
    // Create api to get employee by name

    @GetMapping("/info")
    public ResponseMessage<?> getEmployeeInfo() throws Exception {
        ResponseMessage<?> responseMessage = null;
        try {
            UserDetails userDetail = userDetailsService.loadUserByUsername(getUsername());
            User user = userService.getUserByUsername(userDetail.getUsername());

            responseMessage = responseHandler.generateResponseMessage(user, requestContext.getRequestId());
        } catch (Exception e) {
            responseMessage = responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }
        return responseMessage;
    }

    @PutMapping("/update")
    public ResponseMessage<?> updateEmployeeInfo(@RequestBody User user) throws Exception {
        ResponseMessage<?> responseMessage = null;
        try {
            userService.updateUser(user);
            responseMessage = responseHandler.generateResponseMessage("Update user successfully",
                    requestContext.getRequestId());
        } catch (Exception e) {
            responseMessage = responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }
        return responseMessage;
    }

    @DeleteMapping("/delete")
    public ResponseMessage<?> deleteEmployee(@RequestBody String username) throws Exception {
        ResponseMessage<?> responseMessage = null;
        try {
            userService.deleteUser(username);
            responseMessage = responseHandler.generateResponseMessage("Delete user successfully",
                    requestContext.getRequestId());
        } catch (Exception e) {
            responseMessage = responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }
        return responseMessage;
    }

    @PostMapping("/create")
    public ResponseMessage<?> createEmployee(@RequestBody User user) throws Exception {
        ResponseMessage<?> responseMessage = null;
        try {
            userService.createUser(user);
            responseMessage = responseHandler.generateResponseMessage("Create user successfully",
                    requestContext.getRequestId());
        } catch (Exception e) {
            responseMessage = responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }
        return responseMessage;
    }
}
