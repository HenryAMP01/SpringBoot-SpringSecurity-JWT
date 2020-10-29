package com.study.security.settings.provider;

import com.study.security.settings.token.SecondAuthToken;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component(value = "secondAuthenticationProvider")
public class SecondAuthenticationProvider implements AuthenticationProvider {

    private static final Logger logger = LoggerFactory.getLogger(SecondAuthenticationProvider.class);

    @Autowired
    @Qualifier(value = "secondUserDetailsImpl")
    private UserDetailsService userDetailsService;

    @Autowired
    @Qualifier(value = "secondPasswordEncoder")
    private PasswordEncoder passwordEncoder;

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {

        String username = authentication.getName();
        String password = authentication.getCredentials().toString();

        UserDetails user = userDetailsService.loadUserByUsername(username);

        if (passwordEncoder.matches(password, user.getPassword())) {

            logger.info("AUTHENTICATION WITH SECOND AUTH PROVIDER WITH Pbkdf2");
            
            return new SecondAuthToken(username, password, user.getAuthorities());
        }

        throw new BadCredentialsException("Bad credentials!");
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return authentication.equals(SecondAuthToken.class);
    }
    
}
