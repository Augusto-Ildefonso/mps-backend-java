package com.mps.auth_microservice.Providers;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Service
public class JwtProviderService {

    @Value("${secret.token.key}")
    private String secretKey;

    public String createToken(String userId) {
        Algorithm algorithm = Algorithm.HMAC256(secretKey);
        return JWT.create()
                .withSubject(userId)
                .withIssuer("mps")
                .sign(algorithm);
    }
}
