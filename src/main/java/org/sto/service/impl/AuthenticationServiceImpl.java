package org.sto.service.impl;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;



import lombok.RequiredArgsConstructor;
import org.sto.dto.JwtAuthenticationResponseDTO;
import org.sto.dto.SignInRequestDTO;
import org.sto.dto.SignUpRequestDTO;
import org.sto.entity.User;
import org.sto.entity.enumerable.Role;
import org.sto.repository.UserRepository;

@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl {

    private final UserRepository userRepository;
    private final UserServiceImpl userService;
    private final PasswordEncoder passwordEncoder;
    private final JwtServiceImpl jwtService;
    private final AuthenticationManager authenticationManager;

    public JwtAuthenticationResponseDTO signup(final SignUpRequestDTO request) {
        var user = User
                .builder()
                .firstName(request.getFirstName())
                .lastName(request.getLastName())
                .email(request.getEmail())
                .password(passwordEncoder.encode(request.getPassword()))
                .role(Role.USER)
                .build();

        user = userService.save(user);
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponseDTO.builder().token(jwt).build();
    }


    public JwtAuthenticationResponseDTO signin(final SignInRequestDTO request) {
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(request.getEmail(), request.getPassword()));
        var user = userRepository.findByEmail(request.getEmail())
                .orElseThrow(() -> new IllegalArgumentException("Invalid email or password."));
        var jwt = jwtService.generateToken(user);
        return JwtAuthenticationResponseDTO.builder().token(jwt).build();
    }

}