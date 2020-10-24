package com.study.security.model.impl;

import java.util.List;

import com.study.security.model.dao.IAuthorityDao;
import com.study.security.model.entity.Authority;
import com.study.security.model.service.IAuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AuthorityImpl implements IAuthorityService {

    @Autowired private IAuthorityDao authorityDao;

    @Override
    @Transactional(readOnly = true)
    public Authority findByAuthorityId(Long id) {
        return authorityDao.findById(id).orElse(null);
    }

    @Override
    @Transactional
    public Authority saveAuthority(Authority authority) {
        return authorityDao.save(authority);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Authority> findAllAuthorities() {
        return authorityDao.findAll();
    }

    @Override
    @Transactional
    public void deleteByAuthorityId(Long id) {
        authorityDao.deleteAllByAuthorityId(id);
        authorityDao.deleteById(id);
    }
    
}
