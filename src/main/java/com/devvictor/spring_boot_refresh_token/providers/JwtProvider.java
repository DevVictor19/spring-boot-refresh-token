package com.devvictor.spring_boot_refresh_token.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.devvictor.spring_boot_refresh_token.entities.User;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneOffset;

@Service
public class JwtProvider {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.server.url}")
    private String issuerUrl;

    private static final String REFRESH_TYPE = "refreshToken";
    private static final String ACCESS_TYPE = "accessToken";

    public String generateRefreshToken(User user) {
        // 4 hours
        final Instant expiration = LocalDateTime
                .now().plusHours(4)
                .toInstant(ZoneOffset.of("-03:00"));

        try{
            return JWT.create()
                    .withIssuer(issuerUrl)
                    .withSubject(user.getUsername())
                    .withClaim("type", REFRESH_TYPE)
                    .withExpiresAt(expiration)
                    .sign(getAlgorithm());
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating refresh token", exception);
        }
    }

    public String generateAccessToken(User user) {
        // 1 hour
        final Instant expiration = LocalDateTime
                .now().plusHours(1)
                .toInstant(ZoneOffset.of("-03:00"));

        try{
            return JWT.create()
                    .withIssuer(issuerUrl)
                    .withSubject(user.getUsername())
                    .withClaim("type", ACCESS_TYPE)
                    .withExpiresAt(expiration)
                    .sign(getAlgorithm());
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating access token", exception);
        }
    }

    // returns the subject (username)
    public String validateAccessToken(String token) {
        try {
            return JWT.require(getAlgorithm())
                    .withIssuer(issuerUrl)
                    .withClaim("type", ACCESS_TYPE)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return null;
        }
    }

    // returns the subject (username)
    public String validateRefreshToken(String token) {
        try {
            return JWT.require(getAlgorithm())
                    .withIssuer(issuerUrl)
                    .withClaim("type", REFRESH_TYPE)
                    .build()
                    .verify(token)
                    .getSubject();
        } catch (JWTVerificationException exception){
            return null;
        }
    }

    private Algorithm getAlgorithm() {
        return Algorithm.HMAC256(secret);
    }
}
