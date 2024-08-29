package com.cuonglv.learning_spring.controller;

import java.util.HashSet;
import java.util.Set;

import javax.inject.Inject;

import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cuonglv.learning_spring.utility.model.msg.response.ResponseMessage;
import com.cuonglv.learning_spring.utility.response.handler.ResponseHandler;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cuonglv.learning_spring.context.RequestContext;
import com.cuonglv.learning_spring.data.Role;
import com.cuonglv.learning_spring.data.User;
import com.cuonglv.learning_spring.model.RegisterRequest;
import com.cuonglv.learning_spring.repository.RoleRepository;
import com.cuonglv.learning_spring.repository.UserRepository;
import com.cuonglv.learning_spring.security.JwtUtil;
import com.cuonglv.learning_spring.service.UserService;
import com.cuonglv.learning_spring.utility.helper.GsonHelper;
import com.google.gson.JsonObject;

@RestController
@RequestMapping("/api/employee")
public class EmployeeController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userDetailsService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private JwtUtil jwtUtil;

    @Inject
    RequestContext requestContext;

    @Autowired
    ResponseHandler responseHandler;

    @GetMapping("/info")
    public ResponseMessage<?> getEmployeeInfo(@RequestBody JsonObject req) throws Exception {
        ResponseMessage<?> responseMessage = null;
        try {
            String username = GsonHelper.getAsString(req, "username");
            UserDetails userDetail = userDetailsService.loadUserByUsername(username);
            User user = userDetailsService.getUserByUsername(userDetail.getUsername());

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
            userDetailsService.updateUser(user.getUsername(), user);
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
            userDetailsService.deleteUser(username);
            responseMessage = responseHandler.generateResponseMessage("Delete user successfully",
                    requestContext.getRequestId());
        } catch (Exception e) {
            responseMessage = responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }
        return responseMessage;
    }

    @PostMapping("/create")
    public ResponseMessage<?> createEmployee(@RequestBody RegisterRequest registerRequest) throws Exception {
        ResponseMessage<?> responseMessage = null;
        try {
            String encodedPassword = passwordEncoder.encode(registerRequest.getPassword());
            User newUser = new User();
            newUser.setUsername(registerRequest.getUsername());
            newUser.setPassword(encodedPassword);

            // Gán quyền cho user
            Set<Role> roles = new HashSet<>();
            Optional<Role> role = roleRepository.findByName(registerRequest.getRole());
            if (role.isPresent()) {
                roles.add(role.get());
            } else {
                // Tạo quyền mới nếu chưa tồn tại
                Role newRole = new Role();
                newRole.setName(registerRequest.getRole());
                roleRepository.save(newRole);
                roles.add(newRole);
            }
            newUser.setRoles(roles);
            responseMessage = responseHandler.generateResponseMessage("Create user successfully",
                    requestContext.getRequestId());
        } catch (Exception e) {
            responseMessage = responseHandler.generateResponseMessage(e, requestContext.getRequestId());
        }
        return responseMessage;
    }
}
