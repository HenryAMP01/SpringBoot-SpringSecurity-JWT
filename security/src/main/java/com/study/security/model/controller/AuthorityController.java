package com.study.security.model.controller;

import java.time.Instant;
import java.util.List;

import javax.validation.Valid;

import com.study.security.model.dto.AuthorityDTO;
import com.study.security.model.entity.Authority;
import com.study.security.model.mapper.AuthorityMapper;
import com.study.security.model.service.IAuthorityService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

@RequestMapping("/api/authorities")
@RestController
@CrossOrigin("*")
public class AuthorityController {

    @Autowired private AuthorityMapper authorityMapper;
    @Autowired private IAuthorityService authorityService;

    @GetMapping
    public List<AuthorityDTO> findallAuthorities() {
        return authorityMapper.authorityListToAuthorityDTOlist(authorityService.findAllAuthorities());
    }

    @GetMapping(value = "/{id}")
    public AuthorityDTO findByAuthorityId(@PathVariable Long id) {
        return authorityMapper.authorityToAuthorityDTO(authorityService.findByAuthorityId(id));
    }

    @PostMapping
    public AuthorityDTO saveAuthority(@Valid @RequestBody Authority authority) {
        return authorityMapper.authorityToAuthorityDTO(authorityService.saveAuthority(authority));
    }

    @PutMapping(value = "/{id}")
    public AuthorityDTO updateByAuthorityId(@Valid @RequestBody Authority authority, @PathVariable Long id) {

        Authority authorityFound = authorityService.findByAuthorityId(id);

        if (authorityFound != null) {

            // authorityFound.setId(authority.getId());
            authorityFound.setAuthorityCode(authority.getAuthorityCode());
            authorityFound.setName(authority.getName());
            // authorityFound.setCreateAt(authority.getCreateAt());
            authorityFound.setUpdateAt(Instant.now());

        }

        return authorityMapper.authorityToAuthorityDTO(authorityService.saveAuthority(authority));
    }

    @DeleteMapping(value = "/{id}")
    public void deleteByAuthorityId(@PathVariable Long id){
        authorityService.deleteByAuthorityId(id);
    }

}
