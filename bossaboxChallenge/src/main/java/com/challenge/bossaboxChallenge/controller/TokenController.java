package com.challenge.bossaboxChallenge.controller;

import com.challenge.bossaboxChallenge.dtos.LoginRequest;
import com.challenge.bossaboxChallenge.dtos.LoginResponse;
import com.challenge.bossaboxChallenge.service.TokenService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/login")
public class TokenController {

    private TokenService tokenService;

    public TokenController(TokenService tokenService) {
        this.tokenService = tokenService;
    }

    @PostMapping
    @Operation(description = "Login")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The user logged successfully")
    })
    public ResponseEntity<LoginResponse> login(@RequestBody LoginRequest loginRequest) {

        var response = tokenService.login(loginRequest);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

}
