package com.bilkent.covidmonitoringservice.controller;

import com.bilkent.covidmonitoringservice.entity.User;
import com.bilkent.covidmonitoringservice.request.LoginRequest;
import com.bilkent.covidmonitoringservice.security.CustomUserDetailsService;
import com.bilkent.covidmonitoringservice.service.UserService;
import com.bilkent.covidmonitoringservice.util.AppResponse;
import com.bilkent.covidmonitoringservice.util.AppResponses;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin("*")
@RequestMapping("/api/auth")
public class AuthenticationController {
    @Autowired
    CustomUserDetailsService customUserDetailsService;

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserService userService;

    @ApiOperation("Login to the system with user credentials")
    @PostMapping("/login")
    public AppResponse<User> login(@RequestBody LoginRequest loginRequest) {
        SecurityContextHolder.clearContext();
        User user = null;
        try {
            user = userService.getByUsername(loginRequest.getUsername());
            Authentication authentication =
                    authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));
            SecurityContextHolder.getContext().setAuthentication(authentication);
            return AppResponses.from(user);
        } catch (Exception e) {
            if (user != null) {
                return AppResponses.failure("Wrong password entered.");
            } else {
                return AppResponses.failure("Wrong username entered.");
            }
        }
    }

    @ApiOperation("Logout from the system with user credentials")
    @GetMapping("/logout")
    public ResponseEntity<?> login() {
        SecurityContextHolder.clearContext();
        return new ResponseEntity<>("You successfully logged out", HttpStatus.OK);
    }
}
