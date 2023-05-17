package com.bcc.food_order_system.service;

import com.bcc.food_order_system.contracts.authentication.LoginUserRequest;
import com.bcc.food_order_system.contracts.authentication.RegisterUserRequest;
import com.bcc.food_order_system.entity.User;
import com.bcc.food_order_system.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.CompletionStage;

@Service
@Slf4j
public class AuthenticationService {
    private final UserRepository userRepository;

    @Autowired
    public AuthenticationService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public CompletionStage<User> register(RegisterUserRequest registerUserRequest) {
        log.info("Registering user...");
        return CompletableFuture.completedFuture(getUser(registerUserRequest))
                .thenApply(userRepository::save);
    }

    public CompletionStage<Void> login(LoginUserRequest loginUserRequest) {
        log.info("Logging in the user...");
        return CompletableFuture.completedFuture(userRepository.findByUsernameAndPassword(loginUserRequest.getUsername(), loginUserRequest.getPassword())
                .orElseThrow(() -> new RuntimeException("User not found!")))
                .thenAccept(ignored -> {});
    }

    private User getUser(RegisterUserRequest registerUserRequest) {
        return User.builder()
                .username(registerUserRequest.getUsername())
                .password(registerUserRequest.getPassword())
                .build();
    }
}
