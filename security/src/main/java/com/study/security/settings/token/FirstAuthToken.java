package com.study.security.settings.token;

import java.util.Collection;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

public class FirstAuthToken extends UsernamePasswordAuthenticationToken {

    public FirstAuthToken(Object principal, Object credentials) {
        super(principal, credentials);
    }

    public FirstAuthToken(Object principal, Object credentials, Collection<? extends GrantedAuthority> authorities) {
        super(principal, credentials, authorities);
    }

    private static final long serialVersionUID = 1L;
    
}
