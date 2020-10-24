package com.study.security.model.mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import com.study.security.model.dto.AuthorityDTO;
import com.study.security.model.entity.Authority;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper(componentModel = "spring")
public interface AuthorityMapper{

    AuthorityMapper INSTANCE = Mappers.getMapper(AuthorityMapper.class);

    AuthorityDTO authorityToAuthorityDTO(Authority authority);

    default List<AuthorityDTO> authorityListToAuthorityDTOlist(List<Authority> authorities){

        if(authorities == null){

            return new ArrayList<AuthorityDTO>();

        }

        return authorities.stream().map(this::authorityToAuthorityDTO).collect(Collectors.toList());

    }
}
