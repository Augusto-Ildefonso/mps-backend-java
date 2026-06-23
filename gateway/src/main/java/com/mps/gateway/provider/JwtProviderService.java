package com.mps.gateway.provider;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.exceptions.JWTVerificationException;

@Service
public class JwtProviderService {

    @Value("${secret.token.key}")
    private String secretKey;

    public String validateToken(String token) {

        if (token == null || token.isBlank()) {
            return null;
        }

        token = token.replace("Bearer ", "");

        try {
            Algorithm algorithm = Algorithm.HMAC256(secretKey);

            return JWT.require(algorithm)
                    .withIssuer("mps")
                    .build()
                    .verify(token)
                    .getSubject();

        } catch (JWTVerificationException e) {
            return null;
        }
    }
}