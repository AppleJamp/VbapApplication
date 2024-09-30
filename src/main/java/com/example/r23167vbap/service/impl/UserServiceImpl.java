package com.example.r23167vbap.service.impl;

import com.example.r23167vbap.model.entity.User;
import com.example.r23167vbap.repository.UserRepository;
import com.example.r23167vbap.service.UserService;
import com.example.r23167vbap.util.JwtTokenUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    @Override
    public User createAccount(User account) {
        if (!userRepository.existsByEmail(account.getEmail())) {
            return userRepository.save(account);
        }
        throw new RuntimeException("Účet již existuje");
    }

    @Override
    public String authenticateAccount(String email, String password) {
        log.info("User with email '{}' is trying to log in.", email);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (AuthenticationException e) {
            throw new RuntimeException("Wrong user credentials");
        }

        log.info("User with email '{}' successfully authenticated.", email);
        return jwtTokenUtil.generateAccessToken(userDetailsService.loadUserByUsername(email));
    }

    @Override
    public User getAccountByEmail(String email) {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("User with this email not found: " + email));
    }
}
