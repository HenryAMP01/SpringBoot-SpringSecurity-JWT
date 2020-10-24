package com.study.security.securityconfig.jwt;

import java.security.Key;

import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

public class SecurityConstants {

    public static final Key JWT_KEY = Keys.secretKeyFor(SignatureAlgorithm.HS256);
    public static final String JWT_PREFIX_TOKEN = "Bearer ";

    public static final String AUTH_PATH = "/api/auth";
    
}
