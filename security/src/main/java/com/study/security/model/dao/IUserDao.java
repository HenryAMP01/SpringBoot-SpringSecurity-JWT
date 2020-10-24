package com.study.security.model.dao;

import com.study.security.model.entity.User;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface IUserDao extends JpaRepository<User, Long>{

    public User findByUsername(String username);
    
}
