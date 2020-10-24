package com.study.security.securityconfig.jwt;

import java.util.ArrayList;
import java.util.Date;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.JwtException;
import io.jsonwebtoken.Jwts;

@Service
public class JwtServiceImpl implements JwtService{

    public final String ISSUER = "Study team";
    public final int HOUR = 1;
    public final long EXPIRATION_TIME = 36000000L * HOUR;

    public String generateJwt(final String subject, Map<String, Object> claims) {
        return createJwt(subject, claims);
    }

    public String getSubject(final String jwt) {
        return getBodyToken(jwt).getSubject();
    }

    public Set<GrantedAuthority> getAuthorities(final String jwt) {

        ArrayList<?> authoritiesFound = (ArrayList<?>) getBodyToken(jwt).get("authorities");

        Set<GrantedAuthority> authorities = authoritiesFound.stream()
                .map(authority -> new SimpleGrantedAuthority(authority.toString())).collect(Collectors.toSet());

        return authorities;

    }

    // Helpers methods

    private Claims getBodyToken(final String jwt) {

        Jws<Claims> jwsClaims;

        try {

            jwsClaims = Jwts.parserBuilder().setSigningKey(SecurityConstants.JWT_KEY).build().parseClaimsJws(jwt);

        } catch (JwtException e) {

            throw new JwtException("Signature not valid! ");

        }

        Claims claims = jwsClaims.getBody();

        return claims;
    }

    private String createJwt(final String subject, Map<String, Object> claims) {

        return Jwts.builder().setClaims(claims).setIssuer(ISSUER).setSubject(subject).setAudience(subject)
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME)).setIssuedAt(new Date())
                .setId(UUID.randomUUID().toString()).signWith(SecurityConstants.JWT_KEY).compact();

    }
    
}
