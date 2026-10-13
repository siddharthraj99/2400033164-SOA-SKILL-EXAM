package com.example.demo;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import org.junit.jupiter.api.Test;

class JwtServiceTest {

    private static final String SECRET = "ThisIsADemoSecretKeyForLabExamOnly_ChangeMe_123456";

    @Test
    void validTokenReturnsUsername() {
        JwtService service = new JwtService(SECRET, 60000);
        String token = service.generateToken("admin");
        assertEquals("admin", service.validateAndGetUsername(token));
    }

    @Test
    void garbageTokenIsRejected() {
        JwtService service = new JwtService(SECRET, 60000);
        assertNull(service.validateAndGetUsername("not.a.token"));
    }

    @Test
    void expiredTokenIsRejected() {
        JwtService service = new JwtService(SECRET, -1000);
        String token = service.generateToken("admin");
        assertNull(service.validateAndGetUsername(token));
    }
}
