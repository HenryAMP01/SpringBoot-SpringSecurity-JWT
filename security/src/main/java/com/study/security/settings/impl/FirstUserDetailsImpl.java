package com.study.security.settings.impl;

import java.util.Set;
import java.util.stream.Collectors;

import com.study.security.model.service.IUserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service(value =  "firstUserDetailsImpl")
public class FirstUserDetailsImpl implements UserDetailsService {

    @Autowired
    private IUserService userService;
    

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username){

        com.study.security.model.entity.User user = userService.findByUsername(username);

        Set<GrantedAuthority> authorities = user.getAuthorities().stream()
                .map(authority -> new SimpleGrantedAuthority(authority.getName())).collect(Collectors.toSet());

        return new User(user.getUsername(), user.getPassword(), authorities);
    }

}
