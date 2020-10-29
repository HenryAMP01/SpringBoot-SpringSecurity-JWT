package com.study.security.settings;

import com.study.security.settings.filter.JwtAuthenticationFilter;
import com.study.security.settings.filter.JwtAuthorizationFilter;
import com.study.security.settings.filter.SecondAuthenticationFilter;
import com.study.security.settings.jwt.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;


@EnableWebSecurity
public class SecuritySettings extends WebSecurityConfigurerAdapter{

    @Autowired private JwtService jwtService;

    @Autowired
    @Qualifier(value = "firstAuthenticationProvider")
    private AuthenticationProvider firstAuthenticationProvider;

    @Autowired
    @Qualifier(value = "secondAuthenticationProvider")
    private AuthenticationProvider secondAuthenticationProvider;

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.authenticationProvider(firstAuthenticationProvider)
        .authenticationProvider(secondAuthenticationProvider);
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
        csrf().disable()
        .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/auth").permitAll()
            .antMatchers(HttpMethod.POST, "/api/users/**").permitAll()
            .antMatchers("/api/users/**").hasAnyAuthority("USER", "ADMIN")
            .antMatchers("/api/**").hasAuthority("ADMIN")
        .anyRequest()
            .denyAll()
        .and()
        .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtService))
        .addFilter(new SecondAuthenticationFilter(authenticationManager(), jwtService))
        .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtService))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
    
}
