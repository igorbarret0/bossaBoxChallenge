package com.challenge.bossaboxChallenge.service;

import com.challenge.bossaboxChallenge.entitiy.Tag;
import com.challenge.bossaboxChallenge.repository.TagRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TagService {

    private TagRepository tagRepository;

    public TagService(TagRepository tagRepository) {
        this.tagRepository = tagRepository;
    }

    public void registerTag(Tag tag) {

        tagRepository.save(tag);
    }

    public List<Tag> getAllTags() {

        return tagRepository.findAll();
    }

}
