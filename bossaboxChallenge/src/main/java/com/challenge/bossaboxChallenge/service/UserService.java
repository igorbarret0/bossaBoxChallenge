package com.challenge.bossaboxChallenge.service;

import com.challenge.bossaboxChallenge.dtos.CreateUserDto;
import com.challenge.bossaboxChallenge.entitiy.Role;
import com.challenge.bossaboxChallenge.entitiy.User;
import com.challenge.bossaboxChallenge.repository.RoleRepository;
import com.challenge.bossaboxChallenge.repository.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
public class UserService {

    private UserRepository userRepository;
    private RoleRepository roleRepository;
    private BCryptPasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository,
                       BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }



    public void saveUser(CreateUserDto dto) {


        var basicRole = roleRepository.findByName(Role.Values.BASIC.name());

        var userExists = userRepository.findByUsername(dto.username());
        if (userExists.isPresent()) {
            throw new RuntimeException("This account already exists");
        }

        var newUser = new User();
        newUser.setUsername(dto.username());
        newUser.setPassword(passwordEncoder.encode(dto.password()));
        newUser.setRoles(Set.of(basicRole));

        userRepository.save(newUser);
    }

    public List<User> listUsers() {

        var allUsers = userRepository.findAll();
        return allUsers;
    }

}
