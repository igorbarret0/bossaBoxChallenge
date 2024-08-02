package com.challenge.bossaboxChallenge.dtos;

import com.challenge.bossaboxChallenge.entitiy.Tag;

import java.util.List;

public record RegisterToolDto(
        String title,
        String link,
        String description,
        List<String> tags
) {
}
