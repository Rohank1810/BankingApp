package com.webapp.bankwebapp.service;

import org.springframework.stereotype.Service;

@Service
public interface TransactionService {
  
    public void deposit(String email,double amount);
    void withdraw(String email, double amount);
    void transfer(String senderEmail,String receiverEmail,double amount);
}
