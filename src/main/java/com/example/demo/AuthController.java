package com.example.demo;

import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final JwtService jwtService;
    private final String demoUsername;
    private final String demoPassword;

    public AuthController(JwtService jwtService,
                          @Value("${app.demo.username}") String demoUsername,
                          @Value("${app.demo.password}") String demoPassword) {
        this.jwtService = jwtService;
        this.demoUsername = demoUsername;
        this.demoPassword = demoPassword;
    }

    @PostMapping("/login")
    public ResponseEntity<Map<String, String>> login(@RequestBody LoginRequest request) {
        boolean valid = demoUsername.equals(request.getUsername())
                && demoPassword.equals(request.getPassword());

        if (!valid) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(Map.of("error", "Invalid username or password"));
        }

        String token = jwtService.generateToken(request.getUsername());
        return ResponseEntity.ok(Map.of("token", token));
    }
}
