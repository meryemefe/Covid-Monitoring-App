package com.bilkent.covidmonitoringservice.controller;

import com.bilkent.covidmonitoringservice.entity.User;
import com.bilkent.covidmonitoringservice.exception.NoUserFoundException;
import com.bilkent.covidmonitoringservice.request.UserRequest;
import com.bilkent.covidmonitoringservice.request.UserUpdateRequest;
import com.bilkent.covidmonitoringservice.security.CustomUserDetailsService;
import com.bilkent.covidmonitoringservice.service.UserService;
import com.bilkent.covidmonitoringservice.util.AppResponse;
import com.bilkent.covidmonitoringservice.util.AppResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/users")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @ApiOperation("Add a new user")
    @PostMapping
    public AppResponse<User> addUser(@RequestBody UserRequest request) {
        User addedUser = userService.add(request.toUser());
        if (addedUser == null) {
            return AppResponses.failure("A user with given username already exits.");
        } else {
            return AppResponses.from(addedUser);
        }
    }

    @ApiOperation("Get current user")
    @GetMapping
    public AppResponse<User> getCurrent() {
        User user = null;
        try {
            user = customUserDetailsService.getCurrentUser();
            return AppResponses.from(user);
        } catch (Exception e) {
            return AppResponses.failure("You are not logged in.");
        }

    }

    @ApiOperation("Get all users")
    @GetMapping("/all")
    public AppResponse<List<User>> getAllUsers() {
        List<User> users = userService.getAll();
        return AppResponses.from(users);
    }

    @ApiOperation("Get user by given id")
    @GetMapping("/{id}")
    public AppResponse<User> getUserById(@PathVariable Long id) {
        try {
            User user = userService.get(id);
            return AppResponses.from(user);
        } catch (NoUserFoundException e) {
            return AppResponses.failure(e.getMessage());
        }
    }

    @ApiOperation("Update user by given id")
    @PutMapping
    public AppResponse<User> updateUserById(@RequestBody UserUpdateRequest request) {
        try {
            User updatedUser = userService.update(request.toUser());
            return AppResponses.from(updatedUser);
        } catch (NoUserFoundException e) {
            return AppResponses.failure(e.getMessage());
        }
    }

    @ApiOperation("Delete user by given id")
    @DeleteMapping("/{id}")
    public AppResponse<String> deleteUserById(@PathVariable Long id) {
        try {
            User userToDelete = userService.get(id);
            userService.delete(userToDelete);
            return AppResponses.from("User deleted.");
        } catch (NoUserFoundException e) {
            return AppResponses.failure(e.getMessage());
        }
    }
}
