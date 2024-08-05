package com.challenge.bossaboxChallenge.repository;

import com.challenge.bossaboxChallenge.entitiy.Role;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RoleRepository extends JpaRepository<Role, Long> {

    Role findByName(String name);
}
