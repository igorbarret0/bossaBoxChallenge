package com.challenge.bossaboxChallenge.controller;

import com.challenge.bossaboxChallenge.dtos.CreateUserDto;
import com.challenge.bossaboxChallenge.entitiy.User;
import com.challenge.bossaboxChallenge.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import jakarta.transaction.Transactional;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
public class UserController {

    private UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Transactional
    @PostMapping
    @Operation(description = "Register a new user")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Register a new user successfully")
    })
    public ResponseEntity<Void> saveUser(@RequestBody CreateUserDto dto) {

        userService.saveUser(dto);
        return ResponseEntity.ok().build();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @Operation(description = "Get all users only available for admins")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list of all users were retrieved successfully")
    })
    public ResponseEntity<List<User>> listUsers() {

        var allUsers = userService.listUsers();
        return new ResponseEntity<>(allUsers, HttpStatus.OK);
    }

}
