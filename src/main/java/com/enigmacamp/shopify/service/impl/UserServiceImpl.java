package com.enigmacamp.shopify.service.impl;

import com.enigmacamp.shopify.model.entity.UserAccount;
import com.enigmacamp.shopify.repository.UserRepository;
import com.enigmacamp.shopify.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;

    @Override
    public UserAccount create(UserAccount user) {
        return userRepository.saveAndFlush(user);
    }

    @Override
    public UserAccount loadUserById(String id) {
        // TODO: Get User By Id
        return null;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
    }
}
