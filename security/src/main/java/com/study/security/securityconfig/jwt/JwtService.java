package com.study.security.securityconfig.jwt;

import java.util.Map;
import java.util.Set;

import org.springframework.security.core.GrantedAuthority;

public interface JwtService {

    public String generateJwt(final String subject, Map<String, Object> claims);

    public String getSubject(final String jwt);

    public Set<GrantedAuthority> getAuthorities(final String jwt);

}
