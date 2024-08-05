package com.challenge.bossaboxChallenge.dtos;

public record LoginResponse(
        String accessToken,
        Long expiresIn
) {
}
