package com.challenge.bossaboxChallenge.service;

import com.challenge.bossaboxChallenge.dtos.LoginRequest;
import com.challenge.bossaboxChallenge.dtos.LoginResponse;
import com.challenge.bossaboxChallenge.entitiy.Role;
import com.challenge.bossaboxChallenge.repository.UserRepository;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.oauth2.jwt.JwtClaimsSet;
import org.springframework.security.oauth2.jwt.JwtEncoder;
import org.springframework.security.oauth2.jwt.JwtEncoderParameters;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.util.stream.Collectors;

@Service
public class TokenService {

    private JwtEncoder jwtEncoder;
    private BCryptPasswordEncoder passwordEncoder;
    private UserRepository userRepository;

    public TokenService(JwtEncoder jwtEncoder, BCryptPasswordEncoder passwordEncoder,
                        UserRepository userRepository) {
        this.jwtEncoder = jwtEncoder;
        this.passwordEncoder = passwordEncoder;
        this.userRepository = userRepository;
    }

    public LoginResponse login(LoginRequest request) {

        var userExists = userRepository.findByUsername(request.username());

        if (userExists.isEmpty() ||
            !userExists.get().isLoginCorrect(request, passwordEncoder)) {
            throw new BadCredentialsException("User or Password is invalid");
        }

        var now = Instant.now();
        var expiresIn = 300L;

        var scopes = userExists.get().getRoles()
                .stream()
                .map(Role::getName)
                .collect(Collectors.joining(" "));

        var claims = JwtClaimsSet.builder()
                .issuer("twitterSimplified")
                .subject(userExists.get().getId().toString())
                .issuedAt(now)
                .expiresAt(now.plusSeconds(expiresIn))
                .claim("scope", scopes)
                .build();

        var jwtValue = jwtEncoder.encode(JwtEncoderParameters.from(claims)).getTokenValue();

        return new LoginResponse(jwtValue, expiresIn);

    }

}
