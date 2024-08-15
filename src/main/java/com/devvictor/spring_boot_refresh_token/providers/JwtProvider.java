package com.devvictor.spring_boot_refresh_token.providers;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTCreationException;
import com.auth0.jwt.exceptions.JWTVerificationException;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.time.Instant;

@Service
public class JwtProvider {

    @Value("${api.security.token.secret}")
    private String secret;

    @Value("${api.server.url")
    private String issuerUrl;


    public String generateToken(String subject, Instant expiration) {
        try{
            Algorithm algorithm = Algorithm.HMAC256(secret);
            return JWT.create()
                    .withIssuer(issuerUrl)
                    .withSubject(subject)
                    .withExpiresAt(expiration)
                    .sign(algorithm);
        } catch (JWTCreationException exception) {
            throw new RuntimeException("Error while generating token", exception);
        }
    }

    public DecodedJWT validateToken(String token){
        try {
            Algorithm algorithm = Algorithm.HMAC256(secret);

            return JWT.require(algorithm)
                    .withIssuer(issuerUrl)
                    .build()
                    .verify(token);
        } catch (JWTVerificationException exception){
            return null;
        }
    }
}
