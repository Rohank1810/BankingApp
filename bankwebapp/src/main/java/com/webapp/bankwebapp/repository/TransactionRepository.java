package com.webapp.bankwebapp.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.webapp.bankwebapp.entity.Transaction;
import com.webapp.bankwebapp.entity.User;

@Repository
public interface TransactionRepository  extends JpaRepository<Transaction,Long>{
    List<Transaction> findByUser(User user);
}
