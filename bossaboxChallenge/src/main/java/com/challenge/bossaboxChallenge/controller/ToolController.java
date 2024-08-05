package com.challenge.bossaboxChallenge.controller;

import com.challenge.bossaboxChallenge.dtos.RegisterToolDto;
import com.challenge.bossaboxChallenge.dtos.ToolResponseDto;
import com.challenge.bossaboxChallenge.service.ToolService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tools")
public class ToolController {

    private ToolService toolService;

    public ToolController(ToolService toolService) {
        this.toolService = toolService;
    }

    @Operation(description = "Register a new tool")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "A new tool was successfully registered")
    })
    @PostMapping
    public ResponseEntity<RegisterToolDto> registerTool(@RequestBody RegisterToolDto dto) {

        var response = toolService.registerTool(dto);

        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @Operation(description = "Get all available tools")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "The list of available tools were retrieved successfully")
    })
    @GetMapping
    public ResponseEntity<List<ToolResponseDto>> getAllTools(
            @RequestParam(value = "tag", required = false) String tagName) {

        List<ToolResponseDto> response;

        if (tagName != null && !tagName.isEmpty()) {
            response = toolService.getToolsByTag(tagName);
        } else {
            response = toolService.getAllTools();
        }

        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @Operation(description = "Delete a tool by ID")
    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "A tool was removed successfully")
    })
    @DeleteMapping("/{id}")
    @PreAuthorize("hasAuthority('SCOPE_ADMIN')")
    public ResponseEntity deleteTool(@PathVariable(name = "id") Long id) {

        toolService.deleteTool(id);
        return ResponseEntity.ok().build();
    }


}
