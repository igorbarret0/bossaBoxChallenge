package com.challenge.bossaboxChallenge.repository;

import com.challenge.bossaboxChallenge.entitiy.Tag;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface TagRepository extends JpaRepository<Tag, Long> {

    Optional<Tag> findByName(String name);

}
