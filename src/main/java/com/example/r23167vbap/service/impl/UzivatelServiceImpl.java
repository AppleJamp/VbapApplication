package com.example.r23167vbap.service.impl;

import com.example.r23167vbap.model.entity.Uzivatel;
import com.example.r23167vbap.repository.UzivatelRepository;
import com.example.r23167vbap.service.UzivatelSerivce;
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
public class UzivatelServiceImpl implements UzivatelSerivce {
    private final UzivatelRepository uzivatelRepository;
    private final AuthenticationManager authenticationManager;
    private final JwtTokenUtil jwtTokenUtil;
    private final UserDetailsService userDetailsService;

    @Override
    public Uzivatel createAccount(Uzivatel account) {
        if (!uzivatelRepository.existsByEmail(account.getEmail())) {
            return uzivatelRepository.save(account);
        }
        throw new RuntimeException("Účet již existuje");
    }

    @Override
    public String authenticateAccount(String email, String password) {
        log.info("User with email '{}' is trying to log in.", email);
        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(email, password));
        } catch (AuthenticationException e) {
            throw new RuntimeException("Špatné přihlašovací údaje");
        }

        log.info("User with email '{}' successfully authenticated.", email);
        return jwtTokenUtil.generateAccessToken(userDetailsService.loadUserByUsername(email));
    }

    @Override
    public Uzivatel getAccountByEmail(String email) {
        return uzivatelRepository.findByEmail(email)
                .orElseThrow(() -> new RuntimeException("Účet s tímtom emailem neexistuje: " + email));
    }
}
