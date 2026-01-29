package com.prmbs.component;

import java.util.Date;

import org.springframework.stereotype.Component;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

@Component
public class JwtUtil {
    private final String SECRET = "secret_key";

    public String generateToken(String username) {
        return JWT.create()
            .withSubject(username)
            .withExpiresAt(new Date(System.currentTimeMillis() + 86400000)) // 1 day
            .sign(Algorithm.HMAC256(SECRET));
    }

    public String validateToken(String token) {
        return JWT.require(Algorithm.HMAC256(SECRET))
                  .build()
                  .verify(token)
                  .getSubject();
    }
}