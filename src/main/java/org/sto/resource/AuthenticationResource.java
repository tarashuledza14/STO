package org.sto.resource;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import lombok.RequiredArgsConstructor;
import org.sto.dto.JwtAuthenticationResponseDTO;
import org.sto.dto.SignInRequestDTO;
import org.sto.dto.SignUpRequestDTO;
import org.sto.service.impl.AuthenticationServiceImpl;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthenticationResource {

    private final AuthenticationServiceImpl authenticationService;

    @PostMapping("/signup")
    public ResponseEntity<JwtAuthenticationResponseDTO> signup(@RequestBody SignUpRequestDTO request) {
        return ResponseEntity.ok(authenticationService.signup(request));
    }

    @PostMapping("/signin")
    public ResponseEntity<JwtAuthenticationResponseDTO> signin(@RequestBody SignInRequestDTO request) {
        return ResponseEntity.ok(authenticationService.signin(request));
    }
}