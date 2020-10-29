package com.study.security.settings.encoder;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.crypto.password.Pbkdf2PasswordEncoder;

@Configuration
public class PasswordEncoderSettings {

    @Bean(value = "firstPasswordEncoder")
    public PasswordEncoder firstPasswordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

    
    @Bean(value = "secondPasswordEncoder")
    public PasswordEncoder secondPasswordEncoder(){
        return new Pbkdf2PasswordEncoder();
    }

    
}
