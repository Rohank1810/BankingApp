package com.webapp.bankwebapp.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.bankwebapp.entity.User;



@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    public User findByEmail(String email);
    boolean existsByEmail(String email);
    
} 
