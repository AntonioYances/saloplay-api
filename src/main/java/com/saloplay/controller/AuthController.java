package com.saloplay.controller;


import com.saloplay.dto.AuthResponse;
import com.saloplay.dto.LoginRequest;
import com.saloplay.dto.RegisterRequest;
import com.saloplay.service.SaloplayService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final SaloplayService service;

    public AuthController(SaloplayService service) {
        this.service = service;
    }

    @PostMapping("/register")
    public ResponseEntity<AuthResponse> register(@RequestBody RegisterRequest request) {
        String result = service.register(request.getUsername(), request.getPassword());

        if (result.startsWith("ERROR")) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new AuthResponse(result));
        }
        return ResponseEntity.ok(new AuthResponse(result));
    }

    @PostMapping("/login")
    public ResponseEntity<AuthResponse> login(@RequestBody LoginRequest request) {
        boolean ok = service.login(request.getUsername(), request.getPassword());

        if (!ok) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED)
                    .body(new AuthResponse("ERROR: autenticación fallida"));
        }
        return ResponseEntity.ok(new AuthResponse("Autenticación satisfactoria"));
    }
}
