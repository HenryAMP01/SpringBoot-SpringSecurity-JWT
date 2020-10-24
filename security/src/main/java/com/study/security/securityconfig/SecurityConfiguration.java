package com.study.security.securityconfig;

import com.study.security.securityconfig.jwt.JwtAuthenticationFilter;
import com.study.security.securityconfig.jwt.JwtAuthorizationFilter;
import com.study.security.securityconfig.jwt.JwtService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter{

    @Autowired private UserDetailsService userDetailsService;

    @Autowired private JwtService jwtService;

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailsService).passwordEncoder((passwordEncoder()));
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http.
        csrf().disable()
        .authorizeRequests()
            .antMatchers(HttpMethod.POST, "/api/auth").permitAll()
            .antMatchers("/api/users/**").hasAnyAuthority("USER", "ADMIN")
            .antMatchers("/api/**").hasAuthority("ADMIN")
        .anyRequest()
            .denyAll()
        .and()
        .addFilter(new JwtAuthenticationFilter(authenticationManager(), jwtService))
        .addFilter(new JwtAuthorizationFilter(authenticationManager(), jwtService))
        .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS);

    }
    
}
