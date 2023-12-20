package com.example.r23167vbap.service;

import com.example.r23167vbap.model.entity.Uzivatel;

public interface UzivatelSerivce {
    Uzivatel createAccount(Uzivatel uzivatel);
    String authenticateAccount(String email, String password);
    Uzivatel getAccountByEmail(String email);
}
