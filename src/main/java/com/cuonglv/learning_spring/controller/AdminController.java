package com.cuonglv.learning_spring.controller;

import javax.inject.Inject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuonglv.learning_spring.context.RequestContext;
import com.cuonglv.learning_spring.data.User;
import com.cuonglv.learning_spring.security.JwtUtil;
import com.cuonglv.learning_spring.service.UserService;
import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;
import com.cuonglv.learning_spring.utility.response.handler.ResponseHandler;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/api/admin")
public class AdminController {
    @Autowired
    public UserDetailsService userDetailsService;

    @Autowired
    public UserService userService;
    @Autowired
    public JwtUtil jwtUtil;
    @Inject
    RequestContext requestContext;

    @Autowired
    ResponseHandler responseHandler;
    Gson gson = new Gson();

    @GetMapping("/me")
    public ResponseMessage<?> getAuthenticatedUser() throws Exception {

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

    // Create api to delete user
    @DeleteMapping("/delete")
    public ResponseMessage<?> deleteUser(@RequestBody String username) throws Exception {
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

    @GetMapping("/userinfo")
    public ResponseMessage<?> getUserInfo(@RequestBody String username) throws Exception {
        ResponseMessage<?> responseMessage = null;
        try {
            UserDetails userDetail = userDetailsService.loadUserByUsername(username);
            User user = userService.getUserByUsername(userDetail.getUsername());
            responseMessage = responseHandler.generateResponseMessage(user,
                    requestContext.getRequestId());
        } catch (Exception e) {
            responseMessage = responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }
        return responseMessage;
    }

    public String getUsername() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        // if(authentication.getAuthorities().toString())
        return authentication.getName();
    }

}
