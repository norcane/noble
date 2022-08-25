package com.norcane.noble.service;

import com.norcane.noble.model.User;

import java.util.Optional;

public interface UserService {

    Optional<User> findByUsername(String username);
}
