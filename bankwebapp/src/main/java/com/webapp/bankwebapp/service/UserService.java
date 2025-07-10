package com.webapp.bankwebapp.service;

import org.springframework.stereotype.Service;

import com.webapp.bankwebapp.entity.User;

@Service
public interface UserService {

    public User addUser(User user);
    public User findByEmail(String email);
    public User getUserProfile(String email);
}