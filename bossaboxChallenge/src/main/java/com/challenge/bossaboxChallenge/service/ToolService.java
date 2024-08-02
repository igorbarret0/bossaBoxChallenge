package com.challenge.bossaboxChallenge.service;

import com.challenge.bossaboxChallenge.dtos.RegisterToolDto;
import com.challenge.bossaboxChallenge.dtos.ToolResponseDto;
import com.challenge.bossaboxChallenge.entitiy.Tool;
import com.challenge.bossaboxChallenge.entitiy.ToolTag;
import com.challenge.bossaboxChallenge.entitiy.ToolTagId;
import com.challenge.bossaboxChallenge.repository.TagRepository;
import com.challenge.bossaboxChallenge.repository.ToolRepository;
import com.challenge.bossaboxChallenge.repository.ToolTagRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ToolService {

    private ToolRepository toolRepository;
    private TagRepository tagRepository;
    private ToolTagRepository toolTagRepository;

    public ToolService(ToolRepository toolRepository, TagRepository tagRepository, ToolTagRepository toolTagRepository) {
        this.toolRepository = toolRepository;
        this.tagRepository = tagRepository;
        this.toolTagRepository = toolTagRepository;
    }

    public RegisterToolDto registerTool(RegisterToolDto dto) {


        for (String tag : dto.tags()) {

            var tagExists = tagRepository.findByName(tag);

            if (tagExists.isEmpty()) {
                throw new RuntimeException("A tag you typed is invalid");
            }
        }

        var newTool = new Tool();
        newTool.setTitle(dto.title());
        newTool.setLink(dto.link());
        newTool.setDescription(dto.description());
        toolRepository.save(newTool);

        for (String tag : dto.tags()) {

            var foundTag = tagRepository.findByName(tag);

            var id = new ToolTagId(newTool.getId(), foundTag.get().getId());
            var toolTag = new ToolTag(id, newTool, foundTag.get());
            toolTagRepository.save(toolTag);
        }

        return dto;

    }

    public List<ToolResponseDto> getAllTools() {

        List<Tool> tools = toolRepository.findAll();
        List<ToolResponseDto> toolDtos = new ArrayList<>();

        for (Tool tool : tools) {
            List<String> tagNames = tool.getToolTags().stream()
                    .map(toolTag -> toolTag.getTag().getName())
                    .collect(Collectors.toList());

            ToolResponseDto dto = new ToolResponseDto();
            dto.setId(tool.getId());
            dto.setTitle(tool.getTitle());
            dto.setLink(tool.getLink());
            dto.setDescription(tool.getDescription());
            dto.setTags(tagNames);

            toolDtos.add(dto);
        }

        return toolDtos;
    }

    public List<ToolResponseDto> getToolsByTag(String tagName) {
        // Encontrar a tag pelo nome
        var tag = tagRepository.findByName(tagName).orElseThrow(() -> new RuntimeException("Tag not found"));

        // Buscar ferramentas que possuam essa tag
        List<Tool> tools = toolRepository.findByToolTags_Tag(tag);
        List<ToolResponseDto> toolDtos = new ArrayList<>();

        for (Tool tool : tools) {
            List<String> tagNames = tool.getToolTags().stream()
                    .map(toolTag -> toolTag.getTag().getName())
                    .collect(Collectors.toList());

            ToolResponseDto dto = new ToolResponseDto();
            dto.setId(tool.getId());
            dto.setTitle(tool.getTitle());
            dto.setLink(tool.getLink());
            dto.setDescription(tool.getDescription());
            dto.setTags(tagNames);

            toolDtos.add(dto);
        }

        return toolDtos;
    }

    public void deleteTool(Long id) {

        var tool = toolRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("A tool could not be found"));

        toolRepository.delete(tool);

    }

}
