package com.study.security.model.controller;

import java.time.Instant;
import java.util.List;

import javax.validation.Valid;

import com.study.security.model.dto.UserDTO;
import com.study.security.model.entity.User;
import com.study.security.model.mapper.UserMapper;
import com.study.security.model.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/api/users")
@RestController
@CrossOrigin("*")
public class UserController {

    @Autowired private PasswordEncoder passwordEncoder;

    @Autowired private UserMapper userMapper;

    @Autowired private IUserService userService;

    @GetMapping
    public List<UserDTO> findAllUsers() {
        return userMapper.userListToUserDTOlist(userService.findAllUsers());
    }

    @GetMapping(value = "/{id}")
    public UserDTO findByUserId(@PathVariable Long id) {
        return userMapper.userToUserDTO(userService.findByUserId(id));
    }

    @PostMapping
    public UserDTO saveUser(@Valid @RequestBody User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        return userMapper.userToUserDTO(userService.saveUser(user));
    }

    @PutMapping(value = "/{id}")
    public UserDTO updateByUserId(@Valid @RequestBody User user, @PathVariable Long id) {

        User userFound = userService.findByUserId(id);

        if (userFound != null) {

            // userFound.setId(user.getId());
            userFound.setUsername(user.getUsername());
            userFound.setPassword(passwordEncoder.encode(user.getPassword()));
            userFound.setAuthorities(user.getAuthorities());
            // userFound.setCreateAt(user.getCreateAt());
            userFound.setUpdateAt(Instant.now());

        }

        return userMapper.userToUserDTO(userService.saveUser(user));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteByUserId(@PathVariable Long id) {
        userService.deleteByUserId(id);
    }
}