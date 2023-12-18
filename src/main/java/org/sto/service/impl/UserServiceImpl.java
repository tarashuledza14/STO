package org.sto.service.impl;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.sto.entity.User;
import org.sto.repository.UserRepository;
import org.sto.service.UserService;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserDetailsService userDetailsService() {
        return username -> userRepository.findByEmail(username)
                .orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }

    @Override
    public User findById(final Long id) {
        return userRepository.findById(id).orElseThrow();
    }

    @Override
    public User save(User newUser) {
        if (newUser.getId() == null) {
            newUser.setCreatedAt(LocalDateTime.now());
        }

        newUser.setUpdatedAt(LocalDateTime.now());
        return userRepository.save(newUser);
    }

    @Override
    public void delete(final Long id) {
        userRepository.deleteById(id);
    }

    @Override
    public void update(final Long id, final @Valid User user) {
        User savedUser = findById(id);
        savedUser.setId(user.getId());
        savedUser.setFirstName(user.getFirstName());
        savedUser.setLastName(user.getLastName());
        savedUser.setPhoneNumber(user.getPhoneNumber());
    }
}