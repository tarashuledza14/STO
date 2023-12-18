package org.sto.service;

import jakarta.validation.Valid;
import org.sto.entity.User;

import java.util.Optional;

public interface UserService {
    User findById(final Long id);

    User save(@Valid User user);

    void delete(final Long id);
    void update(final Long id, final User user);
    
}
