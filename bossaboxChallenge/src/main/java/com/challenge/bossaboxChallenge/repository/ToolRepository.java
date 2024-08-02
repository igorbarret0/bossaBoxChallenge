package com.challenge.bossaboxChallenge.repository;

import com.challenge.bossaboxChallenge.entitiy.Tag;
import com.challenge.bossaboxChallenge.entitiy.Tool;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ToolRepository extends JpaRepository<Tool, Long> {

    List<Tool> findByToolTags_Tag(Tag tag);


}
