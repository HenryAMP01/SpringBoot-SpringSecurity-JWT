package com.study.security.model.service;

import java.util.List;

import com.study.security.model.entity.Authority;

public interface IAuthorityService {

    public Authority findByAuthorityId(Long id);

    public Authority saveAuthority(Authority authority);

    public List<Authority> findAllAuthorities();

    public void deleteByAuthorityId(Long id);
    
}
