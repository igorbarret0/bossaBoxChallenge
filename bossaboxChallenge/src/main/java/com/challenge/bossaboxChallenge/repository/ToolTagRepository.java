package com.challenge.bossaboxChallenge.repository;

import com.challenge.bossaboxChallenge.entitiy.ToolTag;
import com.challenge.bossaboxChallenge.entitiy.ToolTagId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ToolTagRepository extends JpaRepository<ToolTag, ToolTagId> {

}
