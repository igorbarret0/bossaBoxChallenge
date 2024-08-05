package com.challenge.bossaboxChallenge.controller;

import com.challenge.bossaboxChallenge.entitiy.Tag;
import com.challenge.bossaboxChallenge.service.TagService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tag")
public class TagController {

    private TagService tagService;

    public TagController(TagService tagService) {
        this.tagService = tagService;
    }

    @Operation(description = "Register a new tag")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Register a new tag successfully"),
    })
    @PostMapping
    public ResponseEntity<Void> registerTag(@RequestBody Tag tag) {

        tagService.registerTag(tag);
        return ResponseEntity.ok().build();
    }

    @Operation(description = "Get all tags available")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Successfully retrieved list of all tags")
    })
    @GetMapping
    public ResponseEntity<List<Tag>> getAllTags() {

        var allTags = tagService.getAllTags();
        return new ResponseEntity<>(allTags, HttpStatus.OK);
    }

}
