package com.webapp.bankwebapp.service;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.webapp.bankwebapp.entity.Transaction;
import com.webapp.bankwebapp.entity.User;
import com.webapp.bankwebapp.repository.TransactionRepository;
import com.webapp.bankwebapp.repository.UserRepository;

import jakarta.transaction.Transactional;

@Service
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository transactionRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void deposit(String email,double amount)
    {
        if (amount <= 0) {
            throw new RuntimeException("Amount must be positive");
        }
        User user=userRepository.findByEmail(email);
        if(user!=null)
        {
            Transaction transaction = new Transaction("DEPOSIT", amount, LocalDateTime.now(),user);
            transactionRepository.save(transaction);
            user.setBalance(user.getBalance()+amount);
            userRepository.save(user);
        }
        else {
            throw new RuntimeException("User not found");
        }
    }

    @Override
    @Transactional
    public void withdraw(String email, double amount) {
        if (amount <= 0) {
            throw new RuntimeException("Amount must be positive");
        }

        User user = userRepository.findByEmail(email);

        if (user != null) {
            if (user.getBalance() < amount) {
                throw new RuntimeException("Insufficient Balance");
            }

            Transaction transaction = new Transaction("WITHDRAW", amount, LocalDateTime.now(), user);
            transactionRepository.save(transaction);

            user.setBalance(user.getBalance() - amount);
            userRepository.save(user);
        } else {
            throw new RuntimeException("User not found");
        }
    }


    @Transactional
    public void transfer(String senderEmail,String receiverEmail,double amount){
          if(amount<=0)
          {
            throw new RuntimeException("Amount must be positive");
          }

          User sender=userRepository.findByEmail(senderEmail);
          User receiver=userRepository.findByEmail(receiverEmail);

          if(sender==null || receiver==null)
          {
            throw new RuntimeException("User not found");
          }

          if(sender.getBalance()<amount)
          {
             throw new RuntimeException("Insufficient balance for transfer");
          }
          sender.setBalance(sender.getBalance()-amount);
          receiver.setBalance(receiver.getBalance()+amount);

          Transaction deposit = new Transaction("DEPOSIT", amount, LocalDateTime.now(),receiver);
          Transaction withdraw = new Transaction("WITHDRAW", amount, LocalDateTime.now(), sender);

          transactionRepository.save(deposit);
          transactionRepository.save(withdraw);
          userRepository.save(sender);
          userRepository.save(receiver);
    }
}
