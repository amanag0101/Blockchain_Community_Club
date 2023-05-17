package com.bcc.food_order_system.controller;

import com.bcc.food_order_system.contracts.authentication.LoginUserRequest;
import com.bcc.food_order_system.contracts.authentication.RegisterUserRequest;
import com.bcc.food_order_system.entity.User;
import com.bcc.food_order_system.service.AuthenticationService;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CompletionStage;

@RestController
@RequestMapping("/authentication")
@Slf4j
public class AuthenticationController {
    private final AuthenticationService authenticationService;

    @Autowired
    public AuthenticationController(AuthenticationService authenticationService) {
        this.authenticationService = authenticationService;
    }

    @PostMapping("/register")
    public CompletionStage<ResponseEntity<User>> register(@Valid @RequestBody RegisterUserRequest registerUserRequest) {
        log.info("Received request to register user: " + registerUserRequest.toString());
        return authenticationService.register(registerUserRequest)
                .thenApply(ResponseEntity::ok);
    }

    @PostMapping("/login")
    public CompletionStage<ResponseEntity<Void>> register(@Valid @RequestBody LoginUserRequest loginUserRequest) {
        log.info("Received request to login user: " + loginUserRequest.toString());
        return authenticationService.login(loginUserRequest)
                .thenApply(ResponseEntity::ok);
    }
}
