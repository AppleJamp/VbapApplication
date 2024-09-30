package com.example.r23167vbap.service.impl;

import com.example.r23167vbap.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.User;
import java.util.HashSet;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        com.example.r23167vbap.model.entity.User user = userRepository.findByEmail(email).orElseThrow(() ->  new RuntimeException("Wrong user credentials"));

        return new User(
                user.getEmail(),
                user.getPassword(),
                new HashSet<>(Set.of(new SimpleGrantedAuthority("ROLE_" + user.getRole().name()))));
    }
}

