package com.example.r23167vbap.service;

import com.example.r23167vbap.model.entity.User;

public interface UserService {
    User createAccount(User user);
    String authenticateAccount(String email, String password);
    User getAccountByEmail(String email);
}
