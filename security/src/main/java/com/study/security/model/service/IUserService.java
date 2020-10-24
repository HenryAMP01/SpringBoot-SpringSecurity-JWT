package com.study.security.model.service;

import java.util.List;

import com.study.security.model.entity.User;

public interface IUserService {

    public User findByUserId(Long id);

    public User findByUsername(String username);

    public User saveUser(User user);

    public List<User> findAllUsers();

    public void deleteByUserId(Long id);

}
