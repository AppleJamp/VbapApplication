package com.example.r23167vbap.service.impl;

import com.example.r23167vbap.model.entity.Uzivatel;
import com.example.r23167vbap.repository.UzivatelRepository;
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

    private final UzivatelRepository uzivatelRepository;

    @Override
    public UserDetails loadUserByUsername(String email) {
        Uzivatel uzivatel = uzivatelRepository.findByEmail(email).orElseThrow(() ->  new RuntimeException("Špatné přihlašovací údaje"));

        return new User(
                uzivatel.getEmail(),
                uzivatel.getHeslo(),
                new HashSet<>(Set.of(new SimpleGrantedAuthority("ROLE_" + uzivatel.getRole().name()))));
    }
}

